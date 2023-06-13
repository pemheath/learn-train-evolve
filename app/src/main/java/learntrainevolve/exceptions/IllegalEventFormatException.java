package learntrainevolve.exceptions;

public class IllegalEventFormatException extends RuntimeException{
    public IllegalEventFormatException() {
    }

    public IllegalEventFormatException(String message) {
        super(message);
    }

    public IllegalEventFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEventFormatException(Throwable cause) {
        super(cause);
    }
}
