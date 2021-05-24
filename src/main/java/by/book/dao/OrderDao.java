package by.book.dao;

import by.book.entity.*;

import java.util.List;

public interface OrderDao {

    void addBook(long id, Book book);
    void deleteBook(long id, Book book);
    List<Book> getListBook(long id);
    void save(Order order);
    void deleteById(long id);
    Order getById(long id);
    List<Order> getOrderByStore(Store store);
    List<Order> getOrderByAddress(Address address);
    List<Order> getOrderByUsername(String username);
    List<Order> getAll();
    void updateType(long id, Type type);
    void updateStatus(long id, OrderStatus status);
    boolean contains(Order order);
    boolean containsId(long id);

}
