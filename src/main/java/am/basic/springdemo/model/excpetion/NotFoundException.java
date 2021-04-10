package am.basic.springdemo.model.excpetion;

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
