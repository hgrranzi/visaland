package com.hgrranzi.visaland.mapper;

import com.hgrranzi.visaland.dto.ApplicationDto;
import com.hgrranzi.visaland.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mappings({
        @Mapping(source = "applicationDate", target = "applicationDate"),
        @Mapping(source = "category.name", target = "visaCategory"),
        @Mapping(source = "country.name", target = "country"),
        @Mapping(source = "startDate", target = "entryDate"),
        @Mapping(source = "durationDays", target = "durationDays"),
        @Mapping(target = "status", expression = "java(application.getStatus().name())")
    })
    ApplicationDto entityToDto(Application application);

}