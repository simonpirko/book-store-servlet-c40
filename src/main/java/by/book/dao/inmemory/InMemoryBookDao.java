package by.book.dao.inmemory;

import by.book.dao.BookDao;
import by.book.entity.Author;
import by.book.entity.Book;
import by.book.exception.DaoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryBookDao implements BookDao {
    private static List<Book> bookList = new ArrayList<>();
    private static int incId = 1;
    @Override
    public List<Book> getAll() {
        return bookList;
    }

    @Override
    public Book getBookById(long id) throws DaoException {
        for(Book book : bookList){
            if(book.getId() == id)
                return book;
        }
        throw new DaoException("book with '" + id + "' not found ");
    }

    @Override
    public Book getBookByName(String name) throws DaoException {
        for(Book book : bookList){
            if(book.getName().equals(name))
                return book;
        }
        throw new DaoException("book with '" + name + "' not found ");
    }

    @Override
    public List<Book> getBookByData(LocalDate date) {
        List <Book> bookListByData = new ArrayList<>();
        for(Book book : bookList){
            if(book.getPublicationDate().equals(date))
                bookListByData.add(book);
        }
        return bookListByData;
    }

    @Override
    public List<Book> getBookByGenre(String genre) {
        List <Book> bookListByData = new ArrayList<>();
        for(Book book : bookList){
            if(book.getGenre().equals(genre))
                bookListByData.add(book);
        }
        return bookListByData;
    }

    @Override
    public List<Book> getBookByGenreAndData(String genre, LocalDate date) {
        List <Book> bookListByData = new ArrayList<>();
        for(Book book : bookList){
            if(book.getGenre().equals(genre) && book.getPublicationDate().equals(date))
                bookListByData.add(book);
        }
        return bookListByData;
    }

    @Override
    public List<Book> getBookByAuthor(Author author) {
        List <Book> bookListByData = new ArrayList<>();
        for(Book book : bookList){
            for(Author authorBook : book.getAuthors()){
                if(authorBook.equals(author))
                    bookListByData.add(book);
            }
        }
        return bookListByData;
    }

    @Override
    public void save(Book book) throws DaoException {
        if(!contains(book.getName(),book.getAuthors())){
            book.setId(incId++);
            bookList.add(book);
            return;
        }
        throw new DaoException();
    }

    @Override
    public void delete(long id) throws DaoException{
        bookList.remove(getBookById(id));
    }

    @Override
    public void updateName(long id, String name) throws DaoException {
        Book book = getBookById(id);
        book.setName(name);
    }

    @Override
    public void updateDescription(long id, String description)throws DaoException {
        Book book = getBookById(id);
        book.setDescription(description);
    }

    @Override
    public void updateAuthors(long id, List<Author> authors)throws DaoException {
        Book book = getBookById(id);
        book.setAuthors(authors);
    }

    @Override
    public void updatePrice(long id, int price)throws DaoException {
        Book book = getBookById(id);
        book.setPrice(price);
    }

    @Override
    public void updateGenre(long id, String genre)throws DaoException {
        Book book = getBookById(id);
        book.setGenre(genre);
    }

    @Override
    public void updatePublicationDate(long id, LocalDate date)throws DaoException {
        Book book = getBookById(id);
        book.setPublicationDate(date);
    }

    @Override
    public boolean contains(String name, List<Author> author){
        for(Book book : bookList){
            if(book.getName().equals(name) && book.getAuthors().equals(author)){
                return true;
            }
        }
        return false;
    }
}