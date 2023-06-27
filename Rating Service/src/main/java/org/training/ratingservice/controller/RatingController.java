package org.training.ratingservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.ratingservice.dto.RatingDto;
import org.training.ratingservice.dto.Response;
import org.training.ratingservice.dto.ViewRating;
import org.training.ratingservice.service.RatingService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping
    public ResponseEntity<Response> addRating(@RequestBody @Valid RatingDto ratingDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.addRating(ratingDto));
    }

    @GetMapping
    public ResponseEntity<List<RatingDto>> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/users/{userId}")
    @Retry(name = "userHotelRetry", fallbackMethod = "fallBackUserHotelBreaker")
    public ResponseEntity<List<ViewRating>> getAllRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getAllByUserId(userId));
    }

    public ResponseEntity<List<ViewRating>> fallBackUserHotelBreaker(String userId, Exception ex){

        logger.info("Fallback method executed since any of the service in down", ex.getMessage());
        List<ViewRating> viewRatings = new ArrayList<>();
        ViewRating rating = ViewRating.builder()
                .feedback("One or more Services are down")
                .build();
        viewRatings.add(rating);
        return ResponseEntity.ok(viewRatings);
    }


    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<RatingDto>> getAllRatingByHotelId(@PathVariable String hotelId){
        return ResponseEntity.ok(ratingService.getAllByHotelId(hotelId));
    }
}
