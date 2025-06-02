package com.cinema.app.infrastructure.controller.mapping;

import com.cinema.app.domain.model.Client;
import com.cinema.app.infrastructure.controller.dto.RequestClient;
import com.cinema.app.infrastructure.controller.dto.ResponseClient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperClient {
    ResponseClient responseClient(Client client);
    List<ResponseClient> responseClientList(List<Client> clients);


    Client responseClientToModel(RequestClient responseClient);
}
