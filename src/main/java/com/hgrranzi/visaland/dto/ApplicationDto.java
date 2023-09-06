package com.hgrranzi.visaland.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ApplicationDto {

    private LocalDate applicationDate;

    private String visaCategory;

    private String country;

    private LocalDate entryDate;

    private int durationDays;

    private String status;

}
