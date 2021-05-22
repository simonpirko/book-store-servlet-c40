package by.book.dao;

import by.book.entity.Author;

import java.util.List;

public interface AuthorDao {
    void save(Author author);
    List<Author> getAll();
    void deleteById(long id);
    Author getById(long id);
    void updateFirstName(long id, String name);
    void updateLastName(long id, String name);
    void updateDescription(long id, String name);
    boolean contains(String fName, String lName);
    boolean containsId(long id);

}
