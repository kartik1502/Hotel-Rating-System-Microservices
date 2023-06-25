package org.training.ratingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewRating {

    private String rating;

    private User user;

    private Hotel hotel;

    private String feedback;
}
