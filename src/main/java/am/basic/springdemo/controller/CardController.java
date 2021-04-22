package am.basic.springdemo.controller;


import am.basic.springdemo.SecurityContextProvider;
import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.model.Card;
import am.basic.springdemo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private SecurityContextProvider securityContextProvider;


    @PostMapping
    public ResponseEntity<Card> add(@RequestBody @Valid Card card) throws ResponseException {
        card.setUserId(securityContextProvider.getByAuthentication().getId());
        return ResponseEntity.ok(cardService.add(card));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> add(@PathVariable long id, @RequestBody @Valid Card card) throws ResponseException {
        return ResponseEntity.ok(cardService.update(id, card, securityContextProvider.getByAuthentication().getId()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> add(@PathVariable long id) throws ResponseException {
        cardService.delete(id, securityContextProvider.getByAuthentication().getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<Card>> add() throws ResponseException {
        return ResponseEntity.ok(cardService.getByUserId(securityContextProvider.getByAuthentication().getId()));
    }

}
