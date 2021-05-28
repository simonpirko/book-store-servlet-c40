package by.book.service;

import by.book.dao.AuthorDao;
import by.book.dao.BookDao;
import by.book.dao.inmemory.InMemoryAuthorDao;
import by.book.dao.inmemory.InMemoryBookDao;
import by.book.dao.postgres.PgBookDao;
import by.book.entity.Author;
import by.book.entity.Book;
import by.book.exception.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaffBookService {
    private BookDao bookDao = new PgBookDao();
    private AuthorDao authorDao = new InMemoryAuthorDao();

    public void save(String name, int price, String description, String genre, List<Long> authorsId,LocalDate publicationData) throws IncorrectData, NotFoundException, DuplicateDataException {
        Book book = create(name,price,description,genre,authorsId,publicationData);
        if(!bookDao.contains(book.getName(), book.getAuthors()))
            bookDao.save(book);
        else{
            throw new DuplicateDataException();
        }
    }

    public Book getById(long id) throws NotFoundException {
        if(bookDao.containsId(id)){
            return bookDao.getBookById(id);
        }else{
            throw new NotFoundException();
        }
    }

    public void update(long id, String name,  int price, String description, String genre, List<Long> authorsId,LocalDate publicationData) throws NotFoundException, IncorrectData, DuplicateDataException {
        Book book = getById(id);
        Book bookUpdate = create(name,price,description,genre,authorsId,publicationData);
        bookUpdate.setId(id);
        containsUpdate(bookUpdate);

        if(!book.getName().equals(bookUpdate.getName())){
            bookDao.updateName(id,name);
        }
        if(book.getPrice() != bookUpdate.getPrice()){
            bookDao.updatePrice(id,bookUpdate.getPrice());
        }
        if(!book.getDescription().equals(bookUpdate.getDescription())){
            bookDao.updateDescription(id,bookUpdate.getDescription());
        }
        if(!book.getGenre().equals(bookUpdate.getGenre())){
            bookDao.updateGenre(id,bookUpdate.getGenre());
        }
        if(!book.getAuthors().equals(bookUpdate.getAuthors())){
            bookDao.updateAuthors(id,bookUpdate.getAuthors());
        }
        if(book.getPublicationDate() != bookUpdate.getPublicationDate()){
            bookDao.updatePublicationDate(id,bookUpdate.getPublicationDate());
        }
    }

    public void delete(long id) throws NotFoundException {
        if(bookDao.containsId(id)){
            bookDao.delete(id);
        }else{
            throw new NotFoundException();
        }
    }

    public List<Book> getAll(){
        return bookDao.getAll();
    }

    private Book create(String name, int price, String description, String genre, List<Long> authorsId,LocalDate publicationData) throws NotFoundException, IncorrectData {
        checkPrice(price);
        return new Book(name,price,description,genre,getAuthorsByListId(authorsId),publicationData);
    }

    private List<Author> getAuthorsByListId(List<Long> authorsId) throws NotFoundException {
        List<Author> authors = new ArrayList<>();
        for(Long id : authorsId){
            if(authorDao.containsId(id)){
                authors.add(authorDao.getById(id));
            }else{
                throw new NotFoundException();
            }
        }
        return authors;
    }

    private void checkPrice(int price) throws IncorrectData {
        if(price < 0)
            throw new IncorrectData();
    }

    private void containsUpdate(Book bookUpdate) throws DuplicateDataException {
        for (Book book : getAll()) {
            if (bookUpdate.getAuthors().equals(book.getAuthors()) & bookUpdate.getName().equals(book.getName()) & bookUpdate.getId() != book.getId())
                throw new DuplicateDataException();
        }
    }
}
