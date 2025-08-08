package com.soporte.app.domain.port.out;


import com.soporte.app.domain.model.SupportProduct;

import java.util.List;


public interface SupportProductPort {
    List<SupportProduct> findAllProduct();
    SupportProduct findProductById(Integer id);
    SupportProduct updateProduct(SupportProduct product, Integer id);
    SupportProduct saveProduct(SupportProduct product);
    String deleteProduct(Integer id);
    List<SupportProduct> findProductByName(String identificationNumber);
    List<SupportProduct> findProductByPrice(Float startRange, Float endRange);
}
