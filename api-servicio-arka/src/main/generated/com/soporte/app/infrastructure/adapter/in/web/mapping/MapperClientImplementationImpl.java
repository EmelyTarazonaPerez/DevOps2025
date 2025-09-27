package com.soporte.app.infrastructure.adapter.in.web.mapping;

import com.soporte.app.domain.model.Client;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestClient;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseClient;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class MapperClientImplementationImpl implements MapperClientImplementation {

    @Override
    public ResponseClient responseClient(Client client) {
        if ( client == null ) {
            return null;
        }

        ResponseClient responseClient = new ResponseClient();

        responseClient.setId( client.getId() );
        responseClient.setName( client.getName() );
        responseClient.setLastName( client.getLastName() );
        responseClient.setAge( client.getAge() );
        responseClient.setPhone( client.getPhone() );
        responseClient.setIdentificationNumber( client.getIdentificationNumber() );
        responseClient.setIdentificationType( client.getIdentificationType() );

        return responseClient;
    }

    @Override
    public List<ResponseClient> responseClientList(List<Client> clients) {
        if ( clients == null ) {
            return null;
        }

        List<ResponseClient> list = new ArrayList<ResponseClient>( clients.size() );
        for ( Client client : clients ) {
            list.add( responseClient( client ) );
        }

        return list;
    }

    @Override
    public Client responseClientToModel(RequestClient responseClient) {
        if ( responseClient == null ) {
            return null;
        }

        String name = null;
        String lastName = null;
        String age = null;
        String phone = null;
        String identificationNumber = null;
        String identificationType = null;

        name = responseClient.getName();
        lastName = responseClient.getLastName();
        age = responseClient.getAge();
        phone = responseClient.getPhone();
        identificationNumber = responseClient.getIdentificationNumber();
        identificationType = responseClient.getIdentificationType();

        Integer id = null;

        Client client = new Client( id, name, lastName, age, phone, identificationNumber, identificationType );

        return client;
    }
}
