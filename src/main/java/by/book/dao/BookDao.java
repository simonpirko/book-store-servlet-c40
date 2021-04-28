package by.book.dao;

import by.book.entity.Author;
import by.book.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getBookById(long id);
    Book getBookByName(String name);
    List<Book> getBookByAuthor(Author author);
    void save(Book book);
    void delete(long id);
    void updateName(long id, String name);
    void updateDescription(long id, String description);
    void updateAuthor(long id, Author author);
    void updatePrice(long id, int price);
    void updateGenre (long id, String genre);
    boolean contains(String name, Author author);




}
