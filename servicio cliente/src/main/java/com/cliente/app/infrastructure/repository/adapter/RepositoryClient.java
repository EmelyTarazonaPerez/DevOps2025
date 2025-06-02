package com.cliente.app.infrastructure.repository.adapter;


import com.cliente.app.domain.model.Client;
import com.cliente.app.aplication.out.ClientRepositoryPort;
import com.cliente.app.infrastructure.repository.IRepositoryClient;
import com.cliente.app.infrastructure.repository.entity.ClientEntity;
import com.cliente.app.infrastructure.repository.mapping.MapperClientEntityImplementation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RepositoryClient implements ClientRepositoryPort {
    private final IRepositoryClient repositoryClient;
    private final MapperClientEntityImplementation mappingClientEntity;

    @Override
    public List<Client> findAllClients() {
        List<ClientEntity> clientEntities = repositoryClient.findAll();
        return mappingClientEntity.entityListToModelList(clientEntities);
    }

    @Override
    public Client findClientById(Integer id) {
      try {
            ClientEntity clientEntity = repositoryClient.findById(id)
                    .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
            return mappingClientEntity.entityToModel(clientEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error finding client with id: " + id, e);
      }
    }

    @Override
    public Client updateClient(Client client, Integer id) {
        return mappingClientEntity.entityToModel(repositoryClient.save(mappingClientEntity.modelToEntity(client)));
    }

    @Override
    public Client saveClient(Client client) {
        ClientEntity clientEntity = mappingClientEntity.modelToEntity(client);
        return mappingClientEntity.entityToModel(repositoryClient.save(clientEntity));
    }

    @Override
    public String deleteClient(Integer id) {
        try {
            repositoryClient.deleteById(id);
            return "Client with ID " + id + " deleted successfully.";
        } catch (Exception e) {
            throw new RuntimeException("Error deleting client with id: " + id, e);
        }
    }

    @Override
    public Client findClientByName(String identificationNumber) {
        ClientEntity clientEntity = repositoryClient.findByName(identificationNumber);
        if (clientEntity == null) {
            throw new RuntimeException("Client not found with name: " + identificationNumber);
        }
        return mappingClientEntity.entityToModel(clientEntity);
    }

}
