package learntrainevolve.activity;

import learntrainevolve.activity.requests.CreateUserTrainingSessionRequest;
import learntrainevolve.activity.responses.CreateUserTrainingSessionResponse;
import learntrainevolve.dynamodb.UserTrainingSessionDao;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.exceptions.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class CreateUserTrainingSessionActivityTest {
    @Mock
    private UserTrainingSessionDao userTrainingSessionDao;

    private CreateUserTrainingSessionActivity createUserTrainingSessionActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        createUserTrainingSessionActivity = new CreateUserTrainingSessionActivity(userTrainingSessionDao);
    }

    @Test
    public void handleRequest_withRequiredFields_createsAndSavesUserTrainingSession() {
        String email = "pem@aol.com";
        String eventId = "123";
        String type = "Advanced";
        String coach = "Joel";
        long timeAndDate = System.currentTimeMillis()/1000;

        CreateUserTrainingSessionRequest request = CreateUserTrainingSessionRequest.builder()
                .withEmail(email)
                .withEventId(eventId)
                .withType(type)
                .withCoach(coach)
                .withTimeAndDate(timeAndDate)
                .build();

        CreateUserTrainingSessionResponse response = createUserTrainingSessionActivity.handleRequest(request);

        verify(userTrainingSessionDao).save(any(UserTrainingSession.class));

        assertNotNull(response.getUserTrainingSession().getCoach());
    }

    @Test
    public void handleRequest_invalidEmail_throwsException() {
        String email = "pemaol.com";
        String type = "Advanced";
        String coach = "Joel";
        long timeAndDate = System.currentTimeMillis()/1000;

        CreateUserTrainingSessionRequest request = CreateUserTrainingSessionRequest.builder()
                .withEmail(email)
                .withType(type)
                .withCoach(coach)
                .withTimeAndDate(timeAndDate)
                .build();

        assertThrows(InvalidRequestException.class, ()-> createUserTrainingSessionActivity.handleRequest(request));
    }

    @Test
    public void handleRequest_missingRequiredFields_throwsException() {
        String email = "pem@aol.com";
        String type = "Advanced";
        String coach = "Joel";
        long timeAndDate = System.currentTimeMillis()/1000;

        CreateUserTrainingSessionRequest request = CreateUserTrainingSessionRequest.builder()
                .withEmail(email)
                .withType(type)
                .withCoach(coach)
                .withTimeAndDate(timeAndDate)
                .build();

        assertThrows(InvalidRequestException.class, ()-> createUserTrainingSessionActivity.handleRequest(request));
    }


}