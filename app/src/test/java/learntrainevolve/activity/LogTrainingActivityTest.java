package learntrainevolve.activity;

import learntrainevolve.activity.requests.LogTrainingRequest;
import learntrainevolve.activity.responses.LogTrainingResponse;
import learntrainevolve.dynamodb.UserTrainingSessionDao;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.exceptions.InvalidRequestException;
import learntrainevolve.models.UserTrainingSessionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LogTrainingActivityTest {

    @Mock
    private UserTrainingSessionDao userTrainingSessionDao;

    private LogTrainingActivity logTrainingActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        logTrainingActivity = new LogTrainingActivity(userTrainingSessionDao);
    }

    @Test
    public void handleRequest_withRequestIncludingAllParameters_savesUserTrainingSession(){
        //Given

        String email = "pem@aol.com";
        String eventId = "123";
        String type = "Advanced";
        String coach = "Joel";
        long timeAndDate = System.currentTimeMillis()/1000;
        int techniqueEnjoyment = 4;
        int performanceRating = 3;
        int intensityRating = 95;
        Set<String> tags = new HashSet<>();
        tags.add("closed guard");
        String note = "notes";
        String goal = "goal";
        boolean attended = true;

        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEmail(email);
        userTrainingSession.setEventId(eventId);
        userTrainingSession.setType(type);
        userTrainingSession.setTimeAndDate(timeAndDate);
        userTrainingSession.setGoal(goal);
        userTrainingSession.setNote(note);
        userTrainingSession.setCoach(coach);
        userTrainingSession.setIntensityRating(intensityRating);
        userTrainingSession.setPerformanceRating(performanceRating);
        userTrainingSession.setTechniqueEnjoyment(techniqueEnjoyment);
        userTrainingSession.setTags(tags);
        userTrainingSession.setAttended(attended);


        LogTrainingRequest request = LogTrainingRequest.builder()
                .withEmail(email)
                .withEventId(eventId)
                .withCoach(coach)
                .withTimeAndDate(timeAndDate)
                .withType(type)
                .withNote(note)
                .withGoal(goal)
                .withIntensityRating(intensityRating)
                .withTechniqueEnjoyment(techniqueEnjoyment)
                .withPerformanceRating(performanceRating)
                .withAttended(attended)
                .withTags(tags)
                .build();

        LogTrainingResponse response = logTrainingActivity.handleRequest(request);

        when(userTrainingSessionDao.save(userTrainingSession)).thenReturn(userTrainingSession);

        verify(userTrainingSessionDao).save(any(UserTrainingSession.class));

        //WHEN

        UserTrainingSessionModel responseModel = response.getUserTrainingSession();
        assertEquals(request.getEmail(), responseModel.getEmail());





    }

    @Test
    public void handleRequest_withRequestMissingSomeParameters_savesUserTrainingSession(){
        //Given

        String email = "pem@aol.com";
        String eventId = "123";
        String type = "Advanced";
        String coach = "Joel";
        long timeAndDate = System.currentTimeMillis()/1000;
        int techniqueEnjoyment = 4;
        String note = "notes";
        boolean attended = true;

        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEmail(email);
        userTrainingSession.setEventId(eventId);
        userTrainingSession.setType(type);
        userTrainingSession.setTimeAndDate(timeAndDate);
        userTrainingSession.setNote(note);
        userTrainingSession.setCoach(coach);
        userTrainingSession.setTechniqueEnjoyment(techniqueEnjoyment);
        userTrainingSession.setAttended(attended);


        LogTrainingRequest request = LogTrainingRequest.builder()
                .withEmail(email)
                .withEventId(eventId)
                .withCoach(coach)
                .withTimeAndDate(timeAndDate)
                .withType(type)
                .withNote(note)
                .withTechniqueEnjoyment(techniqueEnjoyment)
                .withAttended(attended)
                .build();

        LogTrainingResponse response = logTrainingActivity.handleRequest(request);

        when(userTrainingSessionDao.save(userTrainingSession)).thenReturn(userTrainingSession);

        verify(userTrainingSessionDao).save(any(UserTrainingSession.class));

        //WHEN

        UserTrainingSessionModel responseModel = response.getUserTrainingSession();
        assertEquals(request.getEmail(), responseModel.getEmail());


    }

    @Test
    public void handleRequest_withRequestMissingKeyValues_throwsException(){
        //Given
        String type = "Advanced";
        String coach = "Joel";
        long timeAndDate = System.currentTimeMillis()/1000;
        int techniqueEnjoyment = 4;
        int performanceRating = 3;
        int intensityRating = 95;
        Set<String> tags = new HashSet<>();
        tags.add("closed guard");
        String note = "notes";
        String goal = "goal";
        boolean attended = true;

        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setType(type);
        userTrainingSession.setTimeAndDate(timeAndDate);
        userTrainingSession.setGoal(goal);
        userTrainingSession.setNote(note);
        userTrainingSession.setCoach(coach);
        userTrainingSession.setIntensityRating(intensityRating);
        userTrainingSession.setPerformanceRating(performanceRating);
        userTrainingSession.setTechniqueEnjoyment(techniqueEnjoyment);
        userTrainingSession.setTags(tags);
        userTrainingSession.setAttended(attended);

        LogTrainingRequest request = LogTrainingRequest.builder()
                .withCoach(coach)
                .withTimeAndDate(timeAndDate)
                .withType(type)
                .withNote(note)
                .withGoal(goal)
                .withIntensityRating(intensityRating)
                .withTechniqueEnjoyment(techniqueEnjoyment)
                .withPerformanceRating(performanceRating)
                .withAttended(attended)
                .withTags(tags)
                .build();


        //WHEN

        assertThrows(InvalidRequestException.class, ()-> logTrainingActivity.handleRequest(request));
    }




}