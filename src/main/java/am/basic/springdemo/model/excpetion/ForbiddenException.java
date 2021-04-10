package am.basic.springdemo.model.excpetion;

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
