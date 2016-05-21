package by.bsu.nypresent.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 22.02.16
 * Time: 13:56
 * To change this template use File | Settings | File Templates.
 */
public class ValidationException extends Exception {
    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
