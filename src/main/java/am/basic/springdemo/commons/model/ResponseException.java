package am.basic.springdemo.commons.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ResponseException extends Exception {


    private HttpStatus status;


    public ResponseException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public ResponseException(HttpStatus status) {
        this.status = status;
    }


    public static void check(boolean expresion, HttpStatus status, String message) throws ResponseException {
        if (expresion) {
            throw new ResponseException(status, message);
        }
    }

    public static void check(boolean expresion, HttpStatus status) throws ResponseException {
        if (expresion) {
            throw new ResponseException(status);
        }
    }
}
