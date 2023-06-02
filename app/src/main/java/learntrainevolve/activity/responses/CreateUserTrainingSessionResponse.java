package learntrainevolve.activity.responses;

import learntrainevolve.models.UserTrainingSessionModel;

public class CreateUserTrainingSessionResponse {

    private final UserTrainingSessionModel userTrainingSessionModel;

    private CreateUserTrainingSessionResponse(UserTrainingSessionModel userTrainingSessionModel) {
        this.userTrainingSessionModel = userTrainingSessionModel;
    }

    public UserTrainingSessionModel getUserTrainingSession() {return userTrainingSessionModel;}

    @Override
    public String toString() {
        return "CreateUserTrainingSessionResponse{" +
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

        public CreateUserTrainingSessionResponse build() {
            return new CreateUserTrainingSessionResponse(userTrainingSessionModel);}
    }


}