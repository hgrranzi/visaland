package com.hgrranzi.visaland.api.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class VisaCategoryDto {

    private String name;

    private int maxDurationDays;
}
