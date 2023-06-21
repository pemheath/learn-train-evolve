package learntrainevolve.exceptions;

/**
 * A custom exception that gets thrown when a request made to the application is invalid.
 * This can mean the request is missing required attributes or has incorrectly formatted its required attributes, leading
 * to failure to process the request.
 */

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
    }

    public InvalidRequestException(String message) {
        super(message);
    }

    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestException(Throwable cause) {
        super(cause);
    }

}
