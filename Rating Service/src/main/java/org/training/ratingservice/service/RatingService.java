package org.training.ratingservice.service;

import org.training.ratingservice.dto.RatingDto;
import org.training.ratingservice.dto.Response;

import java.util.List;

public interface RatingService {

    Response addRating(RatingDto ratingDto);

    List<RatingDto> getAllRatings();

    List<RatingDto> getAllByUserId(String userId);

    List<RatingDto> getAllByHotelId(String hotelId);
}
