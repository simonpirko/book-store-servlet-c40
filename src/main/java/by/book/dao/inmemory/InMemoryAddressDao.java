package by.book.dao.inmemory;

import by.book.dao.AddressDao;
import by.book.entity.Address;
import by.book.entity.Store;
import by.book.entity.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAddressDao implements AddressDao {
    private static List<Address> listAddress = new ArrayList<>();
    private static long addressId = 1;

    @Override
    public List<Address> getAll() {
        return new ArrayList<>(listAddress);
    }

    @Override
    public Address getById(long id) {
        for (Address address : listAddress) {
            if (address.getId() == id) {
                return address;
            }
        }
        throw new RuntimeException("Address with this id is not exist");
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
    public void delete(long id) {
        listAddress.remove(getById(id));
    }

    @Override
    public void save(Address address) {
        if (!contains(address)) {
            address.setId(addressId++);
            listAddress.add(address);
        }
    }

    @Override
    public void updateStreet(long id, String street) {
        for (Address address : listAddress) {
            if (address.getId() == id) {
                address.setStreet(street);
            }
        }
    }

    @Override
    public void updateHome(long id, int home) {
        for (Address address : listAddress) {
            if (address.getId() == id) {
                address.setHome(home);
            }
        }
    }

    @Override
    public boolean contains(Address isAddress) {
        for (Address address : listAddress) {
            if (address.equals(isAddress)) {
                return true;
            }
        }
        return false;
    }
}