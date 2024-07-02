package com.library.controller.admin;

import com.library.dto.admin._normal.WishlistDTO;
import com.library.service.admin.AcquisitionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/admin/BuyBook")
public class AcquisitionRequestController {

    private final AcquisitionRequestService acquisitionRequestService;

    @Autowired
    public AcquisitionRequestController(AcquisitionRequestService acquisitionRequestService) {
        this.acquisitionRequestService = acquisitionRequestService;
    }

    private static class StringToDateConverter implements Converter<String, Date> {

        private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public Date convert(String source) {
            try {
                return dateFormat.parse(source);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd format.", e);
            }
        }
    }


    @GetMapping("")
    public String buy(Model model) {
        List<WishlistDTO> wishList = acquisitionRequestService.allAcquisitionManage();
        model.addAttribute("wishList", wishList);
        return "admin/adminBook/buyBook/bookBuy";
    }

    //    검색
    @GetMapping("/search")
    public ResponseEntity<List<WishlistDTO>> searchBooks(
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword,
            @RequestParam(value = "completeKind", required = false) String completeKind) {

        List<WishlistDTO> wishBooks;
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            wishBooks = acquisitionRequestService.allAcquisitionManage();
        } else {
            wishBooks = switch (searchType) {
                case "title" -> acquisitionRequestService.findAcquisitionByTitle(searchKeyword);
                case "author" -> acquisitionRequestService.findAcquisitionByAuthor(searchKeyword);
                case "publish" -> acquisitionRequestService.findAcquisitionByPublish(searchKeyword);
                default -> acquisitionRequestService.findAcquisitionByTotal(searchKeyword);
            };
        }

        if (completeKind != null && !completeKind.equals("all")) {
            wishBooks = acquisitionRequestService.filterByCompleteKind(wishBooks, completeKind);
        }

        return ResponseEntity.ok(wishBooks);
    }

    //    승인
    @PostMapping("/acceptBooks")
    public ResponseEntity<String> acceptBooks(@RequestBody List<String> bookIds) {
        acquisitionRequestService.acceptsAcquisition(bookIds);
        return ResponseEntity.ok("승인");
    }

    //    거부
    @PostMapping("/refuseBooks")
    public ResponseEntity<String> refuseBooks(@RequestBody List<String> bookIds) {
        acquisitionRequestService.deleteAcquisition(bookIds);
        return ResponseEntity.ok("거부 승인");
    }

    //    페이지 수정
    // POST 요청 처리
    @PostMapping("/updateWishInfo/{id}")
    @ResponseBody // JSON 형태의 응답
    public String updateBtnClick(@PathVariable int id) {
        return "success";
    }
    //페이지 이동 manage -> modify
    @GetMapping("/updateWishInfo")
    public String writeAnnounce(@RequestParam("wishlistId") int wishlistId, Model model) {
        WishlistDTO wish = acquisitionRequestService.getWishById(wishlistId);
        model.addAttribute("wishlist", wish);
        return "admin/adminBook/buyBook/bookUpdate";
    }

    // 수정 페이지
    @GetMapping("/updateWishInfo/{wishlistId}")
    public ResponseEntity<WishlistDTO> getwishlistById(@PathVariable int wishlistId) {
        WishlistDTO wish = acquisitionRequestService.getWishById(wishlistId);

        if (wish == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(wish);
    }

    //    수정 등록
    @PostMapping("/uploadWishInfo")
    public String uploadComplete(@RequestParam("wishlistId") int wishlistId,
                                 @RequestParam("isbn") String isbn,
                                 @RequestParam("title") String title,
                                 @RequestParam("author") String author,
                                 @RequestParam("publisher") String publisher,
                                 @RequestParam("publicationDate") String publicationDate,
                                 @RequestParam("content") String content,
                                 @RequestParam(value = "file", required = false) MultipartFile file,
                                 HttpServletRequest request) {
        try {
            Date publicationDateAsDate = new SimpleDateFormat("yyyy-MM-dd").parse(publicationDate);

            // 파일 처리
            if (file != null && !file.isEmpty()) {
                String uploadsDir = "/resources/static/documents/"; // 업로드 경로
                String realPathToUploads = request.getServletContext().getRealPath(uploadsDir);
                if (!new File(realPathToUploads).exists()) {
                    new File(realPathToUploads).mkdir();
                }
                String orgName = file.getOriginalFilename();
                String fileName = UUID.randomUUID() + "-" + orgName;
                String filePath = realPathToUploads + fileName;
                File dest = new File(filePath);
                file.transferTo(dest);
            }

            // 서비스 계층에서 희망 도서 정보 업데이트
            acquisitionRequestService.updateWishBook(wishlistId, isbn, title, author, publisher, publicationDateAsDate, content);

            return "redirect:/BuyBook"; // 수정 완료 후 목록 페이지로 리다이렉트

        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 예외 발생 시 에러 페이지로 이동
        }
    }
}
