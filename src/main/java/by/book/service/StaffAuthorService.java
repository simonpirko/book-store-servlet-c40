package by.book.service;

import by.book.dao.AuthorDao;
import by.book.dao.inmemory.InMemoryAuthorDao;
import by.book.entity.Author;
import by.book.exception.DaoException;
import by.book.exception.DuplicateDataException;
import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StaffAuthorService {
    AuthorDao authorDao = new InMemoryAuthorDao();

    public Author getById(long id) throws NotFoundException {
        if(authorDao.containsId(id)){
            return authorDao.getById(id);
        }else{
            throw new NotFoundException();
        }
    }

    public void save(String fName, String lName, String description) throws DuplicateDataException {
        if(!authorDao.contains(fName,lName)){
            Author author = new Author(0,fName,lName,description);
            authorDao.save(author);
        }else{
            throw new DuplicateDataException();
        }
    }

    public List<Author> getAll(){
        return authorDao.getAll();
    }

    public void remove(long id) throws NotFoundException{
        if(authorDao.containsId(id)){
            authorDao.deleteById(id);
        }else{
            throw new NotFoundException();
        }

    }
}
