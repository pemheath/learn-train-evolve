package learntrainevolve.activity;

import learntrainevolve.activity.requests.GetUserTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetUserTrainingSessionsResponse;
import learntrainevolve.converters.ModelConverter;
import learntrainevolve.dynamodb.UserTrainingSessionDao;
import learntrainevolve.exceptions.InvalidRequestException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;



/**
 * Implementation of the GetUserTrainingSessionsActivity for the LearnTrainEvolve API
 * This API allows the application retrieve a list of upcoming user training sessions  from the DynamoDB UserTrainingSessions table for a specific user.
 * */

public class GetUserTrainingSessionsActivity {

    private final Logger log = LogManager.getLogger();

    private final UserTrainingSessionDao userTrainingSessionDao;

    /**
     * Instantiates a new GetUserTrainingSessionsActivity object.
     * @param userTrainingSessionDao UserTrainingSessionDao to access the UserTrainingSession table.
     */

    @Inject
    public GetUserTrainingSessionsActivity(UserTrainingSessionDao userTrainingSessionDao) {
        this.userTrainingSessionDao = userTrainingSessionDao;
    }


    /**
     * This method handles the incoming request by making a call to DynamoDB to retrieve all user training sessions for the user with email provided in the request object.
     *
     *
     *As currently designed, this method will return user training sessions within one week from the time of the request,
     * including attended past sessions and scheduled future sessions.
     *
     * If the optional dataVis parameter in the request object is true, the method will return all UserTrainingSessions matching the user email and marked as attended.
     *
     * @param request GetUserTrainingSessions request object containing the email (partition key), and optional dataVis parameter (if true, directs activity to pull all attended user sessions for data analysis).
     *
     * @return GetUserTrainingSessionsResponse response object containing a list of the API defined {@link learntrainevolve.models.UserTrainingSessionModel}
     * @throws learntrainevolve.exceptions.InvalidRequestException  if email is not provided or obviously not an email. Returns an empty lsit of no data is found.
     */

    public GetUserTrainingSessionsResponse handleRequest(final GetUserTrainingSessionsRequest request) {

        log.info("Received GetUserTrainingSessionsRequest {}", request);

        // if no optional parameters are specified, get the past and next week of user training sessions.
        if (request.getDataVis()==null) {
            log.info("No DataVis parameter specified. Returning next week of user training sessions.");

            return GetUserTrainingSessionsResponse.builder()
                    .withUserTrainingSessionModelList(new ModelConverter()
                            .toListOfUserTrainingSessionModels(userTrainingSessionDao.getTwoWeeksOfUserTrainingSessions(request.getEmail())))
                            .build();
        }

        else {
            String dataVis = request.getDataVis();
            log.info("DataVis: {}", dataVis);

            if (dataVis.equalsIgnoreCase("true")) {
                return GetUserTrainingSessionsResponse.builder()
                        .withUserTrainingSessionModelList(new ModelConverter()
                                .toListOfUserTrainingSessionModels(userTrainingSessionDao.getUserTrainingSessionsForDataVis(request.getEmail())))
                        .build();
            }
            throw new InvalidRequestException("Invalid DataVis parameter: " + dataVis);
        }


    }

}
