package exceptions;

public class PasswordInvalidaException extends RuntimeException {
    public PasswordInvalidaException(String message) {
        super(message);
    }
}
