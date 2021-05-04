package am.basic.springdemo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PhoneSearchDto {



    private String number;

    private String model;

    private String vendor;

    @JsonIgnore
    private long userId;
}
