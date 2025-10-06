package com.example.arka.adapters.driving.http.mapper;

import com.example.arka.adapters.driving.http.dto.UserResponse;
import com.example.arka.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserResponseMapper {
    @Mappings(value = {
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "emailAddress", target = "emailAddress"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "role", target = "role")
    })
    UserResponse toUserResponse (Usuario user);
}
