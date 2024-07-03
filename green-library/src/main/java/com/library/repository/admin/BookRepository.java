package com.library.repository.admin;

import com.library.dto.admin._normal.BookDTO;

import java.util.List;

public interface BookRepository {

    // 모든 책 목록 조회
    List<BookDTO> allHavingBookManage();

//    제목 + 저자 + 출판사 검색
    List<BookDTO> findBookByTotal(String total);

    // 제목으로 책 검색
    List<BookDTO> findBookByTitle(String title);

    // 저자로 책 검색
    List<BookDTO> findBookByAuthor(String authorName);

    // 십진분류로 책 검색
    List<BookDTO> findBookByGenre(String genreFullName);

    // 책 반납
    int updateBookAvailability(int bookId);

    int updateMultipleBooksAvailability(List<Long> bookIds);

    // 책 등록
    int createBook(BookDTO book);

    // 책 수정
    int updateBook(BookDTO book);

    // 책 삭제
    int deleteMultiBook(List<Long> bookId);
    int deleteBook(int bookId);

    // 특정 도서 상세 조회
    BookDTO getBookById(int bookId);

    // 이전 도서 제목 조회
    BookDTO previousBook(int bookId);

    // 다음 도서 제목 조회
    BookDTO nextBook(int bookId);

    int count();
}
