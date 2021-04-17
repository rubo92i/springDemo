package am.basic.springdemo.model.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends Exception {


    public NotFoundException(String message) {
        super(message);
    }


    public static void check(boolean expression,String message) throws NotFoundException {
        if (expression){
            throw new NotFoundException(message);
        }
    }
}
