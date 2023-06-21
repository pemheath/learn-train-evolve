package learntrainevolve.activity;


import learntrainevolve.activity.requests.GetUserTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetUserTrainingSessionsResponse;
import learntrainevolve.converters.ModelConverter;
import learntrainevolve.dynamodb.UserTrainingSessionDao;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.exceptions.InvalidRequestException;
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

        // if no optional parameters are specified, get the next week of user training sessions.
        if (request.getDataVis()==null) {
            log.info("No DataVis parameter specified. Returning next week of user training sessions.");

            return GetUserTrainingSessionsResponse.builder()
                    .withUserTrainingSessionModelList(new ModelConverter()
                            .toListOfUserTrainingSessionModels(userTrainingSessionDao.getNextWeekOfUserTrainingSessions(request.getEmail())))
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
