package com.hgrranzi.visaland.service;

import com.hgrranzi.visaland.dto.VisaDto;
import com.hgrranzi.visaland.entity.Applicant;
import com.hgrranzi.visaland.entity.Visa;
import com.hgrranzi.visaland.mapper.VisaMapper;
import com.hgrranzi.visaland.repository.VisaRepository;
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
