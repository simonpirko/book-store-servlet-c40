package by.book.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    private long id;
    private Address address;
    private String name;
    private List<Order> orders;

}
