package by.book.dao;

import by.book.entity.Author;
import by.book.entity.Book;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getBookById(long id);
    Book getBookByName(String name);
    List<Book> getBookByData(LocalDate date);
    List<Book> getBookByGenre(String genre);
    List<Book> getBookByGenreAndData(String genre, LocalDate date);
    List<Book> getBookByAuthor(Author author);
    void save(Book book);
    void delete(long id);
    void updateName(long id, String name);
    void updateDescription(long id, String description);
    void updateAuthors(long id, List<Author> authors);
    void updatePrice(long id, int price);
    void updateGenre (long id, String genre);
    void updatePublicationDate(long id, LocalDate date);
    boolean contains(String name, List<Author> author);




}
