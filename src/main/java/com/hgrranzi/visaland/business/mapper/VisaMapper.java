package com.hgrranzi.visaland.business.mapper;

import com.hgrranzi.visaland.api.dto.VisaDto;
import com.hgrranzi.visaland.persistence.entity.Visa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VisaMapper {

    @Mappings({
        @Mapping(source = "country", target = "country"),
        @Mapping(source = "category", target = "category"),
        @Mapping(source = "startDate", target = "startDate"),
        @Mapping(source = "endDate", target = "endDate"),
    })
    VisaDto entityToDto(Visa application);
}
