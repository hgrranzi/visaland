package com.hgrranzi.visaland.business.mapper;

import com.hgrranzi.visaland.api.dto.ApplicantDto;
import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ApplicantMapper {

    @Mappings({
        @Mapping(source = "user.username", target = "username"),
        @Mapping(source = "user.email", target = "email"),
        @Mapping(target = "password", ignore = true),
        @Mapping(source = "firstName", target = "firstName"),
        @Mapping(source = "lastName", target = "lastName"),
        @Mapping(source = "dateOfBirth", target = "dateOfBirth"),
        @Mapping(source = "passportNumber", target = "passportNumber"),
        @Mapping(source = "city", target = "city"),
        @Mapping(source = "profession", target = "profession")
    })
    ApplicantDto entityToDto(Applicant applicant);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(source = "user", target = "user"),
        @Mapping(source = "applicant.firstName", target = "firstName"),
        @Mapping(source = "applicant.lastName", target = "lastName"),
        @Mapping(source = "applicant.dateOfBirth", target = "dateOfBirth"),
        @Mapping(source = "applicant.passportNumber", target = "passportNumber"),
        @Mapping(source = "applicant.city", target = "city"),
        @Mapping(source = "applicant.profession", target = "profession")
    })
    Applicant dtoToEntity(ApplicantDto applicant, User user);
}
