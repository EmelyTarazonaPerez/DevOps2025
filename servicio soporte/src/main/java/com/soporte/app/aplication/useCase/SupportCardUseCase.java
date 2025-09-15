package com.soporte.app.aplication.useCase;

import com.soporte.app.domain.model.CardModel;
import com.soporte.app.domain.model.CardProductModel;
import com.soporte.app.domain.model.SupportProduct;
import com.soporte.app.domain.port.out.SupportCardPort;
import com.soporte.app.domain.port.out.SupportProductPort;
import com.soporte.app.domain.servicio.ServiceCardImp;
import com.soporte.app.infrastructure.adapter.in.web.dto.response.BodyResponse;
import com.soporte.app.infrastructure.adapter.in.web.dto.request.RequestCard;
import com.soporte.app.utils.ConstantsMessages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class SupportCardUseCase {

    private final SupportCardPort supportCardPort;
    private final ServiceCardImp serviceCardImp;
    private final SupportProductPort supportProductPort;


    public BodyResponse getAllCards() {
        List<CardModel> cards = supportCardPort.getAllCards();
        return new BodyResponse(ConstantsMessages.STATUS_OK, "Fetched all cards successfully", cards);
    }

    public BodyResponse getCardById(RequestCard requestCard) {
        Optional<CardModel> cardFind = supportCardPort.getCardDetails(requestCard.getIdCard());
        return cardFind.map(cardModel ->
                new BodyResponse(ConstantsMessages.STATUS_OK, "Fetched card successfully", cardModel)).orElseGet(() ->
                new BodyResponse(ConstantsMessages.STATUS_NO_FOUND, "Card not found", cardFind));
    }

    public BodyResponse saveCard(RequestCard requestCard) {
        List<CardModel> cardsByUser = supportCardPort.findByIdCustomer(requestCard.getIdClient());
        SupportProduct supportProduct = supportProductPort.findProductById(Long.valueOf(requestCard.getIdProduct()));

        serviceCardImp.stockControl(supportProduct, requestCard.getQuantity(), false);
        CardModel activeCard = serviceCardImp.findCardActive(cardsByUser);

        if (activeCard == null) {
            activeCard = serviceCardImp.createCard(requestCard);
            activeCard = supportCardPort.saveCard(null, activeCard);
        }

        serviceCardImp.addProductToCard(activeCard, supportProduct, requestCard.getQuantity());
        supportCardPort.saveCard(activeCard.getId(), activeCard);

        return new BodyResponse(ConstantsMessages.STATUS_OK, "Card saved successfully", activeCard);
    }

    public BodyResponse deleteCard(Integer id) {
        CardModel cardModel = supportCardPort.getCardDetails(id)
                .orElseThrow(() -> new RuntimeException("Card not found"));

        List<CardProductModel> details = cardModel.getDetails();
        for (CardProductModel detail : details) {
            SupportProduct product = detail.getProduct();
            serviceCardImp.stockControl(product, detail.getCantidad(), true);
            supportProductPort.updateProduct(product, (long) Math.toIntExact(product.getId()));
        }
        cardModel.setState("ABANDONED");
        supportCardPort.saveCard(cardModel.getId(), cardModel);
        return new BodyResponse(ConstantsMessages.STATUS_OK, "Card deleted successfully", null);
    }

    public BodyResponse updateCard(RequestCard requestCard) {
        CardModel cardModel = supportCardPort.getCardDetails(requestCard.getIdCard())
                .orElseThrow(() -> new RuntimeException("Card not found"));

        List<CardProductModel> existingDetails = cardModel.getDetails();
        Optional<CardProductModel> existingProductOpt = existingDetails.stream()
                .filter(x -> x.getProduct().getId().equals(requestCard.getIdProduct()))
                .findFirst();

        if (existingProductOpt.isEmpty()) {
            throw new RuntimeException("Product not found in the card");
        }

        existingProductOpt.get().setCantidad(requestCard.getQuantity());

        CardModel updatedCard = supportCardPort.saveCard(cardModel.getId(), cardModel);
        return new BodyResponse(ConstantsMessages.STATUS_OK, "Card updated successfully", updatedCard);
    }

    public List<BodyResponse> getAbandonedCarts () {
        List<CardModel> abandonedCarts = supportCardPort.getAbandonedCarts("ABANDONED");
        return abandonedCarts.stream()
                .map(card -> new BodyResponse(ConstantsMessages.STATUS_OK, "Abandoned cart found", card))
                .toList();
    }





}
