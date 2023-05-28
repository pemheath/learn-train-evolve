package LearnTrainEvolve.activity;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import LearnTrainEvolve.activity.requests.GetTrainingSessionsRequest;
import LearnTrainEvolve.activity.responses.GetTrainingSessionsResponse;
import LearnTrainEvolve.dynamodb.TrainingSessionDao;


import javax.inject.Inject;



public class GetTrainingSessionsActivity {

    private final Logger log = LogManager.getLogger();

    private final TrainingSessionDao trainingSessionDao;

    @Inject
    public GetTrainingSessionsActivity(TrainingSessionDao trainingSessionDao) {
        this.trainingSessionDao = trainingSessionDao;
    }

    public GetTrainingSessionsResponse handleRequest(final GetTrainingSessionsRequest request) {
        log.info("Received GetTrainingSessionsActivity {}}", request);
        return new GetTrainingSessionsResponse(trainingSessionDao.getTrainingSessions(request.getUserId()));
    }

}
