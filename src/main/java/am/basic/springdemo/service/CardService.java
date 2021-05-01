package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Card;
import am.basic.springdemo.model.dto.CardSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardService {

    Card add(Card card) throws ResponseException;

    Card update(long id, Card card, long userId) throws ResponseException;

    void delete(long id, long userId);

    Page<Card> getByUserId(long userId, Pageable pageable);


    Page<Card> search(CardSearchDto cardSearchDto, Pageable pageable);
}
