package LearnTrainEvolve.activity;


import LearnTrainEvolve.converters.ModelConverter;
import LearnTrainEvolve.models.TrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import LearnTrainEvolve.activity.requests.GetTrainingSessionsRequest;
import LearnTrainEvolve.activity.responses.GetTrainingSessionsResponse;
import LearnTrainEvolve.dynamodb.TrainingSessionDao;


import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class GetTrainingSessionsActivity {

    private final Logger log = LogManager.getLogger();

    private final TrainingSessionDao trainingSessionDao;

    @Inject
    public GetTrainingSessionsActivity(TrainingSessionDao trainingSessionDao) {
        this.trainingSessionDao = trainingSessionDao;
    }

    public GetTrainingSessionsResponse handleRequest(final GetTrainingSessionsRequest request) {
        List<TrainingSessionModel> listOfSessions = new ArrayList<>();
        System.out.println("received request" + request);
        log.info("Received GetTrainingSessionsActivity {}}", request);
        if(request.getType()==null) {
            listOfSessions = new ModelConverter()
                    .toListOfTrainingSessionModels(trainingSessionDao.getFutureTrainingSessions());
        }

        return GetTrainingSessionsResponse.builder()
                .withTrainingSessionModelList(listOfSessions)
                .build();

    }

}
