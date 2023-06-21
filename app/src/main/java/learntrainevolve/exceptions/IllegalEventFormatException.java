package learntrainevolve.exceptions;

/**
 * A custom exception class that extends the FailedExternalAPICallException
 * This exception is thrown when an event returned from aclling the Google Calendar API lacks the criteria
 * for this application to translate the event into a Training Session object.
 * These required attributes are start time, summary,
 * */

public class IllegalEventFormatException extends FailedExternalAPICallException{
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
