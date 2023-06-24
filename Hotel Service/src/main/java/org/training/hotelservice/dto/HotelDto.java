package org.training.hotelservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelDto {

    @NotNull
    @Pattern(regexp = "[a-zA-Z ]+", message = "Hotel Name should contain only alphabets")
    private String name;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9, ]+", message = "Hotel Location should contain only alphabets")
    private String location;

    @NotNull
    @Pattern(regexp = "[a-zA-z0-9, ]+", message = "About hotel field should not contain symbols")
    private String about;
}
