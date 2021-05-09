package by.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDateTime birthDate;
    private Address address;
    private String password;
    private Role role;
}
