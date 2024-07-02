package com.library.dto.admin._normal;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class BookDTO {
    private int bookId;
    private String title;
    private String img;
    private String isbn;
    private String location;
    private char availability;
    private String summary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDate;
    private int borrowCount;

    private AuthorDTO author;
    private PublisherDTO publisher;
    private GenreDTO genre;
    private String genreFullname;
}
