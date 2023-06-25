package org.training.ratingservice.service.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.training.ratingservice.dto.*;
import org.training.ratingservice.entity.Rating;
import org.training.ratingservice.repository.RatingRepository;
import org.training.ratingservice.service.RatingService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Value("${spring.application.responseCode}")
    private String responseCode;

    @Autowired
    private RestTemplate template;

    @Override
    public Response addRating(RatingDto ratingDto) {

        Rating rating = new Rating();
        BeanUtils.copyProperties(ratingDto, rating);
        rating.setRatingId(UUID.randomUUID().toString());
        ratingRepository.save(rating);
        return new Response(responseCode, "Thank you for your valuable rating !!!");
    }

    @Override
    public List<RatingDto> getAllRatings() {

        return ratingRepository.findAll().stream().map(rating -> {
            RatingDto ratingDto = new RatingDto();
            BeanUtils.copyProperties(rating, ratingDto);
            return ratingDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ViewRating> getAllByUserId(String userId) {

        User user = template.getForObject("http://localhost:8080/users/"+userId, User.class);

        return ratingRepository.findRatingByUserId(userId).stream().map(rating -> {

            Hotel hotel = template.getForObject("http://localhost:8081/hotels/"+rating.getHotelId(), Hotel.class);
            ViewRating ratingDto = ViewRating.builder().feedback(rating.getFeedback()).rating(rating.getRating()).user(user).hotel(hotel).build();
            return ratingDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<RatingDto> getAllByHotelId(String hotelId) {

        return ratingRepository.findRatingByHotelId(hotelId).stream().map(rating -> {
            RatingDto ratingDto = new RatingDto();
            BeanUtils.copyProperties(rating, ratingDto);
            return ratingDto;
        }).collect(Collectors.toList());
    }
}
