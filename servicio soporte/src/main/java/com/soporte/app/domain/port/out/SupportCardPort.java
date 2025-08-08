package com.soporte.app.domain.port.out;
import com.soporte.app.domain.model.CardModel;

import java.util.List;
import java.util.Optional;


public interface SupportCardPort {
    public Optional<CardModel> getCardDetails(Integer cardId);
    public CardModel saveCard(Integer id, CardModel cardModel);
    public void deleteCard(Integer id);
    public List<CardModel> getAllCards();
    public List<CardModel> findByIdCustomer(Integer idClient);
    public List<CardModel> getAbandonedCarts(String status);
}
