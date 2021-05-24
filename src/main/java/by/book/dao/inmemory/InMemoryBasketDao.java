package by.book.dao.inmemory;

import by.book.dao.BasketDao;
import by.book.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBasketDao implements BasketDao {
    private static List<Book> listBookInBasket = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        listBookInBasket.add(book);
    }

    @Override
    public void removeBook(Book book) {
        listBookInBasket.remove(book);
    }

    @Override
    public List<Book> getListBookInBasket() {
        return listBookInBasket;
    }

    @Override
    public boolean basketIsEmpty() {
        if (listBookInBasket.isEmpty()) {
            return false;
        }
        return true;
    }
}
