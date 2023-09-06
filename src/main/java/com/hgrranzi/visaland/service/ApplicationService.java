package com.hgrranzi.visaland.service;

import com.hgrranzi.visaland.dto.ApplicationDto;
import com.hgrranzi.visaland.entity.Applicant;
import com.hgrranzi.visaland.entity.Application;
import com.hgrranzi.visaland.mapper.ApplicationMapper;
import com.hgrranzi.visaland.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final ApplicationMapper applicationMapper;

    public List<ApplicationDto> findAllApplicationsForApplicant(Applicant applicant) {
        List<Application> entities = applicationRepository.findAllByApplicant(applicant);
        return entities.stream()
                   .map(applicationMapper::entityToDto)
                   .collect(Collectors.toList());
    }

}
