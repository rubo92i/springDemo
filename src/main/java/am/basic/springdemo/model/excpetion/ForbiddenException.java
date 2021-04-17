package am.basic.springdemo.model.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends Exception {


    public ForbiddenException(String message) {
        super(message);
    }


    public static void check(boolean expression,String message) throws ForbiddenException {
        if (expression){
            throw new ForbiddenException(message);
        }
    }
}
