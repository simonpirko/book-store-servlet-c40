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
    public void update(Long id, String fName, String lName, String description) throws NotFoundException, InvalidRequestException, DuplicateDataException, DaoException {
        Author author=getById(id);
        Author authorUpdate = create(fName, lName, description);
        authorUpdate.setId(id);
        containsUpdate(authorUpdate);

        if(!author.getFirstName().equals(authorUpdate.getFirstName())){
            authorDao.updateFirstName(id,fName);
        }
        if(!author.getLastName().equals(authorUpdate.getLastName())){
            authorDao.updateFirstName(id,lName);
        }
        if(!author.getDescription().equals(authorUpdate.getDescription())){
            authorDao.updateFirstName(id,description);
        }

    }

    private Author create(String fName, String lName, String description) throws NotFoundException, DuplicateDataException, InvalidRequestException {
        validationParam(fName);
        validationParam(lName);
        validationParam(description);
        return new Author(fName, lName, description);
    }
    public void containsUpdate(Author authorUpdate) throws DaoException{
        for (Author author : getAll()){
            if (authorUpdate.getFirstName().equals(author.getFirstName())
                    & authorUpdate.getLastName().equals(author.getLastName())
                    & authorUpdate.getDescription().equals(author.getDescription())
                    & authorUpdate.getId() != author.getId()){
                throw new DaoException();
            }
        }
    }
    private void validationParam(String param) throws InvalidRequestException {
        if(param == null || param.trim() == "") throw new InvalidRequestException();
    }
    private int validaAndTransformStringToInt(String param) throws InvalidRequestException {
        if(param == null || param.trim() == "") throw new InvalidRequestException();

        int paramInt;
        try {
            paramInt = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new InvalidRequestException();
        }
        return paramInt;
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
