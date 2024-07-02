package com.library.repository.admin;

import com.library.dto.admin._normal.AuthorDTO;
import com.library.dto.admin._normal.BookDTO;
import com.library.dto.admin._normal.GenreDTO;
import com.library.dto.admin._normal.PublisherDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository("AdminBookRepository")
public class BookRepositoryImpl implements BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void setAvailability(ResultSet rs, BookDTO book) throws SQLException {
        String availabilityStr = rs.getString("AVAILABILITY");
        Character availability = 0;

        if (availabilityStr != null && !availabilityStr.isEmpty()) {
            availability = availabilityStr.charAt(0);
        }

        book.setAvailability(availability);
    }

    private Optional<Long> findAuthorIdByName(String authorName) {
        String getAuthorSql = "SELECT AUTHOR_ID FROM AUTHORS WHERE AUTHOR_NAME = ?";
        try {
            Long authorId = jdbcTemplate.queryForObject(getAuthorSql, Long.class, authorName);
            return Optional.ofNullable(authorId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Long getOrCreateAuthor(String authorName) {
        return findAuthorIdByName(authorName).orElseGet(() -> {
            String insertAuthorSql = "INSERT INTO AUTHORS (AUTHOR_ID, AUTHOR_NAME) VALUES (AUTHOR_IDX.nextval, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertAuthorSql, new String[]{"AUTHOR_ID"});
                ps.setString(1, authorName);
                return ps;
            }, keyHolder);

            Number generatedId = keyHolder.getKey();
            if (generatedId != null) {
                return generatedId.longValue();
            } else {
                throw new IllegalStateException("Generated author ID is null");
            }
        });
    }

    private Optional<Long> findPublisherIdByName(String publisherName) {
        String getPublisherSql = "SELECT PUBLISHER_ID FROM PUBLISHERS WHERE PUBLISHER_NAME = ?";
        try {
            Long publisherId = jdbcTemplate.queryForObject(getPublisherSql, Long.class, publisherName);
            return Optional.ofNullable(publisherId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Long getOrCreatePublisher(String publisherName) {
        return findPublisherIdByName(publisherName).orElseGet(() -> {
            String insertPublisherSql = "INSERT INTO PUBLISHERS (PUBLISHER_ID, PUBLISHER_NAME) VALUES (PUBLISHER_IDX.NEXTVAL, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertPublisherSql, new String[]{"PUBLISHER_ID"});
                ps.setString(1, publisherName);
                return ps;
            }, keyHolder);

            Number generatedId = keyHolder.getKey();
            if (generatedId != null) {
                return generatedId.longValue();
            } else {
                throw new IllegalStateException("Generated publisher ID is null");
            }
        });
    }

    @Override
    public List<BookDTO> allHavingBookManage() {
        String sql = "SELECT BOOKS.BOOK_ID, BOOKS.TITLE, AUTHORS.AUTHOR_NAME, PUBLISHERS.PUBLISHER_NAME, " +
                "BOOKS.PUBLICATION_DATE, BOOKS.GENRE_FULLNAME, BOOKS.AVAILABILITY, " +
                "(SELECT COUNT(*) FROM BOOKS) AS total_count " +
                "FROM BOOKS " +
                "JOIN AUTHORS ON BOOKS.AUTHOR_ID = AUTHORS.AUTHOR_ID " +
                "JOIN PUBLISHERS ON BOOKS.PUBLISHER_ID = PUBLISHERS.PUBLISHER_ID " +
                "ORDER BY BOOKS.BOOK_ID ASC";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            BookDTO book = new BookDTO();
            book.setBookId(rs.getInt("BOOK_ID"));
            book.setTitle(rs.getString("TITLE"));
            AuthorDTO author = new AuthorDTO();
            author.setAuthorName(rs.getString("AUTHOR_NAME"));
            book.setAuthor(author);
            PublisherDTO publisher = new PublisherDTO();
            publisher.setPublisherName(rs.getString("PUBLISHER_NAME"));
            book.setPublisher(publisher);
            book.setPublicationDate(rs.getDate("PUBLICATION_DATE"));
            book.setGenreFullname(rs.getString("GENRE_FULLNAME"));
            setAvailability(rs, book);
            return book;
        });
    }

    @Override
    public List<BookDTO> findBookByTotal(String total) {
        String sql = "SELECT BOOKS.BOOK_ID, BOOKS.TITLE, AUTHORS.AUTHOR_NAME, PUBLISHERS.PUBLISHER_NAME, " +
                "BOOKS.PUBLICATION_DATE, BOOKS.GENRE_FULLNAME, BOOKS.AVAILABILITY, " +
                "(SELECT COUNT(*) FROM BOOKS) AS total_count " +
                "FROM BOOKS " +
                "JOIN AUTHORS ON BOOKS.AUTHOR_ID = AUTHORS.AUTHOR_ID " +
                "JOIN PUBLISHERS ON BOOKS.PUBLISHER_ID = PUBLISHERS.PUBLISHER_ID " +
                "WHERE BOOKS.TITLE LIKE ? OR AUTHORS.AUTHOR_NAME LIKE ? " +
                "ORDER BY 1 ASC";

        String queryParam = "%" + total + "%";
        return jdbcTemplate.query(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, queryParam);
            ps.setString(2, queryParam);
            return ps;
        }, (rs, rowNum) -> {
            BookDTO book = new BookDTO();
            book.setBookId(rs.getInt("BOOK_ID"));
            book.setTitle(rs.getString("TITLE"));
            AuthorDTO author = new AuthorDTO();
            author.setAuthorName(rs.getString("AUTHOR_NAME"));
            book.setAuthor(author);
            PublisherDTO publisher = new PublisherDTO();
            publisher.setPublisherName(rs.getString("PUBLISHER_NAME"));
            book.setPublisher(publisher);
            book.setPublicationDate(rs.getDate("PUBLICATION_DATE"));
            book.setGenreFullname(rs.getString("GENRE_FULLNAME"));
            setAvailability(rs, book);
            return book;
        });
    }

    @Override
    public List<BookDTO> findBookByTitle(String title) {
        String sql = "SELECT BOOKS.BOOK_ID, BOOKS.TITLE, AUTHORS.AUTHOR_NAME, PUBLISHERS.PUBLISHER_NAME, " +
                "BOOKS.PUBLICATION_DATE, BOOKS.GENRE_FULLNAME, BOOKS.AVAILABILITY, " +
                "(SELECT COUNT(*) FROM BOOKS) AS total_count " +
                "FROM BOOKS " +
                "JOIN AUTHORS ON BOOKS.AUTHOR_ID = AUTHORS.AUTHOR_ID " +
                "JOIN PUBLISHERS ON BOOKS.PUBLISHER_ID = PUBLISHERS.PUBLISHER_ID " +
                "WHERE BOOKS.TITLE LIKE ? " +
                "ORDER BY 1 ASC";

        String queryParam = "%" + title + "%";
        return jdbcTemplate.query(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, queryParam);
            return ps;
        }, (rs, rowNum) -> {
            BookDTO book = new BookDTO();
            book.setBookId(rs.getInt("BOOK_ID"));
            book.setTitle(rs.getString("TITLE"));
            AuthorDTO author = new AuthorDTO();
            author.setAuthorName(rs.getString("AUTHOR_NAME"));
            book.setAuthor(author);
            PublisherDTO publisher = new PublisherDTO();
            publisher.setPublisherName(rs.getString("PUBLISHER_NAME"));
            book.setPublisher(publisher);
            book.setPublicationDate(rs.getDate("PUBLICATION_DATE"));
            book.setGenreFullname(rs.getString("GENRE_FULLNAME"));
            setAvailability(rs, book);
            return book;
        });
    }

    @Override
    public List<BookDTO> findBookByAuthor(String authorName) {
        String sql = "SELECT BOOKS.BOOK_ID, BOOKS.TITLE, AUTHORS.AUTHOR_NAME, PUBLISHERS.PUBLISHER_NAME, " +
                "BOOKS.PUBLICATION_DATE, BOOKS.GENRE_FULLNAME, BOOKS.AVAILABILITY, " +
                "(SELECT COUNT(*) FROM BOOKS) AS total_count " +
                "FROM BOOKS " +
                "JOIN AUTHORS ON BOOKS.AUTHOR_ID = AUTHORS.AUTHOR_ID " +
                "JOIN PUBLISHERS ON BOOKS.PUBLISHER_ID = PUBLISHERS.PUBLISHER_ID " +
                "WHERE AUTHORS.AUTHOR_NAME LIKE ? " +
                "ORDER BY 1 ASC";

        String queryParam = "%" + authorName + "%";
        return jdbcTemplate.query(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, queryParam);
            return ps;
        }, (rs, rowNum) -> {
            BookDTO book = new BookDTO();
            book.setBookId(rs.getInt("BOOK_ID"));
            book.setTitle(rs.getString("TITLE"));
            AuthorDTO author = new AuthorDTO();
            author.setAuthorName(rs.getString("AUTHOR_NAME"));
            book.setAuthor(author);
            PublisherDTO publisher = new PublisherDTO();
            publisher.setPublisherName(rs.getString("PUBLISHER_NAME"));
            book.setPublisher(publisher);
            book.setPublicationDate(rs.getDate("PUBLICATION_DATE"));
            book.setGenreFullname(rs.getString("GENRE_FULLNAME"));
            setAvailability(rs, book);
            return book;
        });
    }

    @Override
    public List<BookDTO> findBookByGenre(String genreFullName) {
        String sql = "SELECT BOOKS.BOOK_ID, BOOKS.TITLE, AUTHORS.AUTHOR_NAME, PUBLISHERS.PUBLISHER_NAME, " +
                "BOOKS.PUBLICATION_DATE, BOOKS.GENRE_FULLNAME, BOOKS.AVAILABILITY, " +
                "(SELECT COUNT(*) FROM BOOKS) AS total_count " +
                "FROM BOOKS " +
                "JOIN AUTHORS ON BOOKS.AUTHOR_ID = AUTHORS.AUTHOR_ID " +
                "JOIN PUBLISHERS ON BOOKS.PUBLISHER_ID = PUBLISHERS.PUBLISHER_ID " +
                "WHERE BOOKS.GENRE_FULLNAME LIKE ? " +
                "ORDER BY 1 ASC";

        String queryParam = "%" + genreFullName + "%";
        return jdbcTemplate.query(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, queryParam);
            return ps;
        }, (rs, rowNum) -> {
            BookDTO book = new BookDTO();
            book.setBookId(rs.getInt("BOOK_ID"));
            book.setTitle(rs.getString("TITLE"));
            AuthorDTO author = new AuthorDTO();
            author.setAuthorName(rs.getString("AUTHOR_NAME"));
            book.setAuthor(author);
            PublisherDTO publisher = new PublisherDTO();
            publisher.setPublisherName(rs.getString("PUBLISHER_NAME"));
            book.setPublisher(publisher);
            book.setPublicationDate(rs.getDate("PUBLICATION_DATE"));
            book.setGenreFullname(rs.getString("GENRE_FULLNAME"));
            setAvailability(rs, book);
            return book;
        });
    }

    @Override
    public int updateBookAvailability(int bookId) {
        String sql = "UPDATE BOOKS SET AVAILABILITY = 0 WHERE BOOK_ID = ?";
        return jdbcTemplate.update(sql, bookId);
    }

    @Override
    public int updateMultipleBooksAvailability(List<Long> bookIds) {
        StringBuilder sql = new StringBuilder("UPDATE BOOKS SET AVAILABILITY = 0 WHERE BOOK_ID IN (");
        for (int i = 0; i < bookIds.size(); i++) {
            sql.append(bookIds.get(i));
            if (i < bookIds.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        return jdbcTemplate.update(sql.toString());
    }

    @Override
    public int createBook(BookDTO book) {
        // 저자 정보 체크
        AuthorDTO authorDTO = book.getAuthor();
        if (authorDTO == null) {
            throw new IllegalArgumentException("저자 정보가 없습니다.");
        }
        Long authorId = getOrCreateAuthor(authorDTO.getAuthorName());

        // 출판사 정보 체크
        PublisherDTO publisherDTO = book.getPublisher();
        if (publisherDTO == null) {
            throw new IllegalArgumentException("출판사 정보가 없습니다.");
        }
        Long publisherId = getOrCreatePublisher(publisherDTO.getPublisherName());

        // 장르 정보 체크
        GenreDTO genreDTO = book.getGenre();
        if (genreDTO == null) {
            throw new IllegalArgumentException("장르 정보가 없습니다.");
        }
        String genreId = genreDTO.getGenreId();

        // 책 정보 삽입 SQL
        String sql = "INSERT INTO BOOKS (BOOK_ID, AUTHOR_ID, PUBLISHER_ID, GENRE_ID, GENRE_FULLNAME, " +
                "TITLE, IMG, ISBN, LOCATION, SUMMARY, PUBLICATION_DATE) " +
                "VALUES (BOOK_IDX.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // SQL 실행 및 결과 반환
        return jdbcTemplate.update(sql, authorId, publisherId, genreId,
                book.getGenreFullname(), book.getTitle(), book.getImg(), book.getIsbn(),
                book.getLocation(), book.getSummary(), new Date(book.getPublicationDate().getTime()));
    }


    @Override
    public int updateBook(BookDTO book) {
        Long authorId = getOrCreateAuthor(book.getAuthor().getAuthorName());
        Long publisherId = getOrCreatePublisher(book.getPublisher().getPublisherName());

        String sql = "UPDATE BOOKS SET AUTHOR_ID = ?, PUBLISHER_ID = ?, GENRE_ID = ?, GENRE_FULLNAME = ?, " +
                "TITLE = ?, IMG = ?, ISBN = ?, LOCATION = ?, SUMMARY = ?, PUBLICATION_DATE = ? " +
                "WHERE BOOK_ID = ?";
        return jdbcTemplate.update(sql, authorId, publisherId, book.getGenre().getGenreId(),
                book.getGenreFullname(), book.getTitle(), book.getImg(), book.getIsbn(),
                book.getLocation(), book.getSummary(), new Date(book.getPublicationDate().getTime()),
                book.getBookId());
    }

//    도서 삭제
    @Override
    public int deleteMultiBook(List<Long> bookIds) {
        StringBuilder sql = new StringBuilder("DELETE FROM BOOKS WHERE BOOK_ID IN (");
        for (int i = 0; i < bookIds.size(); i++) {
            sql.append(bookIds.get(i));
            if (i < bookIds.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        return jdbcTemplate.update(sql.toString());
    }

    @Override
    public int deleteBook(int bookId){
        String sql = "DELETE FROM BOOKS WHERE BOOK_ID = ?";
        return jdbcTemplate.update(sql, bookId);
    }

    @Override
    public BookDTO getBookById(int bookId) {
        String sql = "SELECT BOOKS.BOOK_ID, BOOKS.GENRE_FULLNAME, BOOKS.TITLE, AUTHORS.AUTHOR_NAME, " +
                "PUBLISHERS.PUBLISHER_NAME, BOOKS.PUBLICATION_DATE, BOOKS.ISBN, BOOKS.LOCATION, " +
                "BOOKS.IMG, BOOKS.SUMMARY " +
                "FROM BOOKS " +
                "JOIN AUTHORS ON BOOKS.AUTHOR_ID = AUTHORS.AUTHOR_ID " +
                "JOIN PUBLISHERS ON BOOKS.PUBLISHER_ID = PUBLISHERS.PUBLISHER_ID " +
                "WHERE BOOKS.BOOK_ID = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            BookDTO book = new BookDTO();
            book.setBookId(rs.getInt("BOOK_ID"));
            book.setGenreFullname(rs.getString("GENRE_FULLNAME"));
            book.setTitle(rs.getString("TITLE"));
            AuthorDTO author = new AuthorDTO();
            author.setAuthorName(rs.getString("AUTHOR_NAME"));
            book.setAuthor(author);
            PublisherDTO publisher = new PublisherDTO();
            publisher.setPublisherName(rs.getString("PUBLISHER_NAME"));
            book.setPublisher(publisher);
            book.setPublicationDate(rs.getDate("PUBLICATION_DATE"));
            book.setIsbn(rs.getString("ISBN"));
            book.setLocation(rs.getString("LOCATION"));
            book.setImg(rs.getString("IMG"));
            book.setSummary(rs.getString("SUMMARY"));
            return book;
        }, bookId);
    }

    @Override
    public BookDTO previousBook(int bookId) {
        String sql = "SELECT BOOK_ID, TITLE FROM (SELECT BOOK_ID, TITLE, " +
                "ROW_NUMBER() OVER (ORDER BY BOOK_ID DESC) AS rnum FROM BOOKS WHERE BOOK_ID < ?) WHERE rnum = 1";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            BookDTO book = new BookDTO();
            book.setBookId(rs.getInt("BOOK_ID"));
            book.setTitle(rs.getString("TITLE"));
            return book;
        }, bookId);
    }

    @Override
    public BookDTO nextBook(int bookId) {
        String sql = "SELECT BOOK_ID, TITLE FROM (SELECT BOOK_ID, TITLE, " +
                "ROW_NUMBER() OVER (ORDER BY BOOK_ID ASC) AS rnum FROM BOOKS WHERE BOOK_ID > ?) WHERE rnum = 1";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            BookDTO book = new BookDTO();
            book.setBookId(rs.getInt("BOOK_ID"));
            book.setTitle(rs.getString("TITLE"));
            return book;
        }, bookId);
    }
}
