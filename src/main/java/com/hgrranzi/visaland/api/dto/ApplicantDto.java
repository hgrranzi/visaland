package com.hgrranzi.visaland.api.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ApplicantDto {

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String passportNumber;

    private String phone;

    private String profession;

}
