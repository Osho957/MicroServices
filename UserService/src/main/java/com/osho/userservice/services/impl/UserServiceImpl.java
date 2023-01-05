package com.osho.userservice.services.impl;

import com.osho.userservice.entites.Hotel;
import com.osho.userservice.entites.Rating;
import com.osho.userservice.entites.User;
import com.osho.userservice.exception.ResourceNotFoundException;
import com.osho.userservice.repositories.UserRepository;
import com.osho.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.rating}")
    private String RATING_SERVICE;

    @Value("${service.hotel}")
    private String HOTEL_SERVICE;

    @Override
    public User saveUser(User user) {
        String randomId = UUID.randomUUID().toString();
        user.setUserId(randomId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
           getUserRating(user);
        }
        return users;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user with given id is not found on server !! :" + userId));
        getUserRating(user);
        return user;
    }

    public void getUserRating(User user) {
        Rating[] ratings = restTemplate.getForObject(RATING_SERVICE + user.getUserId(), Rating[].class);
        List<Rating> ratingsOfUser = Arrays.stream(ratings).toList();
        List<Rating> ratingList = ratingsOfUser.stream().map(rating -> {
            ResponseEntity<Hotel> response = restTemplate.getForEntity(HOTEL_SERVICE + rating.getHotelId(), Hotel.class);
            Hotel hotel = response.getBody();
            logger.info("Response Status {} ", response.getStatusCode());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRating(ratingList);
    }
}
