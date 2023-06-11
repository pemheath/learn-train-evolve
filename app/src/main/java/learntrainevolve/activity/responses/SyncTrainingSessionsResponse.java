package learntrainevolve.activity.responses;

import learntrainevolve.models.UserTrainingSessionModel;

public class SyncTrainingSessionsResponse {

    private final UserTrainingSessionModel userTrainingSessionModel;

    private SyncTrainingSessionsResponse(UserTrainingSessionModel userTrainingSessionModel) {
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

        public SyncTrainingSessionsResponse build() {
            return new SyncTrainingSessionsResponse(userTrainingSessionModel);}
    }


}
