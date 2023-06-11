package learntrainevolve.activity.responses;

import learntrainevolve.models.UserTrainingSessionModel;

public class SyncTrainingSessionsResponse {

    private final String message;
    private final int numberOfEventsProcessed;

    private SyncTrainingSessionsResponse(String message, int numberOfEventsProcessed) {
        this.message = message;
        this.numberOfEventsProcessed = numberOfEventsProcessed;
    }


    public String getMessage() {return message;}
    public int getNumberOfEventsProcessed() {return numberOfEventsProcessed;}

    @Override
    public String toString() {
        return "LogTrainingResponse{" +
                "message='" + message + '\'' +
                "numberOfEventsProcessed=" + numberOfEventsProcessed +
                '}';
    }

    public static Builder builder() {return new Builder();}
    public static class Builder {
        private String message;

        private int numberOfEventsProcessed;

        public Builder withMessage(String message) {

            this.message = message;
            return this;
        }

        public Builder withNumberOfEventsProcessed(int numberOfEventsProcessed) {
            this.numberOfEventsProcessed = numberOfEventsProcessed;
            return this;
        }

        public SyncTrainingSessionsResponse build() {
            return new SyncTrainingSessionsResponse(message, numberOfEventsProcessed);}
    }


}
