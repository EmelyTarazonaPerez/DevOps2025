package com.soporte.app.domain.servicio;

import com.soporte.app.domain.model.CardModel;
import com.soporte.app.domain.model.CardProductModel;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.infrastructure.controller.dto.request.RequestCard;

import java.util.ArrayList;
import java.util.List;

public class ServiceCardImp {

    public CardModel addProductToCard(CardModel card, SupportProduct product, Integer quantity) {

        if (card.getDetails() == null) {
            card.setDetails(new ArrayList<>());
        }
        CardProductModel newProduct = new CardProductModel();
        newProduct.setCantidad(quantity);
        newProduct.setProduct(product);
        card.getDetails().add(newProduct);
        card.setState("PENDING");
        return card;
    }

    public void stockControl(SupportProduct supportProduct, Integer quantity, boolean isDelete) {
        if (supportProduct.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock for product: " + supportProduct.getName());
        }
        if (isDelete) {
            supportProduct.setQuantity(supportProduct.getQuantity() + quantity);
        } else {
            supportProduct.setQuantity(supportProduct.getQuantity() - quantity);
        }

    }

    public CardModel findCardActive(List<CardModel> cardsByUser) {
        return cardsByUser.stream()
                .filter(x -> "ACTIVE".equalsIgnoreCase(x.getState()))
                .findFirst()
                .orElse(null);
    }

    public CardModel createCard(RequestCard requestCard) {
        CardModel activeCard = new CardModel();
        activeCard.setIdClient(requestCard.getIdClient());
        activeCard.setState("ACTIVE");
        activeCard.setUpdatedAt(new java.util.Date());
        return activeCard;
    }

    public CardModel updateDetailsCard(CardModel card, List<CardProductModel> details) {
        if (card.getDetails() == null) {
            card.setDetails(new ArrayList<>());
        }
        card.getDetails().clear();
        card.getDetails().addAll(details);
        return card;
    }
}
