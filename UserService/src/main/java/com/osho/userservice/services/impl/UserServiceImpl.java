package com.osho.userservice.services.impl;

import com.osho.userservice.entites.User;
import com.osho.userservice.exception.ResourceNotFoundException;
import com.osho.userservice.repositories.UserRepository;
import com.osho.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id is not found on server !! :" + userId));
        return user;
    }
}
