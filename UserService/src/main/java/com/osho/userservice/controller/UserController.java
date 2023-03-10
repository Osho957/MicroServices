package com.osho.userservice.controller;

import com.osho.userservice.entites.User;
import com.osho.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
       User savedUser = userService.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

}
