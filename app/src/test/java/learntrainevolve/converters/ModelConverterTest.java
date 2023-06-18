package learntrainevolve.converters;

import learntrainevolve.dynamodb.models.TrainingSession;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.models.TrainingSessionModel;
import learntrainevolve.models.UserTrainingSessionModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModelConverterTest {

    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toUserTrainingSessionModel_givenPartiallyCompleteUserTrainingSession_convertsToModel() {
        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setAttended(false);
        userTrainingSession.setEmail("pepper@pups.org");
        userTrainingSession.setEventId("123");
        userTrainingSession.setCoach("Kevin");
        userTrainingSession.setType("Advanced");

        UserTrainingSessionModel model = modelConverter.toUserTrainingSessionModel(userTrainingSession);
        //THEN
        assertEquals(model.getEmail(), userTrainingSession.getEmail());
        assertEquals(model.getEventId(), userTrainingSession.getEventId());
        assertEquals(model.getCoach(), userTrainingSession.getCoach());
        assertEquals(model.getType(), userTrainingSession.getType());
        assertEquals(model.getEmail(), userTrainingSession.getEmail());
        assertEquals(model.getTags(), new HashSet<>());

    }

    @Test
    void toUserTrainingSessionModel_givenUserTrainingSession_convertsSuccessfully() {

        //Given
        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setAttended(false);
        userTrainingSession.setEmail("pepper@pups.org");
        userTrainingSession.setEventId("123");
        userTrainingSession.setCoach("Kevin");
        userTrainingSession.setType("Advanced");
        userTrainingSession.setTags(new HashSet<>());
        userTrainingSession.setNote("Note");
        userTrainingSession.setGoal("goal");
        userTrainingSession.setTechniqueEnjoyment(4);
        userTrainingSession.setPerformanceRating(4);
        userTrainingSession.setIntensityRating(65);
        //WHEN
        UserTrainingSessionModel model = modelConverter.toUserTrainingSessionModel(userTrainingSession);
        //THEN
        assertEquals(model.getEmail(), userTrainingSession.getEmail());
        assertEquals(model.getEventId(), userTrainingSession.getEventId());
        assertEquals(model.getCoach(), userTrainingSession.getCoach());
        assertEquals(model.getType(), userTrainingSession.getType());
        assertEquals(model.getEmail(), userTrainingSession.getEmail());
        assertEquals(model.getTags(), new HashSet<>());


    }

    @Test
    public void toListOfUserTrainingSessionModels_givenListOfUserTrainingSessions_convertsSuccessfully() {
        //GIVEN
        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setAttended(false);
        userTrainingSession.setEmail("pepper@pups.org");
        userTrainingSession.setEventId("123");
        userTrainingSession.setCoach("Kevin");
        userTrainingSession.setType("Advanced");

        UserTrainingSession userTrainingSession2 = new UserTrainingSession();
        userTrainingSession2.setEmail("pepper@pups.net");
        userTrainingSession2.setEventId("123");
        userTrainingSession2.setCoach("Joel");
        userTrainingSession2.setType("No-gi");
        userTrainingSession2.setAttended(false);

        List<UserTrainingSession> userTrainingSessions = new ArrayList<>();
        userTrainingSessions.add(userTrainingSession);
        userTrainingSessions.add(userTrainingSession2);

        UserTrainingSessionModel userTrainingSessionModel = UserTrainingSessionModel.builder()
                .withEmail("pepper@pups.org")
                .withCoach("Kevin")
                .withEventId("123")
                .withType("Advanced")
                .withAttended(false)
                .build();

        UserTrainingSessionModel userTrainingSessionModel2 = UserTrainingSessionModel.builder()
                .withEmail("pepper@pups.net")
                .withCoach("Joel")
                .withEventId("123")
                .withType("No-gi")
                .withAttended(false)
                .build();

        List<UserTrainingSessionModel> expected = new ArrayList<>();
        expected.add(userTrainingSessionModel);
        expected.add(userTrainingSessionModel2);

        //WHEN
        List<UserTrainingSessionModel> userTrainingSessionModels = modelConverter.toListOfUserTrainingSessionModels(userTrainingSessions);
        //THEN
        assertEquals(userTrainingSessionModels.size(), 2);
        assertEquals(expected, userTrainingSessionModels);

    }

    @Test
    void toUserTrainingSessionModel_givenUserTrainingSessionsWithNulls_convertsToModel() {
        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEmail(null);
        userTrainingSession.setEventId("123");
        userTrainingSession.setCoach("Kevin");
        userTrainingSession.setType("Advanced");
        userTrainingSession.setGoal(null);

        UserTrainingSessionModel model = assertDoesNotThrow(()-> modelConverter.toUserTrainingSessionModel(userTrainingSession));
        //THEN
        assertEquals(model.getEmail(), userTrainingSession.getEmail());


    }




}