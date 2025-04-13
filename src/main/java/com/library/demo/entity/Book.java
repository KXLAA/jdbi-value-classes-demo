package com.library.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private BookId id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;

    @ColumnName("published_date")
    private LocalDateTime publishedDate;
}
