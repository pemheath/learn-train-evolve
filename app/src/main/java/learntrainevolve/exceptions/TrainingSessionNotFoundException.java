package learntrainevolve.exceptions;

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
