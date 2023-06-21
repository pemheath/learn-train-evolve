package learntrainevolve.exceptions;

/**
 * A custom exception thrown when no Training Session (s) match the provided request criteria, even though the
 * request contains valid parameters.
 */

public class TrainingSessionNotFoundException extends RuntimeException{

    public TrainingSessionNotFoundException() {
    }

    public TrainingSessionNotFoundException(String message) {
        super(message);
    }

    public TrainingSessionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TrainingSessionNotFoundException(Throwable cause) {
        super(cause);
    }
}
