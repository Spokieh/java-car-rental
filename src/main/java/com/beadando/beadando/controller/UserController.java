package com.beadando.beadando.controller;

import com.beadando.beadando.model.User;
import com.beadando.beadando.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
//@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/all")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> findAllUser() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/list")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN')")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("user", new User());
        return "users";
    }

    @GetMapping("/users/{username}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public Optional<User> findUserByName(@PathVariable String username) {
        return userService.findUserByName(username);
    }

//    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public String saveUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }


    @GetMapping("/users/save")
    public String createUser(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "users_create";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute("user") User user) {
        user.setRoles("USER");
        userService.saveUser(user);

        return "redirect:/users/list";
    }

    @GetMapping("/users/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);

        if (user == null) {
            return "redirect:/users/list";
        }

        model.addAttribute("user", user);
        return "users_edit";
    }

    @PostMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateUser(@PathVariable Long id,
                            @ModelAttribute("user") User user,
                            Model model) {

        User eu = userService.getUserById(id);

        if (eu == null) {
            return "redirect:/users/list";
        }

        eu.setId(id);
        eu.setName(user.getName());
        eu.setPassword(user.getPassword());
        eu.setRoles(user.getRoles());



        userService.updateUser(user);
        return "redirect:/users/list";
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);

        return "redirect:/users/list";

    }
}
