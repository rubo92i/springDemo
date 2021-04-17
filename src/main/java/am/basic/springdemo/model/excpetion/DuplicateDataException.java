package am.basic.springdemo.model.excpetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateDataException extends Exception {


    public DuplicateDataException(String message) {
        super(message);
    }


    public static void check(boolean expression,String message) throws DuplicateDataException {
        if (expression){
            throw new DuplicateDataException(message);
        }
    }
}
