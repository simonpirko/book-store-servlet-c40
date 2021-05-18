package by.book.dao.inmemory;

import by.book.dao.AuthorDao;
import by.book.entity.Author;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAuthorDao implements AuthorDao {
    private static List<Author> authorList = new ArrayList<Author>();
    private int inc = 1;

    @Override
    public List<Author> getAll(){
        return authorList;
    }

    @Override
    public void save(Author author){
        author.setId(inc++);
        authorList.add(author);
    }
    @Override
    public void deleteById(long id){
        authorList.remove(getById(id));
    }
    @Override
    public Author getById(long id){
        for(Author author : authorList){
            if(author.getId() == id)
                return author;
        }
        return null;
    }

    @Override
    public void updateFirstName(long id, String name) {
        Author author = getById(id);
        author.setFirstName(name);
    }

    @Override
    public void updateLastName(long id, String name) {
        Author author = getById(id);
        author.setLastName(name);
    }

    @Override
    public void updateDescription(long id, String desc) {
        Author author = getById(id);
        author.setDescription(desc);
    }

    @Override
    public boolean contains(String fName, String lName) {
        for(Author author : authorList){
            if(author.getFirstName().equals(fName) && author.getLastName().equals(lName))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsId(long id) {
        for(Author author : authorList){
            if(author.getId() == id)
                return true;
        }
        return false;
    }
}
