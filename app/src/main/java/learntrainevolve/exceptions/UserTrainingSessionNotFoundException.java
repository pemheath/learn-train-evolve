package learntrainevolve.exceptions;

/**
 * A custom exception thrown when no User Training Session (s) match the provided request criteria, even though the
 * request contains valid parameters.
 */

public class UserTrainingSessionNotFoundException extends RuntimeException {
    public UserTrainingSessionNotFoundException() {
    }

    public UserTrainingSessionNotFoundException(String message) {
        super(message);
    }

    public UserTrainingSessionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTrainingSessionNotFoundException(Throwable cause) {
        super(cause);
    }
}
