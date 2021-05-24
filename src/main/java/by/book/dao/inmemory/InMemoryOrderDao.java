package by.book.dao.inmemory;

import by.book.dao.OrderDao;
import by.book.entity.*;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderDao implements OrderDao {
    private List<Order> orderList = new ArrayList<>();
    private int idOrder = 1;

    @Override
    public void addBook(long id, Book book) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                order.getBookList().add(book);
            }
        }
    }

    @Override
    public void deleteBook(long id, Book book) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                order.getBookList().remove(book);
            }
        }
    }

    @Override
    public List<Book> getListBook(long id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order.getBookList();
            }
        }
        return null;
    }

    @Override
    public void save(Order order) {
        order.setId(idOrder++);
        orderList.add(order);
    }

    @Override
    public void deleteById(long id) {
        orderList.remove(getById(id));
    }

    @Override
    public Order getById(long id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getOrderByStore(Store store) {
        List<Order> listOrderByStore = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getStore().equals(store)) {
                listOrderByStore.add(order);
            }
        }
        return listOrderByStore;
    }

    @Override
    public List<Order> getOrderByAddress(Address address) {
        List<Order> listOrderByAddress = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getAddress().equals(address)) {
                listOrderByAddress.add(order);
            }
        }
        return listOrderByAddress;
    }

    @Override
    public List<Order> getOrderByUsername(String username) {
        List<Order> listOrderByUser = new ArrayList<>();
        for (Order order : orderList) {
            if (order.getUser().getUsername() == username) {
                listOrderByUser.add(order);
            }
        }
        return listOrderByUser;
    }

    @Override
    public List<Order> getAll() {
        return orderList;
    }

    @Override
    public void updateType(long id, Type type) {
        Order order = getById(id);
        order.setType(type);
    }

    @Override
    public void updateStatus(long id, OrderStatus status) {
        Order order = getById(id);
        order.setStatus(status);
    }

    @Override
    public boolean contains(Order order) {
        for (Order order1 : orderList) {
            if (order1.getUser().equals(order.getUser())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsId(long id) {
        for (Order order : orderList) {
            if (order.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
