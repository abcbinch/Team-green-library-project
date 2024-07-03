package com.library.controller.admin;

import com.library.dto.admin._normal.AnnouncementDTO;
import com.library.dto.admin._normal.AuthorDTO;
import com.library.dto.admin._normal.BookDTO;
import com.library.dto.admin._normal.PublisherDTO;
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
@RequestMapping("/admin/Book")
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

    // 책 삭제
    @PostMapping("/deleteBooks")
    public ResponseEntity<String> deleteBooks(@RequestBody List<Long> bookIds) {
        bookService.deleteMultiBook(bookIds);
        return ResponseEntity.ok("성공적으로 삭제되었습니다.");
    }

    @PostMapping("/deleteBook")
    public ResponseEntity<String> deleteBooks(@RequestBody int bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("성공적으로 삭제되었습니다.");
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

    //    수정 페이지 버튼
    @PostMapping("/modifyBtnClick/{id}")
    @ResponseBody
    public String bookModifyBtnClick(@PathVariable int id) {
        return "success";
    }

    //    책 수정(등록) 페이지 이동
    @GetMapping("/updateBook")
    public String updateBook(@RequestParam("bookId") int bookId, Model model) {
        BookDTO updatedBook = bookService.getBookById(bookId);
        model.addAttribute("book", updatedBook);
        return "admin/adminBook/manageBook/bookWrite";
    }

    @GetMapping("/getBookById/{bookId}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int bookId) {
        BookDTO book = bookService.getBookById(bookId);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(book);
    }

    //    수정 등록
    @PostMapping("/updateBook")
    public ResponseEntity<String> updateBook(@ModelAttribute BookDTO bookDTO,
                                             @RequestParam("image") MultipartFile file) {
        // 클라이언트에서 전송된 작가 이름과 출판사 이름을 AuthorDTO와 PublisherDTO 객체로 변환
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setAuthorName(String.valueOf(bookDTO.getAuthor()));
        bookDTO.setAuthor(authorDTO);

        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setPublisherName(String.valueOf(bookDTO.getPublisher()));
        bookDTO.setPublisher(publisherDTO);

        // 나머지 필요한 로직을 구현합니다.

        return ResponseEntity.ok("도서 정보가 업데이트되었습니다.");
    }


    // 조회 페이지
    @PostMapping("/details/{id}")
    @ResponseBody
    public String details(@PathVariable int id) {
        return "success";
    }

    //조회 페이지 이동
    @GetMapping("/DetailBook")
    public String detailBook(@RequestParam("bookId") int bookId, Model model) {
        BookDTO book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
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
