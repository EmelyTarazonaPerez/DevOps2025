package com.cliente.app.aplication.useCase;

import com.cliente.app.domain.model.Client;
import com.cliente.app.aplication.out.ClientRepositoryPort;
import com.cliente.app.infrastructure.controller.dto.ResponseClient;
import com.cliente.app.infrastructure.repository.IRepositoryClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientUseCase {

    private final ClientRepositoryPort clientRepositoryPort;

    public List<ResponseClient> getAllClients() {
        List<Client>  clients = clientRepositoryPort.findAllClients();
        if (clients == null || clients.isEmpty()) {
            throw new RuntimeException("No clients found");
        }
        List<ResponseClient> clientList = clients.stream()
                .map(client -> {
                    ResponseClient responseClient = new ResponseClient();
                    responseClient.setId(client.getId());
                    responseClient.setName(client.getName());
                    responseClient.setLastName(client.getLastName());
                    responseClient.setPhone(client.getPhone());
                    responseClient.setAge(client.getAge());
                    responseClient.setIdentificationType(client.getIdentificationType());
                    responseClient.setIdentificationNumber(client.getIdentificationNumber());
                    return responseClient;
                })
                .collect(Collectors.toList());
        return clientList;
    }
    public ResponseClient getClientById(Integer id) {
        Client client = clientRepositoryPort.findClientById(id);
        ResponseClient responseClient = new ResponseClient();
        if (client == null) {
            throw new RuntimeException("Client not found with id: " + id);
        }
        responseClient.setId(client.getId());
        responseClient.setName(client.getName());
        responseClient.setLastName(client.getLastName());
        responseClient.setPhone(client.getPhone());
        responseClient.setAge(client.getAge());
        responseClient.setIdentificationType(client.getIdentificationType());
        responseClient.setIdentificationNumber(client.getIdentificationNumber());

        return responseClient;
    }
    public Client addClient(Client clientDetails) {
        if (clientDetails == null) {
            throw new IllegalArgumentException("Client details cannot be null");
        }
        return  clientRepositoryPort.saveClient(clientDetails);
    }
    public ResponseClient updateClient(Integer id, Client clientDetails) {
        if (id == null || clientDetails == null) {
            throw new IllegalArgumentException("Client ID and details cannot be null");
        }
        clientDetails.setId(id);

        ResponseClient responseClient = new ResponseClient();
        Client clientUpdate = clientRepositoryPort.updateClient(clientDetails, id);
        responseClient.setAge(clientUpdate.getAge());
        responseClient.setId(clientUpdate.getId());
        responseClient.setIdentificationNumber(clientUpdate.getIdentificationNumber());
        responseClient.setIdentificationType(clientUpdate.getIdentificationType());
        responseClient.setLastName(clientUpdate.getLastName());
        responseClient.setName(clientUpdate.getName());
        responseClient.setPhone(clientUpdate.getPhone());
        return responseClient;
    }
    public String deleteClient(Integer id) {
    if (id == null) {
            throw new IllegalArgumentException("Client ID cannot be null");
        }
        return clientRepositoryPort.deleteClient(id);
    }

    public Client filterClientByName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }
        return clientRepositoryPort.findClientByName(name);
    }

    public List<ResponseClient> getClientsByOrderAsc(boolean orderAsc) {
    List<Client> clients = clientRepositoryPort.findAllClients();
        if (clients == null || clients.isEmpty()) {
            throw new RuntimeException("No clients found");
        }
        if (orderAsc) {
            clients.sort((c1, c2) -> c1.getName().compareTo(c2.getName()));
        } else {
            clients.sort((c1, c2) -> c2.getName().compareTo(c1.getName()));
        }
        return clients.stream()
                .map(client -> {
                    ResponseClient responseClient = new ResponseClient();
                    responseClient.setId(client.getId());
                    responseClient.setName(client.getName());
                    responseClient.setLastName(client.getLastName());
                    responseClient.setPhone(client.getPhone());
                    responseClient.setAge(client.getAge());
                    responseClient.setIdentificationType(client.getIdentificationType());
                    responseClient.setIdentificationNumber(client.getIdentificationNumber());
                    return responseClient;
                })
                .collect(Collectors.toList());
    }
}
