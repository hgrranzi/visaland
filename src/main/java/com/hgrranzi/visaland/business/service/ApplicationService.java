package com.hgrranzi.visaland.business.service;

import com.hgrranzi.visaland.api.dto.ApplicantInfoDto;
import com.hgrranzi.visaland.api.dto.ApplicationDto;
import com.hgrranzi.visaland.business.exception.VisalandException;
import com.hgrranzi.visaland.business.mapper.ApplicantMapper;
import com.hgrranzi.visaland.persistence.entity.*;
import com.hgrranzi.visaland.business.mapper.ApplicationMapper;
import com.hgrranzi.visaland.persistence.repository.*;
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

    private final ConsulRepository consulRepository;

    private final ApplicantMapper applicantMapper;

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

    public ApplicantInfoDto pinApplicationToConsul(Long appid, String consulUsername) {
        Application application = applicationRepository.findById(appid).orElseThrow(
                () -> new VisalandException(HttpStatus.BAD_REQUEST, format("No application with id %d found", appid)));
        Consul consul = consulRepository.findByUserUsername(consulUsername).orElseThrow(
                () -> new VisalandException(HttpStatus.BAD_REQUEST, format("No consul with username %s found", consulUsername))
        );
        application.setConsul(consul);
        application.setStatus(AppStatus.PROCESSING);
        applicationRepository.save(application);

        return applicantMapper.entityToDto(application.getApplicant(), appid);
    }

}