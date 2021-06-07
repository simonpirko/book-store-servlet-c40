package by.book.service;

import by.book.dao.OrderDao;
import by.book.dao.StoreDao;
import by.book.dao.inmemory.InMemoryOrderDao;
import by.book.dao.inmemory.InMemoryStoreDao;
import by.book.entity.*;
import by.book.exception.IncorrectData;
import by.book.exception.InvalidRequestException;
import by.book.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;


public class OrderService {
    private OrderDao orderDao = new InMemoryOrderDao();
    private StoreDao storeDao = new InMemoryStoreDao();

    public void save(List<Book> bookList, User user, Address address, int totalPrice, Type type) throws InvalidRequestException, IncorrectData {
        Order order = create(bookList, user, address, totalPrice, type);
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

    public Order create(List<Book> bookList, User user, Address address, int totalPrice, Type type) {
        return new Order(0, bookList, user, address, totalPrice, type, OrderStatus.RECEIVED, LocalDate.now());
    }
}
