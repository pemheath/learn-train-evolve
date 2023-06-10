package learntrainevolve.activity.responses;

import learntrainevolve.models.UserTrainingSessionModel;

public class LogTrainingResponse {

    private final UserTrainingSessionModel userTrainingSessionModel;

    private LogTrainingResponse(UserTrainingSessionModel userTrainingSessionModel) {
        this.userTrainingSessionModel = userTrainingSessionModel;
    }

    public UserTrainingSessionModel getUserTrainingSession() {return userTrainingSessionModel;}

    @Override
    public String toString() {
        return "LogTrainingResponse{" +
                "userTrainingSessionModel=" + userTrainingSessionModel +
                '}';
    }

    public static Builder builder() {return new Builder();}
    public static class Builder {
        private UserTrainingSessionModel userTrainingSessionModel;

        public Builder withUserTrainingSessionModel(UserTrainingSessionModel userTrainingSessionModel) {
            this.userTrainingSessionModel = userTrainingSessionModel;
            return this;
        }

        public LogTrainingResponse build() {
            return new LogTrainingResponse(userTrainingSessionModel);}
    }


}
