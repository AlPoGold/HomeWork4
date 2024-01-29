package CustomExceptions;

public class WrongCustomerException extends RuntimeException {

    public WrongCustomerException(String message) {
        super(message);
    }
}
