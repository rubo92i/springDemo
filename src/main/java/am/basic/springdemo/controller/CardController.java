package am.basic.springdemo.controller;


import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.config.SecurityContextProvider;
import am.basic.springdemo.model.Card;
import am.basic.springdemo.model.dto.PageResponse;
import am.basic.springdemo.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private SecurityContextProvider securityContextProvider;


    @PostMapping
    @ApiOperation(value = "Add card")
    @ApiResponses({
            @ApiResponse(code = 200,message = "This mean that card successfully added"),
            @ApiResponse(code = 409,message = "This mean that card with such number already exists")

    })
    public ResponseEntity<Card> add(@RequestBody @Valid   Card card) throws ResponseException {
        card.setUserId(securityContextProvider.getByAuthentication().getId());
        return ResponseEntity.ok(cardService.add(card));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@PathVariable long id, @RequestBody @Valid Card card) throws ResponseException {
        return ResponseEntity.ok(cardService.update(id, card, securityContextProvider.getByAuthentication().getId()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) throws ResponseException {
        cardService.delete(id, securityContextProvider.getByAuthentication().getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponse<Card>> getByUserId(@PageableDefault(size = 20) Pageable pageable) throws ResponseException {
        Page<Card> cardPage = cardService.getByUserId(securityContextProvider.getByAuthentication().getId(),pageable);
        return ResponseEntity.ok(new PageResponse<>(cardPage));
    }

}
