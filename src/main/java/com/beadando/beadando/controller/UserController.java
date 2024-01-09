package com.beadando.beadando.controller;

import com.beadando.beadando.model.Car;
import com.beadando.beadando.model.Company;
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

//    @PostMapping("/save")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public String saveUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }


    @GetMapping("/save")
    public String createUser(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "users_create";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        user.setRoles("USER");
        userService.saveUser(user);

        return "redirect:/";
    }
}
