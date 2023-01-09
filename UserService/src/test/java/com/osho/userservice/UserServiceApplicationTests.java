package com.osho.userservice;

import com.osho.userservice.entites.Rating;
import com.osho.userservice.external.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
    }


//    @Test
//    void createRating() {
//        Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("Created Using Feign Client").build();
//        Rating savedRating = ratingService.createRating(rating);
//        System.out.println("New Rating Created");
//    }

}
