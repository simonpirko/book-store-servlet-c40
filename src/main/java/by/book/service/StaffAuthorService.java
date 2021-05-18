package by.book.service;

import by.book.dao.AuthorDao;
import by.book.dao.inmemory.InMemoryAuthorDao;
import by.book.entity.Author;
import by.book.exception.DaoException;
import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StaffAuthorService {
    AuthorDao authorDao = new InMemoryAuthorDao();

    public Author getById(String id) throws InvalidRequestException, NotFoundException {
        long idLong = validaAndTransformStringToLong(id);
        if(authorDao.getById(idLong) != null){
            return authorDao.getById(idLong);
        }else{
            throw new NotFoundException();
        }
    }

    public void save(String fName, String lName, String description) throws InvalidRequestException, DaoException {
        validationParam(fName);
        validationParam(lName);
        validationParam(description);
        Author author = new Author(0,fName,lName,description);
        if(!authorDao.contains(author)){
            authorDao.save(author);
        }else{
            throw new DaoException();
        }
    }

    public List<Author> getAll(){
        return authorDao.getAll();
    }

    public void remove(String id) throws NotFoundException, InvalidRequestException {
        authorDao.deleteById(getById(id).getId());
    }

    public List<Author> getListById(String [] arrayId) throws NotFoundException, InvalidRequestException {
        List<Author> authors = new ArrayList<>();
        for(String id : arrayId){
            authors.add(getById(id));
        }
        return authors;
    }

    private void validationParam(String param) throws InvalidRequestException {
        if(param == null || param.trim() == "") throw new InvalidRequestException();
    }

    private long validaAndTransformStringToLong(String param) throws InvalidRequestException {
        validationParam(param);

        try {
            return Long.parseLong(param);
        } catch (NumberFormatException e) {
            throw new InvalidRequestException();
        }
    }
}
