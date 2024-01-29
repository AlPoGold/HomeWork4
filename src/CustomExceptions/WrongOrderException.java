package CustomExceptions;

public class WrongOrderException extends RuntimeException{
    public WrongOrderException(String message) {
        super(message);
    }
}
