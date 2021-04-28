package by.book.dao;

import by.book.entity.Address;
import by.book.entity.Store;

import java.util.List;

public interface StoreDao {
    List<Store> getAll();
    Store getByAddress(Address address);
    Store getById(long id);
    Store getByName(String name);
    void update(long id, String name);
    void update(long id, Address address);
    void delete(long id);
    void save(Store store);
    boolean contains(Address address);


}
