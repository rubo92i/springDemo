package am.basic.springdemo.model.dto;

import lombok.Data;

@Data
public class CardSearchDto {

    private String number;

    private String expiration;

    private String cvc;

    private String cardHolder;

    private String bank;

    private long userId;
}
