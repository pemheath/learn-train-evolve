package learntrainevolve.activity;


import learntrainevolve.converters.ModelConverter;
import learntrainevolve.models.TrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import learntrainevolve.activity.requests.GetTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetTrainingSessionsResponse;
import learntrainevolve.dynamodb.TrainingSessionDao;


import javax.inject.Inject;
import java.util.List;


public class GetTrainingSessionsActivity {

    private final Logger log = LogManager.getLogger();

    private final TrainingSessionDao trainingSessionDao;

    @Inject
    public GetTrainingSessionsActivity(TrainingSessionDao trainingSessionDao) {
        this.trainingSessionDao = trainingSessionDao;
    }

    public GetTrainingSessionsResponse handleRequest(final GetTrainingSessionsRequest request) {
        List<TrainingSessionModel> listOfSessions;
        log.info("Received GetTrainingSessionsRequest {}}", request);
        if(request.getType()==null) {
            listOfSessions = new ModelConverter()
                    .toListOfTrainingSessionModels(trainingSessionDao.getUpcomingTrainingSessions());
        }

        else {
            listOfSessions = new ModelConverter()
                    .toListOfTrainingSessionModels(trainingSessionDao.getUpcomingTrainingSessionsByType(request.getType()));
        }

        return GetTrainingSessionsResponse.builder()
                .withTrainingSessionModelList(listOfSessions)
                .build();

    }

}
