package learntrainevolve.activity.responses;

import learntrainevolve.models.TrainingSessionModel;
import learntrainevolve.models.UserTrainingSessionModel;

import java.util.List;

public class GetUserTrainingSessionsResponse {

    private final List<UserTrainingSessionModel> userTrainingSessionModelList;

    private GetUserTrainingSessionsResponse(List<UserTrainingSessionModel> userTrainingSessionModelList) {
        this.userTrainingSessionModelList = userTrainingSessionModelList;
    }

    public List<UserTrainingSessionModel> GetUserTrainingSessionModelList(){
        return userTrainingSessionModelList;
    }

    @Override
    public String toString() {
        return "GetUserTrainingSessionsResponse{" +
                "trainingSessionModelList=" + userTrainingSessionModelList +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {

        private List<UserTrainingSessionModel> userTrainingSessionModelList;

        public Builder withUserTrainingSessionModelList(List<UserTrainingSessionModel> userTrainingSessionModelList) {
            System.out.println("building response");
            this.userTrainingSessionModelList = userTrainingSessionModelList;
            return this;
        }

        public GetUserTrainingSessionsResponse build() {
            return new GetUserTrainingSessionsResponse(userTrainingSessionModelList);
        }

    }

}
