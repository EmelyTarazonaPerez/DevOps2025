package com.soporte.app.infrastructure.adapter.out.repository.mapping;

import com.soporte.app.domain.model.BillModel;
import com.soporte.app.domain.model.CardModel;
import com.soporte.app.domain.model.CardProductModel;
import com.soporte.app.domain.model.CategoryModel;
import com.soporte.app.domain.model.SupplierModel;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.infrastructure.adapter.out.repository.entity.BillEntity;
import com.soporte.app.infrastructure.adapter.out.repository.entity.CardEntity;
import com.soporte.app.infrastructure.adapter.out.repository.entity.CardProduct;
import com.soporte.app.infrastructure.adapter.out.repository.entity.CategoryEntity;
import com.soporte.app.infrastructure.adapter.out.repository.entity.ProductEntity;
import com.soporte.app.infrastructure.adapter.out.repository.entity.SupplierEntity;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-20T13:43:36-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Oracle Corporation)"
)
@Component
public class IMapperEntityImpl implements IMapperEntity {

    private final DatatypeFactory datatypeFactory;

    public IMapperEntityImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public ProductEntity modelToEntity(SupportProduct product) {
        if ( product == null ) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        if ( product.getId() != null ) {
            productEntity.setId( product.getId().intValue() );
        }
        productEntity.setName( product.getName() );
        productEntity.setCode( product.getCode() );
        productEntity.setQuantity( product.getQuantity() );
        if ( product.getUnitPrice() != null ) {
            productEntity.setUnitPrice( product.getUnitPrice().floatValue() );
        }
        if ( product.getSupplierId() != null ) {
            productEntity.setSupplierId( Integer.parseInt( product.getSupplierId() ) );
        }
        productEntity.setCreationDate( xmlGregorianCalendarToLocalDateTime( localDateToXmlGregorianCalendar( product.getCreationDate() ) ) );
        if ( product.getCategory() != null ) {
            productEntity.setCategory( Integer.parseInt( product.getCategory() ) );
        }

        return productEntity;
    }

    @Override
    public SupportProduct entityToModel(ProductEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SupportProduct supportProduct = new SupportProduct();

        if ( entity.getId() != null ) {
            supportProduct.setId( entity.getId().longValue() );
        }
        supportProduct.setName( entity.getName() );
        supportProduct.setCode( entity.getCode() );
        supportProduct.setQuantity( entity.getQuantity() );
        if ( entity.getUnitPrice() != null ) {
            supportProduct.setUnitPrice( BigDecimal.valueOf( entity.getUnitPrice() ) );
        }
        if ( entity.getSupplierId() != null ) {
            supportProduct.setSupplierId( String.valueOf( entity.getSupplierId() ) );
        }
        if ( entity.getCategory() != null ) {
            supportProduct.setCategory( String.valueOf( entity.getCategory() ) );
        }
        supportProduct.setCreationDate( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( entity.getCreationDate() ) ) );

        return supportProduct;
    }

    @Override
    public List<SupportProduct> entityListToModelList(List<ProductEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<SupportProduct> list = new ArrayList<SupportProduct>( entityList.size() );
        for ( ProductEntity productEntity : entityList ) {
            list.add( entityToModel( productEntity ) );
        }

        return list;
    }

    @Override
    public CardModel entityToModel(CardEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CardModel cardModel = new CardModel();

        cardModel.setId( entity.getId() );
        cardModel.setState( entity.getState() );
        cardModel.setIdClient( entity.getIdClient() );
        cardModel.setUpdatedAt( entity.getUpdatedAt() );
        cardModel.setDetails( cardProductListToCardProductModelList( entity.getDetails() ) );

        return cardModel;
    }

    @Override
    public CardEntity entityToModel(CardModel model) {
        if ( model == null ) {
            return null;
        }

        CardEntity cardEntity = new CardEntity();

        cardEntity.setId( model.getId() );
        cardEntity.setState( model.getState() );
        cardEntity.setIdClient( model.getIdClient() );
        cardEntity.setUpdatedAt( model.getUpdatedAt() );
        cardEntity.setDetails( cardProductModelListToCardProductList( model.getDetails() ) );

        return cardEntity;
    }

    @Override
    public BillEntity entityToModel(BillModel entity) {
        if ( entity == null ) {
            return null;
        }

        BillEntity billEntity = new BillEntity();

        billEntity.setDirection( entity.getDirection() );
        billEntity.setIdClient( entity.getIdClient() );
        billEntity.setIdCard( entity.getIdCard() );
        billEntity.setState( entity.getState() );
        if ( entity.getSubtotal() != null ) {
            billEntity.setSubtotal( entity.getSubtotal().floatValue() );
        }
        if ( entity.getIva() != null ) {
            billEntity.setIva( entity.getIva().floatValue() );
        }
        if ( entity.getTotal() != null ) {
            billEntity.setTotal( entity.getTotal().floatValue() );
        }

        billEntity.setDate( java.time.LocalDateTime.now() );

        return billEntity;
    }

    @Override
    public BillModel entityToModel(BillEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BillModel billModel = new BillModel();

        billModel.setDirection( entity.getDirection() );
        billModel.setIdClient( entity.getIdClient() );
        billModel.setState( entity.getState() );
        billModel.setIdCard( entity.getIdCard() );
        if ( entity.getSubtotal() != null ) {
            billModel.setSubtotal( BigDecimal.valueOf( entity.getSubtotal() ) );
        }
        if ( entity.getIva() != null ) {
            billModel.setIva( BigDecimal.valueOf( entity.getIva() ) );
        }
        if ( entity.getTotal() != null ) {
            billModel.setTotal( BigDecimal.valueOf( entity.getTotal() ) );
        }
        billModel.setDate( entity.getDate() );

        return billModel;
    }

    @Override
    public SupplierModel entityToSupplierModel(SupplierEntity entity) {
        if ( entity == null ) {
            return null;
        }

        SupplierModel supplierModel = new SupplierModel();

        supplierModel.setId( entity.getId() );
        supplierModel.setName( entity.getName() );
        supplierModel.setPhone( entity.getPhone() );
        supplierModel.setEmail( entity.getEmail() );

        return supplierModel;
    }

    @Override
    public SupplierEntity modelToSupplierEntity(SupplierModel model) {
        if ( model == null ) {
            return null;
        }

        SupplierEntity supplierEntity = new SupplierEntity();

        supplierEntity.setId( model.getId() );
        supplierEntity.setName( model.getName() );
        supplierEntity.setPhone( model.getPhone() );
        supplierEntity.setEmail( model.getEmail() );

        return supplierEntity;
    }

    @Override
    public CategoryModel entityToCategoryModel(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setId( entity.getId() );
        categoryModel.setName( entity.getName() );

        return categoryModel;
    }

    @Override
    public CategoryEntity modelToCategoryEntity(CategoryModel model) {
        if ( model == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( model.getId() );
        categoryEntity.setName( model.getName() );

        return categoryEntity;
    }

    private XMLGregorianCalendar localDateToXmlGregorianCalendar( LocalDate localDate ) {
        if ( localDate == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendarDate(
            localDate.getYear(),
            localDate.getMonthValue(),
            localDate.getDayOfMonth(),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }

    private static LocalDateTime xmlGregorianCalendarToLocalDateTime( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        if ( xcal.getYear() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMonth() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getDay() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getHour() != DatatypeConstants.FIELD_UNDEFINED
            && xcal.getMinute() != DatatypeConstants.FIELD_UNDEFINED
        ) {
            if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED
                && xcal.getMillisecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond(),
                    Duration.ofMillis( xcal.getMillisecond() ).getNano()
                );
            }
            else if ( xcal.getSecond() != DatatypeConstants.FIELD_UNDEFINED ) {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute(),
                    xcal.getSecond()
                );
            }
            else {
                return LocalDateTime.of(
                    xcal.getYear(),
                    xcal.getMonth(),
                    xcal.getDay(),
                    xcal.getHour(),
                    xcal.getMinute()
                );
            }
        }
        return null;
    }

    protected CardProductModel cardProductToCardProductModel(CardProduct cardProduct) {
        if ( cardProduct == null ) {
            return null;
        }

        CardProductModel cardProductModel = new CardProductModel();

        cardProductModel.setCantidad( cardProduct.getCantidad() );
        cardProductModel.setProduct( entityToModel( cardProduct.getProduct() ) );

        return cardProductModel;
    }

    protected List<CardProductModel> cardProductListToCardProductModelList(List<CardProduct> list) {
        if ( list == null ) {
            return null;
        }

        List<CardProductModel> list1 = new ArrayList<CardProductModel>( list.size() );
        for ( CardProduct cardProduct : list ) {
            list1.add( cardProductToCardProductModel( cardProduct ) );
        }

        return list1;
    }

    protected CardProduct cardProductModelToCardProduct(CardProductModel cardProductModel) {
        if ( cardProductModel == null ) {
            return null;
        }

        CardProduct cardProduct = new CardProduct();

        cardProduct.setProduct( modelToEntity( cardProductModel.getProduct() ) );
        cardProduct.setCantidad( cardProductModel.getCantidad() );

        return cardProduct;
    }

    protected List<CardProduct> cardProductModelListToCardProductList(List<CardProductModel> list) {
        if ( list == null ) {
            return null;
        }

        List<CardProduct> list1 = new ArrayList<CardProduct>( list.size() );
        for ( CardProductModel cardProductModel : list ) {
            list1.add( cardProductModelToCardProduct( cardProductModel ) );
        }

        return list1;
    }
}
