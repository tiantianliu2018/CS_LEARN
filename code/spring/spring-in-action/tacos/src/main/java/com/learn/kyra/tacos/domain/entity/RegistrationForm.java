package com.learn.kyra.tacos.domain.entity;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liutiantian
 * @create 2020-04-08 20:12
 */
@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder encoder){
        return new User(username, encoder.encode(password),
                fullname, street, city, state, zip, phone);
    }
}
