package com.soporte.app.infrastructure.adapter.in.web.mapping;

import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestSupportProduct;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.ResponseSupportProduct;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-14T23:43:57-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class IMapperProductImpl implements IMapperProduct {

    @Override
    public ResponseSupportProduct responseSupportProduct(SupportProduct supportProduct) {
        if ( supportProduct == null ) {
            return null;
        }

        ResponseSupportProduct responseSupportProduct = new ResponseSupportProduct();

        responseSupportProduct.setId( supportProduct.getId() );
        responseSupportProduct.setName( supportProduct.getName() );
        responseSupportProduct.setCode( supportProduct.getCode() );
        responseSupportProduct.setQuantity( supportProduct.getQuantity() );
        responseSupportProduct.setUnitPrice( supportProduct.getUnitPrice() );
        responseSupportProduct.setSupplierId( supportProduct.getSupplierId() );
        responseSupportProduct.setCategory( supportProduct.getCategory() );

        return responseSupportProduct;
    }

    @Override
    public List<ResponseSupportProduct> responseSupportProductList(List<SupportProduct> supportProducts) {
        if ( supportProducts == null ) {
            return null;
        }

        List<ResponseSupportProduct> list = new ArrayList<ResponseSupportProduct>( supportProducts.size() );
        for ( SupportProduct supportProduct : supportProducts ) {
            list.add( responseSupportProduct( supportProduct ) );
        }

        return list;
    }

    @Override
    public SupportProduct requestToModel(RequestSupportProduct responseClient) {
        if ( responseClient == null ) {
            return null;
        }

        SupportProduct supportProduct = new SupportProduct();

        supportProduct.setName( responseClient.getName() );
        supportProduct.setCode( responseClient.getCode() );
        if ( responseClient.getQuantity() != null ) {
            supportProduct.setQuantity( Integer.parseInt( responseClient.getQuantity() ) );
        }
        supportProduct.setUnitPrice( responseClient.getUnitPrice() );
        supportProduct.setSupplierId( responseClient.getSupplierId() );

        return supportProduct;
    }
}
