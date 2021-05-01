package am.basic.springdemo.service.impl;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Card;
import am.basic.springdemo.repository.CardRepository;
import am.basic.springdemo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card add(Card card) throws ResponseException {
        ResponseException.check(cardRepository.existsByNumber(
                card.getNumber()),
                HttpStatus.CONFLICT,
                "Card with such number already exists");
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public Card update(long id, Card card, long userId) throws ResponseException {
        Card fromDb = cardRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new ResponseException(HttpStatus.NOT_FOUND, "Card with such id is not exists or you have no permission edit on it"));

        ResponseException.check(
                cardRepository.existsByNumberAndIdIsNot(card.getNumber(), id),
                HttpStatus.CONFLICT,
                "Card with such number already exists");

        fromDb.setCvc(card.getCvc());
        fromDb.setExpiration(card.getExpiration());
        fromDb.setNumber(card.getNumber());
        fromDb.setBank(card.getBank());
        fromDb.setCardHolder(card.getCardHolder());
        return fromDb;
    }

    @Override
    public void delete(long id, long userId) {
        cardRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public Page<Card> getByUserId(long userId, Pageable pageable) {
        return cardRepository.findAllByUserId(userId,pageable);
    }
}
