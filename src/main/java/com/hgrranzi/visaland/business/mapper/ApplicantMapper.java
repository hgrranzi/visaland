package com.hgrranzi.visaland.business.mapper;

import com.hgrranzi.visaland.api.dto.ApplicantDto;
import com.hgrranzi.visaland.api.dto.ApplicantInfoDto;
import com.hgrranzi.visaland.api.dto.ApplicationDto;
import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.Application;
import com.hgrranzi.visaland.persistence.entity.User;
import org.mapstruct.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Mapping(source = "applicant.firstName", target = "firstName")
    @Mapping(source = "applicant.lastName", target = "lastName")
    @Mapping(source = "applicant.dateOfBirth", target = "dateOfBirth")
    @Mapping(source = "applicant.passportNumber", target = "passportNumber")
    @Mapping(source = "applicant.city", target = "city")
    @Mapping(source = "applicant.profession", target = "profession")
    @Mapping(source = "applicant.applications", target = "lastFiveApplications", qualifiedByName = "mapApplications")
    ApplicantInfoDto entityToDto(Applicant applicant, @Context Long currentApplicationId);

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

    @Named("mapApplications")
    default List<ApplicationDto> mapApplications(List<Application> applications, @Context Long currentApplicationId) {
        return applications.stream()
                   .filter(a -> a.getStatus().isFinal() || Objects.equals(a.getId(), currentApplicationId))
                   .sorted(Comparator.comparing(Application::getApplicationDate).reversed())
                   .limit(5)
                   .map(ApplicationMapper.INSTANCE::entityToDto)
                   .collect(Collectors.toList());
    }

}
