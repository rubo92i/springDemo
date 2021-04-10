package am.basic.springdemo.model.excpetion;

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
