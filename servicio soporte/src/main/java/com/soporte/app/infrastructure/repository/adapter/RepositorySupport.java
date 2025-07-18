package com.soporte.app.infrastructure.repository.adapter;

import com.soporte.app.aplication.out.SupportProductPort;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.infrastructure.repository.IRepositorySupportProduct;
import com.soporte.app.infrastructure.repository.entity.ProductEntity;
import com.soporte.app.infrastructure.repository.mapping.IMapperEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RepositorySupport implements SupportProductPort {
    private final IRepositorySupportProduct repositoryClient;
    private final IMapperEntity mapperProductEntity;

    @Override
    public List<SupportProduct> findAllProduct() {
        List<ProductEntity> productEntity = repositoryClient.findAll();
        return mapperProductEntity.entityListToModelList(productEntity);
    }

    @Override
    public SupportProduct findClientById(Integer id) {
      try {
          ProductEntity productEntity = repositoryClient.findById(id)
                    .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
            return mapperProductEntity.entityToModel(productEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error finding client with id: " + id, e);
      }
    }

    @Override
    public SupportProduct updateClient(SupportProduct product, Integer id) {
        if(product == null || id == null) {
            throw new IllegalArgumentException("Product and ID must not be null");
        }
        product.setId(id);
        return mapperProductEntity.entityToModel(repositoryClient.save(mapperProductEntity.modelToEntity(product)));
    }

    @Override
    public SupportProduct saveClient(SupportProduct product) {
        ProductEntity clientEntity = mapperProductEntity.modelToEntity(product);
        return mapperProductEntity.entityToModel(repositoryClient.save(clientEntity));
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
    public List<SupportProduct> findClientByName(String identificationNumber) {
        List<ProductEntity> productEntity = repositoryClient.buscarPorNombreODescripcion(identificationNumber);
        if (productEntity == null) {
            throw new RuntimeException("Client not found with name: " + identificationNumber);
        }
        return mapperProductEntity.entityListToModelList(productEntity);
    }
    @Override
    public List<SupportProduct> findProductByPrice(Float startRange, Float endRange) {
        List<ProductEntity> productEntity = repositoryClient.findProductByPrice(startRange, endRange);
        if (productEntity == null) {
            throw new RuntimeException("No products found with price: " + startRange + " to " + endRange);
        }
        return mapperProductEntity.entityListToModelList(productEntity);
    }

}
