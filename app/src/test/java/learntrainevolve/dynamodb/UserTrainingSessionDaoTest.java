package learntrainevolve.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.exceptions.TrainingSessionNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTrainingSessionDaoTest {
    
    @Mock
    DynamoDBMapper dynamoDBMapper;
    UserTrainingSessionDao userTrainingSessionDao;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userTrainingSessionDao = new UserTrainingSessionDao(dynamoDBMapper);
    }

    @Test
    public void save_givenUserTrainingSession_callsMapperWithUserTrainingSession() {
        //GIVEN
        UserTrainingSession UserTrainingSession = new UserTrainingSession();
        //WHEN
        UserTrainingSession result = userTrainingSessionDao.save(UserTrainingSession);
        //THEN
        verify(dynamoDBMapper).save(UserTrainingSession);
        assertEquals(UserTrainingSession, result);
    }

    @Test
    public void getUserTrainingSessionById_givenValidIdAndEmail_returnsUserTrainingSession() {
        String email = "pem.heath@gmail.com";
        String eventId = "123";
        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEmail(email);
        userTrainingSession.setEventId(eventId);
        userTrainingSession.setType("fundamentals");
        userTrainingSession.setCoach("Bonnie");
        when(dynamoDBMapper.load(UserTrainingSession.class, email, eventId)).thenReturn(userTrainingSession);
        UserTrainingSession actualResult = userTrainingSessionDao.getUserTrainingSessionById("pem.heath@gmail.com", "123");
        assertEquals("123", actualResult.getEventId());
        assertEquals("fundamentals", actualResult.getType());
        assertEquals("Bonnie", actualResult.getCoach());
        assertEquals("pem.heath@gmail.com", actualResult.getEmail());
    }

    @Test
    public void getUserTrainingSessionById_givenNonExistentIdAndEmail_throwsResourceNotFoundException() {
        String email = "pem.heath@gmail.com";
        String eventId = "123";
        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEmail(email);
        userTrainingSession.setEventId(eventId);
        userTrainingSession.setType("fundamentals");
        userTrainingSession.setCoach("Bonnie");
        when(dynamoDBMapper.load(UserTrainingSession.class, "pem@aol.com", eventId)).thenReturn(null);

        assertThrows(TrainingSessionNotFoundException.class, () ->userTrainingSessionDao.getUserTrainingSessionById("pem.@aol.com", "123") );
    }

}