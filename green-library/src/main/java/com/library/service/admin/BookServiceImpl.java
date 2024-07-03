package com.library.service.admin;

import com.library.dto.admin._normal.BookDTO;
import com.library.repository.admin.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AdminBookService")
public class BookServiceImpl implements BookService {

    @Qualifier("AdminBookRepository")
    @Autowired
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 모든 책 조회
    public List<BookDTO> allHavingBookManage() {
        return bookRepository.allHavingBookManage();
    }

    //제목 + 저자  + 출판사조회
    public List<BookDTO> findBookByTotal(String total) {
        return bookRepository.findBookByTotal(total);
    }

    // 제목으로 조회
    public List<BookDTO> findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    // 저자로 조회
    public List<BookDTO> findBookByAuthor(String author) {
        return bookRepository.findBookByAuthor(author);
    }

    // 십진분류로 조회
    public List<BookDTO> findBookByGenre(String genre) {
        return bookRepository.findBookByGenre(genre);
    }

    //   책 반납
    @Transactional
    public void returnUpdateBook(int bookId) {
        bookRepository.updateBookAvailability(bookId);
    }
    @Transactional
    public void returnMultiBooks(List<Long> bookIds) {
        bookRepository.updateMultipleBooksAvailability(bookIds);
    }

    // 책 등록
    @Transactional
    public void createBook(BookDTO book) {
        bookRepository.createBook(book);
    }

    // 책 수정
    @Transactional
    public void updateBook(BookDTO book) {
        bookRepository.updateBook(book);
    }

    // 책 삭제
    @Transactional
    public void deleteMultiBook(List<Long> bookIds) {
        bookRepository.deleteMultiBook(bookIds);
    }
    @Transactional
    public void deleteBook(int bookId) {
        bookRepository.deleteBook(bookId);
    }


    // 특정 도서 상세 조회
    public BookDTO getBookById(int id) {
        return bookRepository.getBookById(id);
    }

    // 이전 도서 제목 조회
    public BookDTO previousBook(int id) {
        return bookRepository.previousBook(id);
    }

    // 다음 도서 제목 조회
    public BookDTO nextBook(int id) {
        return bookRepository.nextBook(id);
    }

    @Override
    public int count() {
        return bookRepository.count();
    }
}
