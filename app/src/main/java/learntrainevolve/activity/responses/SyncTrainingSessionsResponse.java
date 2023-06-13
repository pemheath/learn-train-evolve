package learntrainevolve.activity.responses;


public class SyncTrainingSessionsResponse {

    private final String message;


    private SyncTrainingSessionsResponse(String message) {
        this.message = message;
    }


    public String getMessage() {return message;}

    @Override
    public String toString() {
        return "LogTrainingResponse{" +
                "message='" + message +
                '}';
    }

    public static Builder builder() {return new Builder();}
    public static class Builder {
        private String message;



        public Builder withMessage(String message) {

            this.message = message;
            return this;
        }

        public SyncTrainingSessionsResponse build() {
            return new SyncTrainingSessionsResponse(message);}
    }


}
