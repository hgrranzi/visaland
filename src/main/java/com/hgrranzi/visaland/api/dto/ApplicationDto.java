package com.hgrranzi.visaland.api.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ApplicationDto {

    private LocalDate applicationDate = LocalDate.now();

    private String visaCategory;

    private String country;

    private LocalDate entryDate;

    private int durationDays;

    private String status = "OPEN";

}
