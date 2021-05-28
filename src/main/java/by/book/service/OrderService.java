package by.book.service;

import by.book.dao.BasketDao;
import by.book.dao.BookDao;
import by.book.dao.OrderDao;
import by.book.dao.StoreDao;
import by.book.dao.inmemory.InMemoryBasketDao;
import by.book.dao.inmemory.InMemoryBookDao;
import by.book.dao.inmemory.InMemoryOrderDao;
import by.book.dao.inmemory.InMemoryStoreDao;
import by.book.dao.postgres.PgBookDao;
import by.book.entity.*;
import by.book.exception.DaoException;
import by.book.exception.IncorrectData;
import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;


public class OrderService {
    private OrderDao orderDao = new InMemoryOrderDao();
    private BasketDao basketDao = new InMemoryBasketDao();
    private BookDao bookDao = new PgBookDao();
    private StoreDao storeDao = new InMemoryStoreDao();

    public List<Book> getListBookInBasket() {
        return basketDao.getListBookInBasket();
    }

    public void addBookInBasket(long id) {
        basketDao.addBook(bookDao.getBookById(id));
    }

    public boolean basketIsEmpty() {
        return basketDao.basketIsEmpty();
    }

    public void removeBookInBasket(long id) {
        basketDao.removeBook(bookDao.getBookById(id));
    }

    public void save(List<Book> bookList, User user, long storeId, Type type) throws InvalidRequestException, IncorrectData {
        Order order = create(bookList, user, storeId, type);
        if (!orderDao.contains(order)) {
            orderDao.save(order);
        } else {
            throw new InvalidRequestException();
        }
    }

    public void deleteById(long id) throws NotFoundException {
        if (orderDao.containsId(id)) {
            orderDao.deleteById(id);
        } else {
            throw new NotFoundException();
        }
    }

    public Order getById(long id) throws InvalidRequestException, NotFoundException {
        if (orderDao.containsId(id)) {
            return orderDao.getById(id);
        } else {
            throw new NotFoundException();
        }
    }

    public List<Order> getAll() {
        return orderDao.getAll();
    }

    public List<Order> getOrderByStore(Store store) {
        return orderDao.getOrderByStore(store);
    }

    public List<Order> getOrderByAddress(Address address) {
        return orderDao.getOrderByAddress(address);
    }

    public List<Order> getOrderByUsername(String username) {
        return orderDao.getOrderByUsername(username);
    }

    public void updateType(long id, Type type) {
        orderDao.updateType(id, type);
    }

    public void updateStatus(long id, OrderStatus status) {
        orderDao.updateStatus(id, status);
    }

    public Order create(List<Book> bookList, User user, long storeId, Type type) throws InvalidRequestException, IncorrectData {
        checkPrice(totalPrice());
        try {
            Store store = storeDao.getById(storeId);
            return new Order(0, bookList, user, user.getAddress(), totalPrice(), store, type, OrderStatus.RECEIVED, LocalDate.now());
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkPrice(int price) throws IncorrectData {
        if (price < 0)
            throw new IncorrectData();
    }

    public int totalPrice() {
        int result = 0;
        for (Book book : getListBookInBasket()) {
            result += book.getPrice();
        }
        return result;
    }
}
