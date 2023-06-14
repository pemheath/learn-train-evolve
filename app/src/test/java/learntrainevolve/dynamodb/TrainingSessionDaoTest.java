package learntrainevolve.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import learntrainevolve.dynamodb.models.TrainingSession;
import net.bytebuddy.matcher.FilterableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TrainingSessionDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private TrainingSessionDao trainingSessionDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        trainingSessionDao = new TrainingSessionDao(dynamoDBMapper);
    }

    @Test
    public void save_givenTrainingSession_callsMapperWithTrainingSession() {
        //GIVEN
        TrainingSession trainingSession = new TrainingSession();
        //WHEN
        TrainingSession result = trainingSessionDao.save(trainingSession);
        //THEN
        verify(dynamoDBMapper).save(trainingSession);
        assertEquals(trainingSession, result);
    }

//    @Test
//    public void getUpcomingTrainingSessions_whenInvoked_callsMapperWithScan() {
//        //WHEN
//        List<TrainingSession> resultList = trainingSessionDao.getUpcomingTrainingSessions();
//        verify(dynamoDBMapper).scan(eq(TrainingSession.class), any(DynamoDBScanExpression.class));
//
//    }

    @Test
    public void getUpcoiningTrainingSessionsByType_givenType_returnsFilteredList() {
        //GIVEN
        List<TrainingSession > resultList = new ArrayList<>();
        TrainingSession fundamentals = new TrainingSession();
        TrainingSession advanced = new TrainingSession();
        fundamentals.setType("Fundamentals");
        advanced.setType("Advanced");
        resultList.add(fundamentals);
        resultList.add(advanced);
//I need help figuring out how to mock the PaginatedScanList.  ***

    //    when(dynamoDBMapper.scan(eq(TrainingSession.class), any(DynamoDBScanExpression.class))).thenReturn(resultList);

        //WHEN
  //      List<TrainingSession> actualResult = trainingSessionDao.getUpcomingTrainingSessionsByType("Fundamentals");

        //THEN
  //      assertEquals(1, actualResult.size());



    }

}