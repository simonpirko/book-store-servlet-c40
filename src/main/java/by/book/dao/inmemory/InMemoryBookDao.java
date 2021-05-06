package by.book.dao.inmemory;

import by.book.dao.BookDao;
import by.book.entity.Author;
import by.book.entity.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InMemoryBookDao implements BookDao {
    private static List<Book> bookList = new ArrayList<>();
    @Override
    public List<Book> getAll() {
        return bookList;
    }

    @Override
    public Book getBookById(long id) {
        for(Book book : bookList){
            if(book.getId() == id)
                return book;
        }
        return null;
    }

    @Override
    public Book getBookByName(String name) {
        for(Book book : bookList){
            if(book.getName().equals(name))
                return book;
        }
        return null;
    }

    @Override
    public List<Book> getBookByData(LocalDate date) {
        List <Book> bookListByData = new ArrayList<>();
        for(Book book : bookList){
            if(book.getPublicationDate() == date)
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
            if(book.getGenre().equals(genre) && book.getPublicationDate() == date)
                bookListByData.add(book);
        }
        return bookListByData;
    }

    @Override
    public List<Book> getBookByAuthor(Author author) {
        List <Book> bookListByData = new ArrayList<>();
        for(Book book : bookList){
            for(Author authorBook : book.getAuthors()){
                if(authorBook == author)
                    bookListByData.add(book);
            }
        }
        return bookListByData;
    }

    @Override
    public void save(Book book) {
        bookList.add(book);
    }

    @Override
    public void delete(long id) {
        bookList.remove(getBookById(id));
    }

    @Override
    public void updateName(long id, String name) {
        for(Book book : bookList){
            if(book.getId() == id){
                book.setName(name);
            }
        }
    }

    @Override
    public void updateDescription(long id, String description) {
        for(Book book : bookList){
            if(book.getId() == id){
                book.setDescription(description);
            }
        }
    }

    @Override
    public void updateAuthors(long id, List<Author> authors){
        for(Book book : bookList){
            if(book.getId() == id){
                book.setAuthors(authors);
            }
        }
    }

    @Override
    public void updatePrice(long id, int price) {
        for(Book book : bookList){
            if(book.getId() == id){
                book.setPrice(price);
            }
        }
    }

    @Override
    public void updateGenre(long id, String genre) {
        for(Book book : bookList){
            if(book.getId() == id){
                book.setGenre(genre);
            }
        }
    }

    @Override
    public void updatePublicationDate(long id, LocalDate date) {
        for(Book book : bookList){
            if(book.getId() == id){
                book.setPublicationDate(date);
            }
        }
    }

    @Override
    public boolean contains(String name, List<Author> author){
        for(Book book : bookList){
            if(book.getName().equals(name) && book.getAuthors() == author){
                return true;
            }
        }
        return false;
    }
}
