package com.cliente.app.infrastructure.repository.mapping;

import com.cliente.app.domain.model.Client;
import com.cliente.app.infrastructure.repository.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperClientEntityImplementation {

    ClientEntity modelToEntity(Client client);
    Client entityToModel(ClientEntity entity);
    List<Client> entityListToModelList(List<ClientEntity> entityList);
}
