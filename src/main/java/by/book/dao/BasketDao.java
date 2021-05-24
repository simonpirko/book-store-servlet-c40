package by.book.dao;

import by.book.entity.Book;

import java.util.List;

public interface BasketDao {

    void addBook(Book book);
    void removeBook(Book book);
    List<Book> getListBookInBasket();
    boolean basketIsEmpty();
}
