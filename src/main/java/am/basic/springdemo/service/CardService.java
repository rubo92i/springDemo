package am.basic.springdemo.service;

import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Card;

import java.util.List;

public interface CardService {

    Card add(Card card) throws ResponseException;

    Card update(long id, Card card, long userId) throws ResponseException;

    void delete(long id, long userId);

    List<Card> getByUserId(long userId);


}
