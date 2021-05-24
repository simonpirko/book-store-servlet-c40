package by.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private List<Book> bookList;
    private User user;
    private Address address;
    private int totalPrice;
    private Store store;
    private Type type;
    private OrderStatus status;
    private LocalDate orderDate;
}
