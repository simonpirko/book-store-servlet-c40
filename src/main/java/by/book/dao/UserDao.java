package by.book.dao;

import by.book.entity.Role;
import by.book.entity.User;
import by.book.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface UserDao {
    void save(User user);
    void updateFirstName(long id, String name);
    void updateLastName(long id, String name);
    void updatePassword(long id, String password);
    void updateBirthday(long id, LocalDate localDate);
    void updateRole(long id, Role role);
    List<User> getAll();
    User getById(long id) throws DaoException;
    User getByUserName(String userName);
    void delete(String userName);
    void delete(long id);
    boolean containsByName(String userName);
    boolean containsByPassword(String password);
}
