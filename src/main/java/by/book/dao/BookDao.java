package by.book.dao;

import by.book.entity.Author;
import by.book.entity.Book;
import by.book.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface BookDao {
    List<Book> getAll();
    Book getBookById(long id) throws DaoException;
    Book getBookByName(String name) throws DaoException;
    List<Book> getBookByData(LocalDate date);
    List<Book> getBookByGenre(String genre);
    List<Book> getBookByGenreAndData(String genre, LocalDate date);
    List<Book> getBookByAuthor(Author author);
    void save(Book book) throws DaoException;
    void delete(long id) throws DaoException;
    void updateName(long id, String name) throws DaoException;
    void updateDescription(long id, String description) throws DaoException;
    void updateAuthors(long id, List<Author> authors) throws DaoException;
    void updatePrice(long id, int price) throws DaoException;
    void updateGenre (long id, String genre) throws DaoException;
    void updatePublicationDate(long id, LocalDate date) throws DaoException;
    boolean contains(String name, List<Author> author);





}
