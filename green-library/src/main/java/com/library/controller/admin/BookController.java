package com.library.controller.admin;

import com.library.dto.admin._normal.BookDTO;
import com.library.service.admin.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller("AdminBookController")
@RequestMapping("/Book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //    onload
    @GetMapping()
    public String book(Model model) {
        List<BookDTO> bookDTOS = bookService.allHavingBookManage();
        model.addAttribute("bookDTOS", bookDTOS);
        return "admin/adminBook/manageBook/bookManage";
    }

    //    검색
    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooks(
            @RequestParam(value = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword", required = false) String searchKeyword) {

        List<BookDTO> books;

        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            books = bookService.allHavingBookManage();
        } else {
            books = switch (searchType) {
                case "title" -> bookService.findBookByTitle(searchKeyword);
                case "author" -> bookService.findBookByAuthor(searchKeyword);
                case "genre" -> bookService.findBookByGenre(searchKeyword);
                default -> bookService.findBookByTotal(searchKeyword);
            };
        }
        return ResponseEntity.ok(books);
    }

    //    책 반납
    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(@RequestParam("bookId") int bookId) {
        bookService.returnUpdateBook(bookId);
        return ResponseEntity.ok(bookId + "가 성공적으로 반납되었습니다.");
    }

    // 다수 책 반납
    @PostMapping("/returnMultiBook")
    public ResponseEntity<String> returnMultipleBooks(@RequestBody List<Long> bookIds) {
        bookService.returnMultiBooks(bookIds);
        return ResponseEntity.ok("성공적으로 반납되었습니다.");
    }

    //등록 페이지 이동
    @GetMapping("/WriteBook")
    public String writeBook(Model model) {
        return "admin/adminBook/manageBook/bookWrite";
    }

    //    책 생성 (등록)
    @PostMapping("/createBook")
    public String createBook(@ModelAttribute BookDTO bookDTO,
                             @RequestParam(value = "image", required = false) MultipartFile file,
                             HttpServletRequest request) {
        try {
            saveUploadedFile(file, bookDTO, request);

            bookService.createBook(bookDTO);

            return "redirect:/Book";

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    private void saveUploadedFile(MultipartFile file, BookDTO bookDTO, HttpServletRequest request) throws IOException {
        if (file != null && !file.isEmpty()) {
            String uploadsDir = "/resources/static/documents/";
            String realPathToUploads = request.getServletContext().getRealPath(uploadsDir);
            if (!new File(realPathToUploads).exists()) {
                new File(realPathToUploads).mkdir();
            }
            String orgName = file.getOriginalFilename();
            String fileName = UUID.randomUUID() + "-" + orgName;
            String filePath = realPathToUploads + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);
            bookDTO.setImg(fileName);
        }
    }

    //    수정 페이지 이동
    @GetMapping("/modify")
    public String modify(@RequestParam(value = "bookId") int bookId, Model model) {
        BookDTO bookDTOS = bookService.getBookById(bookId);
        model.addAttribute("bookDTOS", bookDTOS);
        return "admin/adminBook/manageBook/bookWrite";
    }

    //    수정 전 데이터 불러오기
    @PostMapping("getBookById/{bookId}")
    @ResponseBody
    public String getBookById(@PathVariable int bookId) {
        return "success";
    }

    //    책 수정(등록)
    @PostMapping("/updateBook")
    public String updateBook(BookDTO bookDTO) {
        bookService.updateBook(bookDTO);
        return "success";
    }


    //책 상세보기
    @GetMapping("/DetailBook")
    public String detailBook(@RequestParam(value = "bookId") int bookId, Model model) {
        BookDTO bookDTOS = bookService.getBookById(bookId);
        model.addAttribute("bookDTOS", bookDTOS);
        return "admin/adminBook/manageBook/bookDetail";
    }

    @GetMapping("/prevBook")
    public ResponseEntity<BookDTO> getPreviousBook(@RequestParam("bookId") int bookId) {
        try {
            BookDTO prevBook = bookService.previousBook(bookId);
            return new ResponseEntity<>(prevBook, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/nextBook")
    public ResponseEntity<BookDTO> getNextBook(@RequestParam("bookId") int bookId) {
        try {
            BookDTO nextBook = bookService.nextBook(bookId);
            return new ResponseEntity<>(nextBook, HttpStatus.OK);
        } catch (Exception e) {
            return null;
        }
    }

}
