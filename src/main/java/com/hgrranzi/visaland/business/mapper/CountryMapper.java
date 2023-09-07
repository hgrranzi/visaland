package com.hgrranzi.visaland.business.mapper;

import com.hgrranzi.visaland.api.dto.CountryDto;
import com.hgrranzi.visaland.persistence.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mapping(source = "name", target = "name")
    CountryDto entityToDto(Country country);
}
