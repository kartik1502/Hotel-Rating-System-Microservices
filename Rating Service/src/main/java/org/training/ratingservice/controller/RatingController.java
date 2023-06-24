package org.training.ratingservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.ratingservice.dto.RatingDto;
import org.training.ratingservice.dto.Response;
import org.training.ratingservice.service.RatingService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Response> addRating(@RequestBody @Valid RatingDto ratingDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.addRating(ratingDto));
    }

    @GetMapping
    public ResponseEntity<List<RatingDto>> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<RatingDto>> getAllRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getAllByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<RatingDto>> getAllRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getAllByHotelId(hotelId));
    }
}
