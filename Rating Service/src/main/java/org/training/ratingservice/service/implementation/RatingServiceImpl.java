package org.training.ratingservice.service.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.training.ratingservice.dto.RatingDto;
import org.training.ratingservice.dto.Response;
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
    public List<RatingDto> getAllByUserId(String userId) {
        return ratingRepository.findRatingByUserId(userId).stream().map(rating -> {
            RatingDto ratingDto = new RatingDto();
            BeanUtils.copyProperties(rating, ratingDto);
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
