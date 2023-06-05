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
    void toTrainingSessionModel_withISODate_convertsTrainingSession() {
        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setEventId("123");
        Long time = new Long(1686235200);
        trainingSession.setTimeAndDate(time);
        trainingSession.setType("Advanced");
        trainingSession.setIsCancelled(false);

        TrainingSessionModel model = modelConverter.toTrainingSessionModel(trainingSession);

        assertEquals(model.getEventId(), "123");
     //   assertEquals(model.getTimeAndDate(), "2023-05-31T08:00:00.123+01:00");
        assertEquals(model.getType(), "Advanced");

        assertEquals(String.valueOf(model.getTimeAndDate()), "1686235200");

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