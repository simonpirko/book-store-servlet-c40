package by.book.dao;

import by.book.entity.Address;
import by.book.entity.Store;
import by.book.entity.User;
import by.book.exception.DaoException;

import java.util.List;

public interface AddressDao {
    List<Address> getAll();
    Address getById(long id) throws DaoException;
    Address getByUser(User user);
    Address getByStore(Store store);
    void delete(long id)throws DaoException;
    void save(Address address);
    void updateStreet(long id, String street)throws DaoException;
    void updateHome(long id, int home)throws DaoException;
    boolean contains(String street, int home);

}
