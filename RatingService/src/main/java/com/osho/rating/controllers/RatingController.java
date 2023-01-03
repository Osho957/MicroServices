package com.osho.rating.controllers;

import com.osho.rating.entities.Rating;
import com.osho.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping("/get")
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    @GetMapping("/get/user")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@RequestParam String userId) {
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    @GetMapping("/get/hotel")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@RequestParam String hotelId) {
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}
