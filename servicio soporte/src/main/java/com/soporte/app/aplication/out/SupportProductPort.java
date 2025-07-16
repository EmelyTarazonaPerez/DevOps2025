package com.soporte.app.aplication.out;


import com.soporte.app.domain.model.SupportProduct;

import java.util.List;

/**
 * Port for client repository operations.
 * This interface defines the methods for interacting with the client data store.
 */
public interface SupportProductPort {
    List<SupportProduct> findAllProduct();
    SupportProduct findClientById(Integer id);
    SupportProduct updateClient(SupportProduct client, Integer id);
    SupportProduct saveClient(SupportProduct product);
    String deleteClient(Integer id);
    List<SupportProduct> findClientByName(String identificationNumber);
    List<SupportProduct> findProductByPrice(Float startRange, Float endRange);
}
