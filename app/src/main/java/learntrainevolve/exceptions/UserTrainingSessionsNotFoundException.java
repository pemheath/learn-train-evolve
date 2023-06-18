package learntrainevolve.exceptions;



public class UserTrainingSessionsNotFoundException extends RuntimeException {
    public UserTrainingSessionsNotFoundException() {
    }

    public UserTrainingSessionsNotFoundException(String message) {
        super(message);
    }

    public UserTrainingSessionsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTrainingSessionsNotFoundException(Throwable cause) {
        super(cause);
    }
}
