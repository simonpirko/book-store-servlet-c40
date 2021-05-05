package by.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private String name;
    private int price;
    private String description;
    private String genre;
    private List<Comment> comments;
    private List<Author> authors;
    private LocalDate publicationDate;
}
