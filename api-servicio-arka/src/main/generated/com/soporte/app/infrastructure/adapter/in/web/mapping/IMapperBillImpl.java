package com.soporte.app.infrastructure.adapter.in.web.mapping;

import com.soporte.app.domain.model.BillModel;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestOrder;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-21T12:16:37-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class IMapperBillImpl implements IMapperBill {

    @Override
    public BillModel requestToModel(RequestOrder requestOrder) {
        if ( requestOrder == null ) {
            return null;
        }

        BillModel billModel = new BillModel();

        billModel.setDirection( requestOrder.getDirection() );
        billModel.setIdClient( requestOrder.getIdClient() );
        billModel.setIdCard( requestOrder.getIdCard() );

        billModel.setState( "PENDIENTE" );
        billModel.setSubtotal( new BigDecimal( "0.0f" ) );
        billModel.setIva( new BigDecimal( "0.0f" ) );
        billModel.setTotal( new BigDecimal( "0.0f" ) );

        return billModel;
    }
}
