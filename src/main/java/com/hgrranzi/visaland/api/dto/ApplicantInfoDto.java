package com.hgrranzi.visaland.api.dto;

import com.hgrranzi.visaland.persistence.entity.AppStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class ApplicantInfoDto {

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String passportNumber;

    private String city;

    private String profession;

    public ApplicationDto currentApp() {
        return lastFiveApplications
                   .stream()
                   .filter(a -> a.getStatus().equals(AppStatus.PROCESSING.name()))
                   .findFirst()
                   .orElse(null);
    }


    private List<ApplicationDto> lastFiveApplications;

}
