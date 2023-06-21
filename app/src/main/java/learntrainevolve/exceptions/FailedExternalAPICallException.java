package learntrainevolve.exceptions;

/**
 * A custom exception class to capture exceptions that occur in the process of making external API calls.
 * */
public class FailedExternalAPICallException extends RuntimeException {
    public FailedExternalAPICallException() {
    }

    public FailedExternalAPICallException(String message) {
        super(message);
    }

    public FailedExternalAPICallException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedExternalAPICallException(Throwable cause) {
        super(cause);
    }
}
