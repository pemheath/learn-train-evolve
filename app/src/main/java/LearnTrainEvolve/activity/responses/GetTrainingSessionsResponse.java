package LearnTrainEvolve.activity.responses;
import LearnTrainEvolve.models.TrainingSessionModel;
import java.util.List;

public class GetTrainingSessionsResponse {

    private final List<TrainingSessionModel> trainingSessionModelList;

    private GetTrainingSessionsResponse(List<TrainingSessionModel> trainingSessionModelList) {
        this.trainingSessionModelList = trainingSessionModelList;
    }

    public List<TrainingSessionModel> getTrainingSessionModelList(){
        return trainingSessionModelList;
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
            System.out.println("building response");
            this.trainingSessionModelList = trainingSessionModelList;
            return this;
        }

        public GetTrainingSessionsResponse build() {
            return new GetTrainingSessionsResponse(trainingSessionModelList);
        }

    }

}
