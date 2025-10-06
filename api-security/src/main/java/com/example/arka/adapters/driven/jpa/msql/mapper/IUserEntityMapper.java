package com.example.arka.adapters.driven.jpa.msql.mapper;


import com.example.arka.adapters.driven.jpa.msql.entity.UserEntity;
import com.example.arka.domain.model.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {
    @Mappings(value = {
            @Mapping(source = "userName", target = "userName"),
            @Mapping(source = "emailAddress", target = "emailAddress"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "role", target = "role")
    })
    Usuario toUser(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity toUserEntity(Usuario user);
}
