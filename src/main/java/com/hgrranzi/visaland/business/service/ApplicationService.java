package com.hgrranzi.visaland.business.service;

import com.hgrranzi.visaland.api.dto.ApplicationDto;
import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.Application;
import com.hgrranzi.visaland.persistence.entity.Country;
import com.hgrranzi.visaland.persistence.entity.VisaCategory;
import com.hgrranzi.visaland.business.mapper.ApplicationMapper;
import com.hgrranzi.visaland.persistence.repository.ApplicationRepository;
import com.hgrranzi.visaland.persistence.repository.CountryRepository;
import com.hgrranzi.visaland.persistence.repository.VisaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final CountryRepository countryRepository;

    private final VisaCategoryRepository visaCategoryRepository;

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    public List<ApplicationDto> findAllApplicationsForApplicant(Applicant applicant) {
        List<Application> entities = applicationRepository.findAllByApplicantOrderByApplicationDate(applicant);
        return entities.stream()
                   .map(applicationMapper::entityToDto)
                   .collect(Collectors.toList());
    }

    public void saveNewApplication(ApplicationDto applicationDto, Applicant applicant) {
        VisaCategory category = visaCategoryRepository.findByName(applicationDto.getVisaCategory());
        Country country = countryRepository.findByName(applicationDto.getCountry());

        applicationRepository.save(applicationMapper.dtoToEntity(applicationDto, applicant, category, country));
    }

}