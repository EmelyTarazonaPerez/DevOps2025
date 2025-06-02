
package com.cliente.app.infrastructure.controller.mapping;

import com.cliente.app.domain.model.Client;
import com.cliente.app.infrastructure.controller.dto.RequestClient;
import com.cliente.app.infrastructure.controller.dto.ResponseClient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperClientImplementation {
        ResponseClient responseClient(Client client);
        List<ResponseClient> responseClientList(List<Client> clients);


        Client responseClientToModel(RequestClient responseClient);
}


