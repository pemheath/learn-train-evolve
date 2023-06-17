package learntrainevolve.activity;


import learntrainevolve.activity.requests.GetUserTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetUserTrainingSessionsResponse;
import learntrainevolve.converters.ModelConverter;
import learntrainevolve.dynamodb.UserTrainingSessionDao;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.models.UserTrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;


public class GetUserTrainingSessionsActivity {

    private final Logger log = LogManager.getLogger();

    private final UserTrainingSessionDao userTrainingSessionDao;

    @Inject
    public GetUserTrainingSessionsActivity(UserTrainingSessionDao userTrainingSessionDao) {
        this.userTrainingSessionDao = userTrainingSessionDao;
    }

    public GetUserTrainingSessionsResponse handleRequest(final GetUserTrainingSessionsRequest request) {

        log.info("Received GetUserTrainingSessionsRequest {}}", request);

        return GetUserTrainingSessionsResponse.builder()
                .withUserTrainingSessionModelList(new ModelConverter().toListOfUserTrainingSessionModels(userTrainingSessionDao.getNextWeekOfUserTrainingSessions(request.getEmail()))).build();

    }

}
