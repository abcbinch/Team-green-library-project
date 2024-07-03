package com.library.service.admin;

import com.library.dto.admin._normal.BookDTO;

import java.util.List;

public interface BookService {

    // 모든 책 조회
    List<BookDTO> allHavingBookManage();

    //    제목 + 저자 + 출판사
    List<BookDTO> findBookByTotal(String searchKeyword);

    // 제목으로 조회
    List<BookDTO> findBookByTitle(String title);

    // 저자로 조회
    List<BookDTO> findBookByAuthor(String author);

    // 십진분류로 조회
    List<BookDTO> findBookByGenre(String genre);

    //책 반납
    void returnUpdateBook(int bookId);

    void returnMultiBooks(List<Long> bookIds);

    // 책 등록
    void createBook(BookDTO book);

    // 책 수정
    void updateBook(BookDTO book);

    // 책 삭제
    void deleteMultiBook(List<Long> bookIds);
    void deleteBook(int id);
    // 특정 도서 상세 조회

    BookDTO getBookById(int id);
    // 이전 도서 제목 조회

    BookDTO previousBook(int id);
    // 다음 도서 제목 조회

    BookDTO nextBook(int id);

    int count();
}
