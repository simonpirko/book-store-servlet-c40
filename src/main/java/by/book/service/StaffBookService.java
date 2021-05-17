package by.book.service;

import by.book.dao.BookDao;
import by.book.dao.inmemory.InMemoryBookDao;
import by.book.entity.Author;
import by.book.entity.Book;
import by.book.exception.DaoException;
import by.book.exception.IncorrectData;
import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public class StaffBookService {
    private BookDao bookDao = new InMemoryBookDao();

    public void save(String name, String price, String description, String genre, List<Author> authors,String publicationData) throws DaoException, InvalidRequestException, IncorrectData {
        if(!bookDao.contains(name,authors)){
            Book book = create(name,price,description,genre,authors,publicationData);
            bookDao.save(book);
        }else{
            throw new DaoException();
        }
    }

    public Book getById(String id) throws InvalidRequestException, NotFoundException {
        long idLong = validaAndTransformStringToLong(id);
        if(bookDao.getBookById(idLong) != null){
            return bookDao.getBookById(idLong);
        }else{
            throw new NotFoundException();
        }
    }

    public void update(String id, String name, String price, String description, String genre, List<Author> authors,String publicationData) throws InvalidRequestException, NotFoundException, DaoException, IncorrectData {
        Book book = getById(id);
        long idLong = validaAndTransformStringToLong(id);
        Book bookUpdate = create(name,price,description,genre,authors,publicationData);
        bookUpdate.setId(idLong);
        containsUpdate(bookUpdate);

        if(!book.getName().equals(bookUpdate.getName())){
            bookDao.updateName(idLong,name);
        }
        if(book.getPrice() != bookUpdate.getPrice()){
            bookDao.updatePrice(idLong,bookUpdate.getPrice());
        }
        if(!book.getDescription().equals(bookUpdate.getDescription())){
            bookDao.updateDescription(idLong,bookUpdate.getDescription());
        }
        if(!book.getGenre().equals(bookUpdate.getGenre())){
            bookDao.updateGenre(idLong,bookUpdate.getGenre());
        }
        if(!book.getAuthors().equals(bookUpdate.getAuthors())){
            bookDao.updateAuthors(idLong,bookUpdate.getAuthors());
        }
        if(book.getPublicationDate() != bookUpdate.getPublicationDate()){
            bookDao.updatePublicationDate(idLong,bookUpdate.getPublicationDate());
        }
    }

    public void delete(String id) throws InvalidRequestException, NotFoundException {
        getById(id);
        bookDao.delete(validaAndTransformStringToLong(id));
    }

    public List<Book> getAll(){
        return bookDao.getAll();
    }

    private Book create(String name, String price, String description, String genre, List<Author> authors,String publicationData) throws InvalidRequestException, IncorrectData {
        validationParam(name);
        validationParam(description);
        validationParam(genre);
        int priceInt = validaAndTransformStringToInt(price);
        checkPrice(priceInt);
        LocalDate publicationDate = validationAndTransformStringToLocalData(publicationData);
        return new Book(name,priceInt,description,genre,authors,publicationDate);
    }

    private void checkPrice(int price) throws IncorrectData {
        if(price < 0)
            throw new IncorrectData();
    }

    private void containsUpdate(Book bookUpdate) throws DaoException {
        for (Book book : getAll()) {
            if (bookUpdate.getAuthors().equals(book.getAuthors()) & bookUpdate.getName().equals(book.getName()) & bookUpdate.getId() != book.getId())
                throw new DaoException();
        }
    }
    //Нет реализации, пока неизвестно на счет типа даты
    private LocalDate validationAndTransformStringToLocalData(String param){
        return LocalDate.parse(param);
    }

    private long validaAndTransformStringToLong(String param) throws InvalidRequestException {
        validationParam(param);
        try {
            return Long.parseLong(param);
        }catch (NumberFormatException e){
            throw new InvalidRequestException();
        }
    }

    private void validationParam(String param) throws InvalidRequestException {
        if(param == null || param.trim() == "") throw new InvalidRequestException();
    }

    private int validaAndTransformStringToInt(String param) throws InvalidRequestException {
        validationParam(param);

        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new InvalidRequestException();
        }
    }
}
