package com.hgrranzi.visaland.business.service;

import com.hgrranzi.visaland.api.dto.VisaDto;
import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.Visa;
import com.hgrranzi.visaland.business.mapper.VisaMapper;
import com.hgrranzi.visaland.persistence.repository.VisaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisaService {

    private final VisaRepository visaRepository;

    private final VisaMapper visaMapper;

    public List<VisaDto> findAllVisasForApplicant(Applicant applicant) {
        List<Visa> entities = visaRepository.findAllByApplicantOrderByEndDate(applicant);
        return entities.stream()
                   .map(visaMapper::entityToDto)
                   .collect(Collectors.toList());
    }
}
