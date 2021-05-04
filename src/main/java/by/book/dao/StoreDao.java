package by.book.dao;

import by.book.exception.DaoException;
import by.book.entity.Address;
import by.book.entity.Store;

import java.util.List;

public interface StoreDao {
    List<Store> getAll();
    Store getByAddress(Address address) throws DaoException;
    Store getById(long id) throws DaoException;
    Store getByName(String name) throws DaoException;
    void update(long id, String name) throws DaoException;
    void update(long id, Address address) throws DaoException;
    void delete(long id) throws DaoException;
    void save(Store store) throws DaoException;
    boolean contains(Address address);


}
