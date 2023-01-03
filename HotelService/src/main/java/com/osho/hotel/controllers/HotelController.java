package com.osho.hotel.controllers;

import com.osho.hotel.entities.Hotel;
import com.osho.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    @GetMapping("/get")
    public ResponseEntity<Hotel> getHotel(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(id));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Hotel>> getHotel() {
        return ResponseEntity.status(HttpStatus.OK).body(hotelService.getAll());
    }
}
