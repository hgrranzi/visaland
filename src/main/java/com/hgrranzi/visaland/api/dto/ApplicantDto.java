package com.hgrranzi.visaland.api.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ApplicantDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "{error.validation.username}")
    private String username;

    @Email(message = "{error.validation.email}")
    private String email;

    @Size(min = 6, max = 50, message = "{error.validation.password}")
    private String password;

    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "{error.validation.Name}")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]{2,50}$", message = "{error.validation.Name}")
    private String lastName;

    @PastOrPresent(message = "{error.validation.DateOfBirth}")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "^\\d{10}$", message = "{error.validation.passportNumber}")
    private String passportNumber;

    @Pattern(regexp = "^\\d{10}$", message = "{error.validation.phone}")
    private String phone;

    @Pattern(regexp = "^[a-zA-Z]{2,50}$|^$", message = "{error.validation.profession}")
    private String profession;

}