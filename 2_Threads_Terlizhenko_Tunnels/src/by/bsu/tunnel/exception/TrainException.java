package by.bsu.tunnel.exception;

/**
 * Created with IntelliJ IDEA.
 * User: Irina
 * Date: 01.03.16
 * Time: 5:39
 * To change this template use File | Settings | File Templates.
 */
public class TrainException extends Exception {
    public TrainException(Throwable cause) {
        super(cause);
    }

    public TrainException(String message) {
        super(message);
    }

    public TrainException(String message, Throwable cause) {
        super(message, cause);
    }
}
