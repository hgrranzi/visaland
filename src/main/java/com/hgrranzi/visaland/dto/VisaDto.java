package com.hgrranzi.visaland.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@ToString
@Getter
public class VisaDto {

    private String country;

    private String category;

    private Date startDate;

    private Date endDate;
}
