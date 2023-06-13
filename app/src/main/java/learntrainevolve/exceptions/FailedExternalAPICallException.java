package learntrainevolve.exceptions;



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
