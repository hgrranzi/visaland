package com.hgrranzi.visaland.business.mapper;

import com.hgrranzi.visaland.api.dto.ApplicationDto;
import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.Application;
import com.hgrranzi.visaland.persistence.entity.Country;
import com.hgrranzi.visaland.persistence.entity.VisaCategory;
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
        @Mapping(source = "dto.applicationDate", target = "applicationDate"),
        @Mapping(source = "dto.entryDate", target = "startDate"),
        @Mapping(source = "dto.durationDays", target = "durationDays"),
        @Mapping(target = "status",
            expression = "java(com.hgrranzi.visaland.persistence.entity.AppStatus.valueOf(dto" + ".getStatus()))"),
        @Mapping(target = "comment", ignore = true)
    })
    Application dtoToEntity(ApplicationDto dto,
                            Applicant applicant,
                            VisaCategory category,
                            Country country);

}