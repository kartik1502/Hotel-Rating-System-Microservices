package org.training.ratingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDto {

    @NotNull
    private String rating;

    @NotNull
    private String userId;

    @NotNull
    private String hotelId;

    private String feedback;
}
