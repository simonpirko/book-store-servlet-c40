package by.book.dao;

import by.book.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static List<User> users = new ArrayList<>();
    private static int incId = 1;

    @Override
    public void save(User user) {
        user.setId(incId++);
        users.add(user);
    }

    @Override
    public void updateFirstName(long id, String name) {
        User user = getById(id);
        if (user != null){
            users.remove(user);
            user.setFirstName(name);
            users.add(user);
        }
    }

    @Override
    public void updateLastName(long id, String name) {
        User user = getById(id);
        if (user != null){
            users.remove(user);
            user.setLastName(name);
            users.add(user);
        }
    }

    @Override
    public void updatePassword(long id, String password) {
        User user = getById(id);
        if (user != null) {
            users.remove(user);
            user.setPassword(password);
            users.add(user);
        }
    }

    @Override
    public void updateBirthday(long id, LocalDateTime localDateTime) {
        User user = getById(id);
        if (user != null) {
            users.remove(user);
            user.setBirthDate(localDateTime);
            users.add(user);
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
            if (user.getUsername() == userName) {
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
            if (user.getUsername().equals(userName)){
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
}
