package com.example.reactiveweb.controller;

import com.example.reactiveweb.entity.Users;
import com.example.reactiveweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public Flux<Users> getAll() {
        return userService.getAllUses();
    }

    @PostMapping()
    public Mono<Users> saveUser(@RequestBody Mono<Users> user) {
        return userService.saveUser(user);
    }
}
