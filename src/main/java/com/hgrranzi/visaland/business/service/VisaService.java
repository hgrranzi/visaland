package com.hgrranzi.visaland.business.service;

import com.hgrranzi.visaland.api.dto.CountryDto;
import com.hgrranzi.visaland.api.dto.VisaCategoryDto;
import com.hgrranzi.visaland.api.dto.VisaDto;
import com.hgrranzi.visaland.business.mapper.CountryMapper;
import com.hgrranzi.visaland.business.mapper.VisaCategoryMapper;
import com.hgrranzi.visaland.business.mapper.VisaMapper;
import com.hgrranzi.visaland.persistence.entity.Visa;
import com.hgrranzi.visaland.persistence.repository.CountryRepository;
import com.hgrranzi.visaland.persistence.repository.VisaCategoryRepository;
import com.hgrranzi.visaland.persistence.repository.VisaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisaService {

    private final VisaRepository visaRepository;

    private final VisaCategoryRepository visaCategoryRepository;

    private final CountryRepository countryRepository;

    private final VisaMapper visaMapper;

    private final VisaCategoryMapper visaCategoryMapper;

    private final CountryMapper countryMapper;

    public List<VisaDto> findAllVisasForApplicantWithUsername(String username) {
        List<Visa> visas = visaRepository.findAllByApplicant_User_UsernameOrderByEndDate(username);
        return visas.stream()
                   .map(visaMapper::entityToDto)
                   .collect(Collectors.toList());
    }

    public List<VisaCategoryDto> findAllCategories() {
        return visaCategoryRepository.findAll().stream()
                   .map(visaCategoryMapper::entityToDto)
                   .collect(Collectors.toList());
    }

    public List<CountryDto> findAllCountries() {
        return countryRepository.findAll().stream()
                   .map(countryMapper::entityToDto)
                   .collect(Collectors.toList());
    }
}
