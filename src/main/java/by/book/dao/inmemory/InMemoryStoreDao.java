package by.book.dao.inmemory;

import by.book.dao.StoreDao;
import by.book.exception.DaoException;
import by.book.entity.Address;
import by.book.entity.Store;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStoreDao implements StoreDao {
    private static List<Store> stores = new ArrayList<>();
    private static long incId = 1;

    @Override
    public List<Store> getAll() {
        return stores;
    }

    @Override
    public Store getByAddress(Address address) throws DaoException {
        for (Store store : stores) {
            if (store.getAddress().equals(address)) {
                return store;
            }
        }
        throw new DaoException("Магазина с адрессом: " + address + " несуществует");
    }

    @Override
    public Store getById(long id) throws DaoException {
        for (Store store : stores) {
            if (store.getId() == id) {
                return store;
            }
        }
        throw new DaoException("Магазина с id " + id + " несуществует");
    }

    @Override
    public Store getByName(String name) throws DaoException {
        for (Store store : stores) {
            if (store.getName().equals(name)) {
                return store;
            }
        }
        throw new DaoException("Магазина с именем " + name + " несуществует");
    }

    @Override
    public void update(long id, String name) throws DaoException {
        Store store = getById(id);
        store.setName(name);
    }

    @Override
    public void update(long id, Address address) throws DaoException {
        Store store = getById(id);
        store.setAddress(address);
    }

    @Override
    public void delete(long id) throws DaoException {
        Store store = getById(id);
        stores.remove(store);
    }

    @Override
    public void save(Store store) throws DaoException {
        if (!stores.contains(store.getAddress())){
            store.setId(incId++);
            stores.add(store);
            return;
        }
        throw new DaoException("Такой магазин уже существует");
    }

    @Override
    public boolean contains(Address address){
        for (Store store : stores) {
            if (store.getAddress().equals(address)) {
                return true;
            }
        }
        return false;
    }
}
