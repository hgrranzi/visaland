package com.hgrranzi.visaland.api.dto;

import jakarta.validation.constraints.Future;
import lombok.*;
import org.hibernate.validator.constraints.Range;

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

    @Future(message = "{error.entry.date}")
    private LocalDate entryDate;

    @Range(min = 1, max = 99999, message = "{error.duration.days}")
    private Integer durationDays;

    private String status = "OPEN";

}