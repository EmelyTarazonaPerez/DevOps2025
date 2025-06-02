package com.cinema.app.aplication.out;

import com.cinema.app.domain.model.Client;

import java.util.List;

/**
 * Port for client repository operations.
 * This interface defines the methods for interacting with the client data store.
 */
public interface ClientRepositoryPort {
    List<Client> findAllClients();
    Client findClientById(Integer id);
    Client updateClient(Client client, Integer id);
    Client saveClient(Client client);
    String deleteClient(Integer id);
}
