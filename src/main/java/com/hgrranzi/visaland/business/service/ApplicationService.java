package com.hgrranzi.visaland.business.service;

import com.hgrranzi.visaland.api.dto.ApplicationDto;
import com.hgrranzi.visaland.business.exception.VisalandException;
import com.hgrranzi.visaland.persistence.entity.*;
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

import static java.lang.String.format;

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

    public List<ApplicationDto> findAllApplicationsWithStatus(AppStatus status) {
        List<Application> apps = applicationRepository.findAllByStatusOrderByApplicationDate(status);
        return apps.stream()
                   .map(applicationMapper::entityToDto)
                   .collect(Collectors.toList());
    }

    public ApplicationDto findApplicationById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(
            () -> new VisalandException(HttpStatus.BAD_REQUEST, format("No application with id %d found", id)));

        return applicationMapper.entityToDto(application);
    }

    public void saveNewApplicationFromApplicantWithUsername(ApplicationDto applicationDto, String username) {
        Applicant applicant = applicantRepository.findByUserUsername(username).orElseThrow(
            () -> new VisalandException(HttpStatus.BAD_REQUEST, format("User not found with username %s",
                                                                       username)));

        Country country = countryRepository.findByName(applicationDto.getCountry()).orElseThrow(
            () -> new VisalandException(HttpStatus.BAD_REQUEST, format("Wrong country name %s",
                                                                       applicationDto.getCountry())));

        VisaCategory category = visaCategoryRepository.findByName(applicationDto.getVisaCategory()).orElseThrow(
            () -> new VisalandException(HttpStatus.BAD_REQUEST, format("Wrong category name %s",
                                                                       applicationDto.getVisaCategory())));

        if (applicationDto.getDurationDays() > category.getMaxDurationDays()) {
            throw new VisalandException(HttpStatus.BAD_REQUEST, format("Max duration days allowed %d",
                                                                       category.getMaxDurationDays()));
        }

        applicationRepository.save(applicationMapper.dtoToEntity(applicationDto, applicant, category, country));
    }

}