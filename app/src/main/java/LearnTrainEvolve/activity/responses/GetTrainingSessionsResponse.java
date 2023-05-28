package LearnTrainEvolve.activity.responses;

import LearnTrainEvolve.dynamodb.models.TrainingSession;
import LearnTrainEvolve.models.TrainingSessionModel;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import java.util.List;

public class GetTrainingSessionsResponse {

    private List<TrainingSessionModel> trainingSessionModelList;

    private GetTrainingSessionsResponse(List<TrainingSessionModel> trainingSessionModelList) {
        this.trainingSessionModelList = trainingSessionModelList;
    }

    @Override
    public String toString() {
        return "GetTrainingSessionsResponse{" +
                "trainingSessionModelList=" + trainingSessionModelList +
                '}';
    }
}
