package learntrainevolve.activity;


import learntrainevolve.converters.ModelConverter;
import learntrainevolve.dynamodb.models.TrainingSession;
import learntrainevolve.exceptions.InvalidRequestException;
import learntrainevolve.models.TrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import learntrainevolve.activity.requests.GetTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetTrainingSessionsResponse;
import learntrainevolve.dynamodb.TrainingSessionDao;


import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the GetTrainingSessionsActivity for the LearnTrainEvolve API
 * This API allows the application retrieve a list of upcoming training sessions  from the DynamoDB TrainingSessions table.
 * */

public class GetTrainingSessionsActivity {

    private final Logger log = LogManager.getLogger();

    private final TrainingSessionDao trainingSessionDao;

    /**
     * Instantiates a new CreateUserTrainingSessionActivity object.
     *
     * @param trainingSessionDao TrainingSessionDao to access the TrainingSession table.
     */

    @Inject
    public GetTrainingSessionsActivity(TrainingSessionDao trainingSessionDao) {
        this.trainingSessionDao = trainingSessionDao;
    }

    /**
     * This method handles the incoming request by making a call to DynamoDB to retrieve all training sessions.
     *  If the type is specified, only sessions matching that type will be returned.
     *
     *As currently designed, this method will return training sessions schedule within one week out from the time of the request,
     * that have not been marked as cancelled. Ie, this method will retrieve a week's worth of scheduled classes.
     *
     * @param request GetTrainingSessions request object containing the optional type parameter
     *
     * @return GetTrainingSessionsResponse response object containing a list of the API defined {@link TrainingSessionModel}
     * @throws learntrainevolve.exceptions.InvalidRequestException  if the type provided does not match a supported class type (currently,
     * Advanced, Basics, Fundamentals, No-Gi, Competition, Open Mat).
     */

    public GetTrainingSessionsResponse handleRequest(final GetTrainingSessionsRequest request) {
        List<TrainingSessionModel> listOfSessions;
        log.info("Received GetTrainingSessionsRequest {}", request);
        if(request.getType()==null) {
            List<TrainingSession> listFromDynamoDB=trainingSessionDao.getUpcomingTrainingSessions();
            log.info("list from dynamoDB{}", listFromDynamoDB);
            listOfSessions = new ModelConverter()
                    .toListOfTrainingSessionModels(listFromDynamoDB);
            log.info("list of models {}", listOfSessions);
        }
        else {
            String supportedTypes = "advanced, basics, fundamentals, no-gi, nogi, competition, open mat";
            if (!supportedTypes.contains(request.getType().toLowerCase())) {
                throw new InvalidRequestException("Provided class type does not match available class type options");
            }
            listOfSessions = new ModelConverter()
                    .toListOfTrainingSessionModels(trainingSessionDao.getUpcomingTrainingSessionsByType(request.getType()));
        }

        return GetTrainingSessionsResponse.builder()
                .withTrainingSessionModelList(listOfSessions)
                .build();
    }

}
