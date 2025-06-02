package com.cinema.app.infrastructure.repository.mapping;

import com.cinema.app.domain.model.Client;
import com.cinema.app.infrastructure.repository.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperClientEntity {

    ClientEntity modelToEntity(Client client);
    Client entityToModel(ClientEntity entity);
    List<Client> entityListToModelList(List<ClientEntity> entityList);
}
