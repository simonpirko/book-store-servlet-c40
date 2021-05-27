package by.book.dao.inmemory;

import by.book.dao.BookDao;
import by.book.entity.Author;
import by.book.entity.Book;
import java.util.HashSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryBookDao implements BookDao {
    private static List<Book> bookList = new ArrayList<>();
    private static long id = 1;
    @Override
    public List<Book> getAll() {
        return new ArrayList<>(bookList);
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
        if(!contains(book.getName(),book.getAuthors())){
            book.setId(id++);
            bookList.add(book);
        }
    }

    @Override
    public void delete(long id) {
        bookList.remove(getBookById(id));
    }

    @Override
    public void updateName(long id, String name) {
        Book book = getBookById(id);
        book.setName(name);
    }

    @Override
    public void updateDescription(long id, String description) {
        Book book = getBookById(id);
        book.setDescription(description);
    }

    @Override
    public void updateAuthors(long id, List<Author> authors){
        Book book = getBookById(id);
        book.setAuthors(authors);
    }

    @Override
    public void updatePrice(long id, int price) {
        Book book = getBookById(id);
        book.setPrice(price);
    }

    @Override
    public void updateGenre(long id, String genre) {
        Book book = getBookById(id);
        book.setGenre(genre);
    }

    @Override
    public void updatePublicationDate(long id, LocalDate date) {
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

    @Override
   public boolean containsId(long id) {
        for(Book book : bookList){
            if(book.getId() == id)
                return true;
        }
        return false;
    }
  
    public Set<String> getGenre() {
        Set<String> genreSet = new HashSet<>();
        for(Book item : bookList) {
            genreSet.add(item.getGenre());
        }
        return genreSet;
    }
}
