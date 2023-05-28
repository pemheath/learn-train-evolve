package LearnTrainEvolve.dynamodb;

import LearnTrainEvolve.dynamodb.models.UserTrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


class UserTrainingSessionDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    public UserTrainingSessionDao userTrainingSessionDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userTrainingSessionDao = new UserTrainingSessionDao(dynamoDBMapper);}

    @Test
    public void saveUserTrainingSession_validUserTrainingSession_callsDynamoDBMapper() {
        UserTrainingSession userTrainingSessionMocked = new UserTrainingSession();
        userTrainingSessionMocked.setEmail("joe@gmail.com");
        userTrainingSessionMocked.setEventId("123");
        userTrainingSessionMocked.setDate(new Date());
        userTrainingSessionMocked.setType("Advanced");

        doNothing().when(dynamoDBMapper).save(any(UserTrainingSession.class));

        //WHEN savePlaylist method is called
        UserTrainingSession userTrainingSession = userTrainingSessionDao.save(userTrainingSessionMocked);
        // Verify call was made to dynamodb

        verify(dynamoDBMapper).save(userTrainingSessionMocked);

    }


}