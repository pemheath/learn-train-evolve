package learntrainevolve.exceptions;

/**
 * A custom exception class that extends the FailedExternalAPICallException
 * This exception is thrown when an AWS exception occurs with accessing the secrets from Secrets Manager
 * */
public class FailedSecretsAccessException extends FailedExternalAPICallException {
    public FailedSecretsAccessException() {
    }

    public FailedSecretsAccessException(String message) {
        super(message);
    }

    public FailedSecretsAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedSecretsAccessException(Throwable cause) {
        super(cause);
    }
}
