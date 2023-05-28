package LearnTrainEvolve.activity.responses;
import LearnTrainEvolve.models.TrainingSessionModel;
import java.util.ArrayList;
import java.util.List;

public class GetTrainingSessionsResponse {

    private final List<TrainingSessionModel> trainingSessionModelList;

    private GetTrainingSessionsResponse(List<TrainingSessionModel> trainingSessionModelList) {
        this.trainingSessionModelList = trainingSessionModelList;
    }

    @Override
    public String toString() {
        return "GetTrainingSessionsResponse{" +
                "trainingSessionModelList=" + trainingSessionModelList +
                '}';
    }

    public static Builder builder() {return new Builder();}

    public static class Builder {

        private List<TrainingSessionModel> trainingSessionModelList;

        public Builder withTrainingSessionModelList(List<TrainingSessionModel> trainingSessionModelList) {
            this.trainingSessionModelList = new ArrayList<>(trainingSessionModelList);
            return this;
        }

        public GetTrainingSessionsResponse build() {
            return new GetTrainingSessionsResponse(trainingSessionModelList);
        }

    }

}
