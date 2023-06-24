package org.training.ratingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_ratings")
public class Rating {

    @Id
    private String ratingId;

    private String rating;

    private String userId;

    private String hotelId;

    private String feedback;
}
