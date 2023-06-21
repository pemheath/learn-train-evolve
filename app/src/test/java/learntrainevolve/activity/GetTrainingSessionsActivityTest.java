package learntrainevolve.activity;

import learntrainevolve.activity.requests.GetTrainingSessionsRequest;
import learntrainevolve.activity.requests.GetUserTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetTrainingSessionsResponse;
import learntrainevolve.dynamodb.TrainingSessionDao;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.exceptions.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


class GetTrainingSessionsActivityTest {

    @Mock
    private TrainingSessionDao trainingSessionDao;

    private GetTrainingSessionsActivity getTrainingSessionsActivity;



    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        getTrainingSessionsActivity = new GetTrainingSessionsActivity(trainingSessionDao);
    }

    @Test
    public void handleRequest_givenNonSupportedType_throwsInvalidRequestException(){
        //GIVEN
        String type = "fun times";
        GetTrainingSessionsRequest request = GetTrainingSessionsRequest.builder().withType(type).build();
        //WHEN/THEN
        assertThrows(InvalidRequestException.class, ()-> getTrainingSessionsActivity.handleRequest(request));


    }


    @Test
    public void handleRequest_givenValidRequestWithType_callsTrainingSessionDao(){
        //GIVEN
        String type = "Advanced";

        GetTrainingSessionsRequest request = GetTrainingSessionsRequest.builder().withType(type).build();
        //WHEN

        getTrainingSessionsActivity.handleRequest(request);

        // THEN

        verify(trainingSessionDao).getUpcomingTrainingSessionsByType(type);


    }

    @Test
    public void handleRequest_givenValidRequestWithNoType_callsTrainingSessionDao(){
        //GIVEN

        GetTrainingSessionsRequest request = GetTrainingSessionsRequest.builder().withType(null).build();

        //WHEN
        getTrainingSessionsActivity.handleRequest(request);
        // THEN

        verify(trainingSessionDao).getUpcomingTrainingSessions();


    }

}