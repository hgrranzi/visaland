package com.hgrranzi.visaland.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@ToString
@Getter
public class VisaDto {

    private String country;

    private String category;

    private LocalDate startDate;

    private LocalDate endDate;
}
