package learntrainevolve.activity.responses;

import learntrainevolve.models.UserTrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateUserTrainingSessionResponse {
    private final Logger log = LogManager.getLogger();


    private final UserTrainingSessionModel userTrainingSessionModel;

    private CreateUserTrainingSessionResponse(UserTrainingSessionModel userTrainingSessionModel) {
        log.info("userTrainingSessionModel is {}", userTrainingSessionModel);
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
