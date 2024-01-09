package com.beadando.beadando.service;

import com.beadando.beadando.model.Car;
import com.beadando.beadando.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findUserByName(String name);
    String saveUser(User user);


    User updateUser(User user);
    void deleteUserById(Long id);

    User getUserById(Long id);
}
