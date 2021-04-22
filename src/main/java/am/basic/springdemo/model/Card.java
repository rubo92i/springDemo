package am.basic.springdemo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Entity
public class Card {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Size(min = 12, max = 12)
    private String number;

    @NotEmpty
    @Size(min = 6, max = 6)
    private String expiration;

    @NotEmpty
    @Size(min = 3, max = 3)
    private String cvc;

    @NotEmpty
    @Size(min = 2)
    private String cardHolder;

    @NotEmpty
    @Size(min = 1)
    private String bank;

    @JsonIgnore
    private long userId;



}
