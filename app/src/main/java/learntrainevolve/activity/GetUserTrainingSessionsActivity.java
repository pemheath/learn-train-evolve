package learntrainevolve.activity;


import learntrainevolve.activity.requests.GetUserTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetUserTrainingSessionsResponse;
import learntrainevolve.converters.ModelConverter;
import learntrainevolve.dynamodb.TrainingSessionDao;
import learntrainevolve.models.TrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;


public class GetUserTrainingSessionsActivity {

    private final Logger log = LogManager.getLogger();

    private final TrainingSessionDao trainingSessionDao;

    @Inject
    public GetUserTrainingSessionsActivity(TrainingSessionDao trainingSessionDao) {
        this.trainingSessionDao = trainingSessionDao;
    }

    public GetUserTrainingSessionsResponse handleRequest(final GetUserTrainingSessionsRequest request) {
        List<TrainingSessionModel> listOfSessions;
        log.info("Received GetUserTrainingSessionsRequest {}}", request);
        if(request.getType()==null) {
            listOfSessions = new ModelConverter()
                    .toListOfTrainingSessionModels(trainingSessionDao.getUpcomingTrainingSessions());
        }

        else {
            listOfSessions = new ModelConverter()
                    .toListOfTrainingSessionModels(trainingSessionDao.getUpcomingTrainingSessionsByType(request.getType()));
        }

        return GetUserTrainingSessionsResponse.builder()
                .withTrainingSessionModelList(listOfSessions)
                .build();

    }

}
