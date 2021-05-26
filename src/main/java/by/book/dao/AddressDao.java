package by.book.dao;

import by.book.entity.Address;
import by.book.entity.Store;
import by.book.entity.User;

import java.util.List;

public interface AddressDao {
    List<Address> getAll();
    Address getById(long id);
    Address getByUser(User user);
    Address getByStore(Store store);
    void delete(long id);
    void save(Address address);
    void updateStreet(long id, String street);
    void updateHome(long id, int home);
    boolean contains(Address address);

}
