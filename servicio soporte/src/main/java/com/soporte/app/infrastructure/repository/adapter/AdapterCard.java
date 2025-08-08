package com.soporte.app.infrastructure.repository.adapter;

import com.soporte.app.domain.model.CardModel;
import com.soporte.app.domain.port.out.SupportCardPort;
import com.soporte.app.infrastructure.repository.IRepositorySupportCard;
import com.soporte.app.infrastructure.repository.entity.CardEntity;
import com.soporte.app.infrastructure.repository.mapping.IMapperEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdapterCard implements SupportCardPort {

    private final IRepositorySupportCard repositorySupportCard;
    private final IMapperEntity mapperEntity;

    @Override
    public Optional<CardModel> getCardDetails(Integer id) {
        Optional<CardEntity> entityOptional = repositorySupportCard.findById(id);
        return entityOptional.map(mapperEntity::entityToModel);
    }

    @Override
    public CardModel saveCard(Integer id, CardModel cardModel) {
        CardEntity cardEntity = repositorySupportCard.save(mapperEntity.entityToModel(cardModel));
        return mapperEntity.entityToModel(cardEntity);
    }

    @Override
    public void deleteCard(Integer id) {
        return;
    }

    @Override
    public List<CardModel> getAllCards() {
       return repositorySupportCard.findAll().stream()
                .map(mapperEntity::entityToModel)
                .toList();
    }

    @Override
    public List<CardModel> findByIdCustomer(Integer idClient) {
        return repositorySupportCard.findByCardByIdCustomer(idClient)
                .stream()
                .map(mapperEntity::entityToModel)
                .toList();
    }

    @Override
    public List<CardModel> getAbandonedCarts(String status) {
        return repositorySupportCard.getAbandonedCarts(status)
                .stream()
                .map(mapperEntity::entityToModel)
                .toList();
    }

}
