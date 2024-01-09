package com.beadando.beadando.controller;

import com.beadando.beadando.model.User;
import com.beadando.beadando.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> findAllUser() {
        return userService.findAllUsers();
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<User> findUserByName(@PathVariable String username) {
        return userService.findUserByName(username);
    }

    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
