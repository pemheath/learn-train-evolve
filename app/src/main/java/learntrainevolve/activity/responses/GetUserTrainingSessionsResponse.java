package learntrainevolve.activity.responses;

import learntrainevolve.models.TrainingSessionModel;

import java.util.List;

public class GetUserTrainingSessionsResponse {

    private final List<TrainingSessionModel> trainingSessionModelList;

    private GetUserTrainingSessionsResponse(List<TrainingSessionModel> trainingSessionModelList) {
        this.trainingSessionModelList = trainingSessionModelList;
    }

    public List<TrainingSessionModel> GetUserTrainingSessionModelList(){
        return trainingSessionModelList;
    }

    @Override
    public String toString() {
        return "GetUserTrainingSessionsResponse{" +
                "trainingSessionModelList=" + trainingSessionModelList +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {

        private List<TrainingSessionModel> trainingSessionModelList;

        public Builder withTrainingSessionModelList(List<TrainingSessionModel> trainingSessionModelList) {
            System.out.println("building response");
            this.trainingSessionModelList = trainingSessionModelList;
            return this;
        }

        public GetUserTrainingSessionsResponse build() {
            return new GetUserTrainingSessionsResponse(trainingSessionModelList);
        }

    }

}
