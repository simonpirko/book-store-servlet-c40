package by.book.service;

import by.book.dao.UserDao;
import by.book.dao.inmemory.InMemoryUserDao;
import by.book.entity.Address;
import by.book.entity.Role;
import by.book.entity.User;
import by.book.exception.UserDataException;

import java.time.LocalDateTime;
import java.util.List;

public class UserService {
    private UserDao userDao = new InMemoryUserDao();

    public void add(String userName, String firstName, String lastName, LocalDateTime birthDate, Address address, String password) throws UserDataException {
        if (userName == null || firstName == null || lastName == null || birthDate == null || address == null || password == null) {
            throw new UserDataException("Fill in all the fields!");
        }
        if (password.length() <= 3) {
            throw new UserDataException("Password length less then 3");
        }
        if (userDao.containsByName(userName)) {
            throw new UserDataException("User already exist!");
        }
        userDao.save(new User(0, userName, firstName, lastName, birthDate, address, password, Role.USER));
    }


    public User getByUserName(String login) {
        if (userDao.containsByName(login)) {
            return userDao.getByUserName(login);
        }
        return null;
    }

    public boolean authCheck(String login, String password) {
        User user = userDao.getByUserName(login);
        if (user == null) {
            return false;
        }
        if (user.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    public List<User> get(){
        return userDao.getAll();
    }


}
