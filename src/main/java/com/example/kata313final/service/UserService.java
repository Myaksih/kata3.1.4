package com.example.kata313final.service;



import com.example.kata313final.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    void update(User user);

    void delete(Long id);

    List<User> getAllUser();

    User show(Long id);

    User findByUsername(String name);

}
