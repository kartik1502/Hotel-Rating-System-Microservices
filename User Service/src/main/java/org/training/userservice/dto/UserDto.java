package org.training.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @NotNull
    @Pattern(regexp = "[A-Za-z ]+", message = "First name should contain only the alphabets")
    private String firstName;

    @NotNull
    @Pattern(regexp = "[A-Za-z ]+", message = "Last name should contain only the alphabets")
    private String lastName;

    @NotNull
    @Email
    private String emailId;

    @NotNull
    @Pattern(regexp = "[6-9][0-9]{9}", message = "Contact number should be of 10 digits")
    private String contactNo;
}
