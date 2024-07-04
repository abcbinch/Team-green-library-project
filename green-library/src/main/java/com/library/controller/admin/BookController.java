package com.library.controller.admin;

import com.library.dto.admin._normal.AuthorDTO;
import com.library.dto.admin._normal.BookDTO;
import com.library.dto.admin._normal.PublisherDTO;
import com.library.propertyeditor.AuthorDTOPropertyEditor;
import com.library.propertyeditor.PublisherDTOPropertyEditor;
import com.library.service.admin.BookService;
import com.library.service.admin.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller("AdminBookController")
@RequestMapping("/admin/Book")
public class BookController {

    private final BookService bookService;
    private final StorageService storageService;

    @Autowired
    public BookController(BookService bookService, StorageService storageService) {
        this.bookService = bookService;
        this.storageService = storageService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(AuthorDTO.class, new AuthorDTOPropertyEditor());
        binder.registerCustomEditor(PublisherDTO.class, new PublisherDTOPropertyEditor());
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
            @RequestParam(value = "searchType", name = "searchType", required = false) String searchType,
            @RequestParam(value = "searchKeyword", name = "searchKeyword", required = false) String searchKeyword) {

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

        int count = bookService.count();
        model.addAttribute("nextId", count);
        return "admin/adminBook/manageBook/bookWrite";
    }

    //    책 생성 (등록)
    @PostMapping("/createBook")
    public String createBook(@ModelAttribute BookDTO bookDTO,
                             @RequestParam(value = "image", name = "image", required = false) MultipartFile file,
                             HttpServletRequest request) {
        try {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setAuthorName(bookDTO.getAuthorName());
            bookDTO.setAuthor(authorDTO);

            PublisherDTO publisherDTO = new PublisherDTO();
            publisherDTO.setPublisherName(bookDTO.getPublisherName());
            bookDTO.setPublisher(publisherDTO);

            if (file != null && !file.isEmpty()) {
                String fileName = storageService.store(file);
                bookDTO.setImg(fileName);
            }

            bookService.createBook(bookDTO);

            return "redirect:/admin/Book";

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    //    수정 페이지 버튼
    @PostMapping("/modifyBtnClick/{id}")
    @ResponseBody
    public String bookModifyBtnClick(@PathVariable("id") int bookId) {
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
    public ResponseEntity<BookDTO> getBookById(@PathVariable("bookId") int bookId) {
        BookDTO book = bookService.getBookById(bookId);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(book);
    }

    @PostMapping("/updateBook")
    public ResponseEntity<String> updateBook(@ModelAttribute BookDTO bookDTO,
                                             @RequestParam(value = "image", name = "image", required = false) MultipartFile file,
                                             HttpServletRequest request) {
        try {
            AuthorDTO authorDTO = new AuthorDTO();
            authorDTO.setAuthorName(bookDTO.getAuthorName());
            bookDTO.setAuthor(authorDTO);

            PublisherDTO publisherDTO = new PublisherDTO();
            publisherDTO.setPublisherName(bookDTO.getPublisherName());
            bookDTO.setPublisher(publisherDTO);

            if (file != null && !file.isEmpty()) {
                String fileName = storageService.store(file);
                bookDTO.setImg(fileName);
            }
            bookService.updateBook(bookDTO);

            return ResponseEntity.ok("도서 정보가 업데이트되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("도서 정보 업데이트 중 오류가 발생했습니다.");
        }
    }

    // 조회 페이지
    @PostMapping("/details/{id}")
    @ResponseBody
    public String details(@PathVariable("id") int bookId) {
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
