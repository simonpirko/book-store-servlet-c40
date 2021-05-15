package by.book.dao.inmemory;

import by.book.dao.UserDao;
import by.book.entity.Address;
import by.book.entity.Role;
import by.book.entity.User;
import by.book.exception.DaoException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class InMemoryUserDao implements UserDao {
    private static List<User> users = new ArrayList<>();
    private static int incId = 3;

    @Override
    public void save(User user) {
        user.setId(incId++);
        users.add(user);
    }

    @Override
    public void updateFirstName(long id, String name) {
        User user = getById(id);
        if (user != null){
            user.setFirstName(name);
        }
    }

    @Override
    public void updateLastName(long id, String name) {
        User user = getById(id);
        if (user != null){
            user.setLastName(name);
        }
    }

    @Override
    public void updatePassword(long id, String password) {
        User user = getById(id);
        if (user != null) {
            user.setPassword(password);
        }
    }

    @Override
    public void updateBirthday(long id, LocalDateTime localDateTime) {
        User user = getById(id);
        if (user != null) {
            user.setBirthDate(localDateTime);
        }
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByUserName(String userName) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(userName))  {
                return user;
            }
        }
        return null;
    }

    @Override
    public void delete(String userName) {
        users.removeIf(user -> user.getUsername().equals(userName));
    }

    @Override
    public void delete(long id) {
        for (User user : users){
            if (user.getId() == id){
                users.remove(user);
                return;
            }
        }
    }

    @Override
    public boolean containsByName(String userName) {
        for (User user : users){
            if (user.getUsername().equalsIgnoreCase(userName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsByPassword(String password) {
        for (User user : users){
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    static {
        LocalDateTime randDate = LocalDateTime.of(2017, Month.JULY, 9, 11, 6, 22);
        users.add(new User(1, "Moder", "Стэк", "Оверфлоувович", randDate, new Address(0, "www", 1), "1111", Role.MODERATOR));
        users.add(new User(2, "Admin", "Отец", "Отцов", randDate, new Address(1, "www", 1), "1111", Role.ADMIN));
    }

}
