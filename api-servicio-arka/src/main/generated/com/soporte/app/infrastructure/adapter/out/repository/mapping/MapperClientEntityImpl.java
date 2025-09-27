package com.soporte.app.infrastructure.adapter.out.repository.mapping;

import com.soporte.app.domain.model.Client;
import com.soporte.app.infrastructure.adapter.out.repository.entity.ClientEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class MapperClientEntityImpl implements MapperClientEntity {

    @Override
    public ClientEntity modelToEntity(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setId( client.getId() );
        clientEntity.setName( client.getName() );
        clientEntity.setLastName( client.getLastName() );
        clientEntity.setAge( client.getAge() );
        clientEntity.setPhone( client.getPhone() );
        clientEntity.setIdentificationNumber( client.getIdentificationNumber() );
        clientEntity.setIdentificationType( client.getIdentificationType() );

        return clientEntity;
    }

    @Override
    public Client entityToModel(ClientEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String lastName = null;
        String age = null;
        String phone = null;
        String identificationNumber = null;
        String identificationType = null;

        id = entity.getId();
        name = entity.getName();
        lastName = entity.getLastName();
        age = entity.getAge();
        phone = entity.getPhone();
        identificationNumber = entity.getIdentificationNumber();
        identificationType = entity.getIdentificationType();

        Client client = new Client( id, name, lastName, age, phone, identificationNumber, identificationType );

        return client;
    }

    @Override
    public List<Client> entityListToModelList(List<ClientEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>( entityList.size() );
        for ( ClientEntity clientEntity : entityList ) {
            list.add( entityToModel( clientEntity ) );
        }

        return list;
    }
}
