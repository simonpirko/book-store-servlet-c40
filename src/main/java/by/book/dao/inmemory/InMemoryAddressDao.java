package by.book.dao.inmemory;

import by.book.dao.AddressDao;
import by.book.entity.Address;
import by.book.entity.Store;
import by.book.entity.User;
import by.book.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAddressDao implements AddressDao {
    private static List<Address> listAddress = new ArrayList<>();
    private static long addressId = 0;

    @Override
    public List<Address> getAll() {
        return new ArrayList<>(listAddress);
    }

    @Override
    public Address getById(long id) throws DaoException {
        for (Address address : listAddress) {
            if (address.getId() == id) {
                return address;
            }
        }
        throw new DaoException("Адреса с таким id не существует");
    }

    @Override
    public Address getByUser(User user) {
        for (Address address : listAddress) {
            if (address.equals(user.getAddress())) {
                return address;
            }
        }
        return null;
    }

    @Override
    public Address getByStore(Store store) {
        for (Address address : listAddress) {
            if (address.equals(store.getAddress())) {
                return address;
            }
        }
        return null;
    }

    @Override
    public void delete(long id) throws DaoException {
        listAddress.remove(getById(id));
    }

    @Override
    public void save(Address address) {
        if (!contains(address.getStreet(), address.getHome())) {
            address.setId(addressId++);
            listAddress.add(address);
        }
    }

    @Override
    public void updateStreet(long id, String street) throws DaoException {
        Address address = getById(id);
        address.setStreet(street);
    }

    @Override
    public void updateHome(long id, int home) throws DaoException {
        Address address = getById(id);
        address.setHome(home);
    }

    @Override
    public boolean contains(String street, int home) {
        for (Address address : listAddress) {
            if (address.getStreet().equals(street) && address.getHome() == home) {
                return true;
            }
        }
        return false;
    }
}