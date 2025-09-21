package com.soporte.app.domain.port.out;


import com.soporte.app.domain.model.SupportProduct;

import java.util.List;


public interface SupportProductPort {
    List<SupportProduct> findAllProduct(int page, int size, String sort);
    SupportProduct findProductById(Long id);
    SupportProduct updateProduct(SupportProduct product, Long id);
    SupportProduct saveProduct(SupportProduct product);
    String deleteProduct(Integer id);
    List<SupportProduct> findProductByName(String identificationNumber);
    List<SupportProduct> findProductByPrice(Float startRange, Float endRange);
}
