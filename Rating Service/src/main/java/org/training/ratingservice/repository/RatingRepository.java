package org.training.ratingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.ratingservice.entity.Rating;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, String> {

    List<Rating> findRatingByHotelId(String hotelId);

    List<Rating> findRatingByUserId(String userId);
}
