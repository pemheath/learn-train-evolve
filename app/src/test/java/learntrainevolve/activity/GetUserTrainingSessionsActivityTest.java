package learntrainevolve.activity;

import learntrainevolve.activity.requests.GetUserTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetUserTrainingSessionsResponse;
import learntrainevolve.dynamodb.UserTrainingSessionDao;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetUserTrainingSessionsActivityTest {

    @Mock
    private UserTrainingSessionDao userTrainingSessionDao;

    private GetUserTrainingSessionsActivity getUserTrainingSessionsActivity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        getUserTrainingSessionsActivity = new GetUserTrainingSessionsActivity(userTrainingSessionDao);
    }

    @Test
    public void handleRequest_givenValidRequestForExistingUser_returnsListOfUserTrainingSessions(){
        //GIVEN
        String userEmail = "john@aol.com";
        UserTrainingSession userTrainingSession1 = new UserTrainingSession();
        UserTrainingSession userTrainingSession2 = new UserTrainingSession();
        UserTrainingSession userTrainingSession3 = new UserTrainingSession();

        userTrainingSession1.setEmail(userEmail);
        userTrainingSession2.setEmail(userEmail);
        userTrainingSession3.setEmail(userEmail);

        when(userTrainingSessionDao.getNextWeekOfUserTrainingSessions(userEmail)).thenReturn(List.of(userTrainingSession1, userTrainingSession2, userTrainingSession3));

        GetUserTrainingSessionsRequest getUserTrainingSessionsRequest = GetUserTrainingSessionsRequest.builder()
                .withEmail(userEmail)
                .build();
        //WHEN
        GetUserTrainingSessionsResponse response = getUserTrainingSessionsActivity.handleRequest(getUserTrainingSessionsRequest);
        //THEN

        assertEquals(3, response.getUserTrainingSessionModelList().size());
    }

    @Test
    public void handleRequest_givenEmailWithNoUserSessions_returnsEmptyList(){
        //GIVEN
        String userEmail = "hello@hotmail.net";

        when(userTrainingSessionDao.getNextWeekOfUserTrainingSessions(userEmail)).thenReturn(List.of());

        GetUserTrainingSessionsRequest getUserTrainingSessionsRequest = GetUserTrainingSessionsRequest.builder()
                .withEmail(userEmail)
                .build();
        //WHEN
        GetUserTrainingSessionsResponse response = getUserTrainingSessionsActivity.handleRequest(getUserTrainingSessionsRequest);
        //THEN

        assertEquals(0, response.getUserTrainingSessionModelList().size());
    }

}