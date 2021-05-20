package by.book.service;

import by.book.dao.AddressDao;
import by.book.dao.UserDao;
import by.book.dao.inmemory.InMemoryAddressDao;
import by.book.dao.inmemory.InMemoryUserDao;
import by.book.entity.Address;
import by.book.entity.Role;
import by.book.entity.User;
import by.book.exception.DaoException;
import by.book.exception.UserDataException;

import java.time.LocalDate;
import java.util.List;

public class UserService {
    private UserDao userDao = new InMemoryUserDao();
    private AddressDao addressDao = new InMemoryAddressDao();

    public void add(String userName, String firstName, String lastName, LocalDate birthDate, Address address, String password) throws UserDataException {
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

    public void changPassword(User user, String oldPassword, String newPassword, String confNewPassword)  throws UserDataException {
        if(!(user.getPassword().equals(oldPassword))){
            throw new UserDataException("Invalid old password");
        }
        if (oldPassword == null || newPassword == null || confNewPassword == null ) {
            throw new UserDataException("Fill in all the fields!");
        }
        if (newPassword.length() <= 3) {
            throw new UserDataException("Password length password then 3");
        }
        if(oldPassword.equals(newPassword)){
            throw  new UserDataException("The new password is the same as the old one");
        }
        if(!(newPassword.equals(confNewPassword))){
            throw  new UserDataException("new password confirmation entered incorrectly");
        }
        user.setPassword(newPassword);
    }

    public void changAddress(Address address, String street, int home) throws UserDataException {

        if (street == null || home <= 1) {
            throw new UserDataException("Fill in all the fields!");
        }
        if (street.equals(address.getStreet())  || home == address.getHome()) {
            throw new UserDataException("The new address is the same as the old one");
        }
            address.setStreet(street);
            address.setHome(home);
    }

    public void changPersonalData(User user, String userName, String firstName,
                                  String lastName, LocalDate birthDate) throws UserDataException{
        if(userName == null || firstName == null || lastName == null || birthDate == null){
            throw  new UserDataException("Fill in all the fields!");
        }
        if(userDao.containsByName(userName)){
            throw  new UserDataException("The new address is the same as the old one");
        }
        user.setUsername(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
    }


}
