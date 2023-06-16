package learntrainevolve.activity;

import learntrainevolve.dynamodb.TrainingSessionDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;


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
    public void handleRequest_givenValidRequest_returnsListOfTrainingSessions(){

    }

}