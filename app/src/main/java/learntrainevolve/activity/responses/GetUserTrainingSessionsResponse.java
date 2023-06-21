package learntrainevolve.activity.responses;

import learntrainevolve.models.UserTrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GetUserTrainingSessionsResponse {

    private final List<UserTrainingSessionModel> userTrainingSessionModelList;
    private final Logger log = LogManager.getLogger();



    private GetUserTrainingSessionsResponse(List<UserTrainingSessionModel> userTrainingSessionModelList) {
        this.userTrainingSessionModelList = userTrainingSessionModelList;
    }

    public List<UserTrainingSessionModel> getUserTrainingSessionModelList(){
        return userTrainingSessionModelList;
    }

    @Override
    public String toString() {
        return "GetUserTrainingSessionsResponse{" +
                "userTrainingSessionModelList=" + userTrainingSessionModelList +
                '}';
    }


    public static Builder builder() {return new Builder();}

    public static class Builder {

        private List<UserTrainingSessionModel> userTrainingSessionModelList;

        public Builder withUserTrainingSessionModelList(List<UserTrainingSessionModel> userTrainingSessionModelList) {

            this.userTrainingSessionModelList = userTrainingSessionModelList;
            return this;
        }

        public GetUserTrainingSessionsResponse build() {
            return new GetUserTrainingSessionsResponse(userTrainingSessionModelList);
        }

    }

}
