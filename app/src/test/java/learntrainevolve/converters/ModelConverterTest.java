package learntrainevolve.converters;

import learntrainevolve.dynamodb.models.TrainingSession;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.models.TrainingSessionModel;
import learntrainevolve.models.UserTrainingSessionModel;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ModelConverterTest {

    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toUserTrainingSessionModel_givenPartiallCompleteUserTrainingSession_convertsToModel() {
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




}