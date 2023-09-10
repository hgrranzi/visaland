package com.hgrranzi.visaland.business.mapper;

import com.hgrranzi.visaland.api.dto.ConsulDto;
import com.hgrranzi.visaland.persistence.entity.Consul;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ConsulMapper {

    @Mappings({
        @Mapping(source = "user.username", target = "username"),
        @Mapping(source = "firstName", target = "firstName"),
        @Mapping(source = "lastName", target = "lastName"),
    })
    ConsulDto entityToDto(Consul consul);
}
