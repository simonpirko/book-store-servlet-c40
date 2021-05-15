package by.book.service;

import by.book.dao.StoreDao;
import by.book.dao.inmemory.InMemoryStoreDao;
import by.book.entity.Address;
import by.book.entity.Store;
import by.book.exception.*;

import java.util.ArrayList;
import java.util.List;

public class StaffStoreService {
    private StoreDao storeDao = new InMemoryStoreDao();

    public void create(String name, String street, String house) throws InvalidRequestException, DuplicateDataException, ServerErrorException {
        validationParam(name);
        validationParam(street);
        int houseInt = validaAndTransformStringToInt(house);

        Address address = new Address(0, street.trim(), houseInt);

        try {
            storeDao.getByAddress(address);
            throw new DuplicateDataException("address");
        } catch (DaoException e) {}

        Store store = new Store(0, address, name.trim(), new ArrayList<>());

        try {
            storeDao.save(store);
        } catch (DaoException e) {
            throw new ServerErrorException();
        }
    }

    public Store getOne(String id) throws NotFoundException, InvalidRequestException {
        int idInt = validaAndTransformStringToInt(id);

        try {
            Store store = storeDao.getById(idInt);
            return store;
        } catch (DaoException e) {
            throw new NotFoundException();
        }
    }

    public void update(String id, String name, String street, String house) throws InvalidRequestException, ServerErrorException, NotFoundException {
        // Валидируем
        int idInt =  validaAndTransformStringToInt(id);
        int houseInt = validaAndTransformStringToInt(house);
        validationParam(name);
        validationParam(street);

        // Пытаемся получить магазин
        Store findStore;
        try {
            findStore = storeDao.getById(idInt);
        } catch (DaoException e) {
            throw new NotFoundException();
        }

        // Если есть изменения в адресе обновляем его
        if(!findStore.getAddress().getStreet().equals(street.trim()) || findStore.getAddress().getHome() != houseInt) {
            try {
                storeDao.update(idInt, new Address(0, street.trim(), houseInt));
            } catch (DaoException e) {
                throw new ServerErrorException();
            }
        }

        // Если есть изменения в названии обновляем его
        if(!findStore.getName().equals(name)) {
            try {
                storeDao.update(idInt, name);
            } catch (DaoException e) {
                throw new ServerErrorException();
            }
        }
    }

    public void delete(String id) throws InvalidRequestException, NotFoundException, ServerErrorException {
        int idInt =  validaAndTransformStringToInt(id);

        try {
            storeDao.getById(idInt);
        } catch (DaoException e) {
            throw new NotFoundException();
        }

        try {
            storeDao.delete(idInt);
        } catch (DaoException e) {
            throw new ServerErrorException();
        }
    }

    public List<Store> getAll() {
        return storeDao.getAll();
    }

    private void validationParam(String param) throws InvalidRequestException {
        if(param == null || param.trim() == "") throw new InvalidRequestException();
    }

    private int validaAndTransformStringToInt(String param) throws InvalidRequestException {
        if(param == null || param.trim() == "") throw new InvalidRequestException();

        int paramInt;
        try {
            paramInt = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            throw new InvalidRequestException();
        }
        return paramInt;
    }
}
