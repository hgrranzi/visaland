package com.hgrranzi.visaland.mapper;

import com.hgrranzi.visaland.dto.ApplicationDto;
import com.hgrranzi.visaland.entity.Applicant;
import com.hgrranzi.visaland.entity.Application;
import com.hgrranzi.visaland.entity.Country;
import com.hgrranzi.visaland.entity.VisaCategory;
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

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(source = "applicant", target = "applicant"),
        @Mapping(source = "category", target = "category"),
        @Mapping(source = "country", target = "country"),
        @Mapping(target = "consul", ignore = true),
        @Mapping(source = "applicationDto.applicationDate", target = "applicationDate"),
        @Mapping(source = "applicationDto.entryDate", target = "startDate"),
        @Mapping(source = "applicationDto.durationDays", target = "durationDays"),
        @Mapping(target = "status",
            expression = "java(com.hgrranzi.visaland.entity.ApplicationStatus.valueOf(applicationDto.getStatus()))"),
        @Mapping(target = "comment", ignore = true)
    })
    Application dtoToEntity(ApplicationDto applicationDto,
                            Applicant applicant,
                            VisaCategory category,
                            Country country);

}