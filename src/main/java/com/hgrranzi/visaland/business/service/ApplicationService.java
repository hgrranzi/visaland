package com.hgrranzi.visaland.business.service;

import com.hgrranzi.visaland.api.dto.ApplicationDto;
import com.hgrranzi.visaland.business.exception.VisalandException;
import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.Application;
import com.hgrranzi.visaland.persistence.entity.Country;
import com.hgrranzi.visaland.persistence.entity.VisaCategory;
import com.hgrranzi.visaland.business.mapper.ApplicationMapper;
import com.hgrranzi.visaland.persistence.repository.ApplicantRepository;
import com.hgrranzi.visaland.persistence.repository.ApplicationRepository;
import com.hgrranzi.visaland.persistence.repository.CountryRepository;
import com.hgrranzi.visaland.persistence.repository.VisaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final CountryRepository countryRepository;

    private final VisaCategoryRepository visaCategoryRepository;

    private final ApplicationRepository applicationRepository;

    private final ApplicantRepository applicantRepository;

    private final ApplicationMapper applicationMapper;

    public List<ApplicationDto> findAllApplicationsForApplicantWithUsername(String username) {
        List<Application> apps = applicationRepository.findAllByApplicant_User_UsernameOrderByApplicationDate(username);
        return apps.stream()
                   .map(applicationMapper::entityToDto)
                   .collect(Collectors.toList());
    }

    public void saveNewApplicationFromApplicantWithUsername(ApplicationDto applicationDto, String username) {
        Applicant applicant = applicantRepository.findByUserUsername(username).orElseThrow(
            () -> new VisalandException(HttpStatus.BAD_REQUEST, String.format("User not found with username %s",
                                                                              username)));

        Country country = countryRepository.findByName(applicationDto.getCountry()).orElseThrow(
            () -> new VisalandException(HttpStatus.BAD_REQUEST, String.format("Wrong country name %s",
                                                                              applicationDto.getCountry())));

        VisaCategory category = visaCategoryRepository.findByName(applicationDto.getVisaCategory()).orElseThrow(
            () -> new VisalandException(HttpStatus.BAD_REQUEST, String.format("Wrong category name %s",
                                                                              applicationDto.getVisaCategory())));

        if (applicationDto.getDurationDays() > category.getMaxDurationDays()) {
            throw new VisalandException(HttpStatus.BAD_REQUEST, String.format("Max duration days allowed %d",
                                                                              category.getMaxDurationDays()));
        }

        applicationRepository.save(applicationMapper.dtoToEntity(applicationDto, applicant, category, country));
    }

}