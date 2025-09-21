
package com.soporte.app.infrastructure.adapter.in.web.mapping;

import com.soporte.app.domain.model.Client;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestClient;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseClient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperClientImplementation {
        ResponseClient responseClient(Client client);
        List<ResponseClient> responseClientList(List<Client> clients);
        Client responseClientToModel(RequestClient responseClient);
}


