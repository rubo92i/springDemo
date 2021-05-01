package am.basic.springdemo.controller;


import am.basic.springdemo.commons.model.ResponseException;
import am.basic.springdemo.config.SecurityContextProvider;
import am.basic.springdemo.model.Card;
import am.basic.springdemo.model.annoatations.SuccessResponseBody;
import am.basic.springdemo.model.dto.CardSearchDto;
import am.basic.springdemo.model.dto.PageResponse;
import am.basic.springdemo.service.CardService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
            @ApiResponse(code = 200, message = "This mean that card successfully added"),
            @ApiResponse(code = 409, message = "This mean that card with such number already exists")

    })
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Card add(@RequestBody @Valid Card card) throws ResponseException {
        card.setUserId(securityContextProvider.getByAuthentication().getId());
        return cardService.add(card);
    }

    @PutMapping("/{id}")
    public Card update(@PathVariable long id, @RequestBody @Valid Card card) throws ResponseException {
        return cardService.update(id, card, securityContextProvider.getByAuthentication().getId());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) throws ResponseException {
        cardService.delete(id, securityContextProvider.getByAuthentication().getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public @SuccessResponseBody(HttpStatus.OK) PageResponse<Card> getByUserId(@PageableDefault(size = 20) Pageable pageable) throws ResponseException {
        Page<Card> cardPage = cardService.getByUserId(securityContextProvider.getByAuthentication().getId(), pageable);
        return new PageResponse<>(cardPage);
    }

    @GetMapping("/search")
    public  PageResponse<Card> search(@RequestBody CardSearchDto cardSearchDto, @PageableDefault(size = 20) Pageable pageable) throws ResponseException {
        Page<Card> cardPage = cardService.search(cardSearchDto, pageable);
        return new PageResponse<>(cardPage);
    }

}
