package am.basic.springdemo.commons.model;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse {

    private Date timestamp;

    private int status;

    private String error;

    private String message;

    private String path;


}
