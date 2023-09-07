package com.hgrranzi.visaland.business.mapper;

import com.hgrranzi.visaland.api.dto.VisaCategoryDto;
import com.hgrranzi.visaland.persistence.entity.VisaCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VisaCategoryMapper {

    @Mappings({
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "maxDurationDays", target = "maxDurationDays")
    })
    VisaCategoryDto entityToDto(VisaCategory visaCategory);
}
