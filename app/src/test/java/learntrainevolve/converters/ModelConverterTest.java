package learntrainevolve.converters;

import learntrainevolve.dynamodb.models.TrainingSession;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.models.TrainingSessionModel;
import learntrainevolve.models.UserTrainingSessionModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ModelConverterTest {

    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toTrainingSessionModel_withISODate_convertsTrainingSession() {
        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setEventId("123");
        trainingSession.setTimeAndDate("2023-05-31T08:00:00.123+01:00");
        trainingSession.setType("Advanced");
        trainingSession.setIsCancelled(false);

        TrainingSessionModel model = modelConverter.toTrainingSessionModel(trainingSession);

        assertEquals(model.getEventId(), "123");
     //   assertEquals(model.getTimeAndDate(), "2023-05-31T08:00:00.123+01:00");
        assertEquals(model.getType(), "Advanced");

        LocalDateTime dateTime = model.getTimeAndDate();
        String day = dateTime.getDayOfWeek().toString();
        assertEquals("WEDNESDAY", day);

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
        userTrainingSession.setTimeAndDate("2023-05-31T08:00:00.123+01:00");
        //WHEN
        UserTrainingSessionModel model = modelConverter.toUserTrainingSessionModel(userTrainingSession);
        //THEN
        assertEquals(model.getEmail(), userTrainingSession.getEmail());
        assertEquals(model.getEventId(), userTrainingSession.getEventId());
        assertEquals(model.getCoach(), userTrainingSession.getCoach());
        assertEquals(model.getType(), userTrainingSession.getType());
        assertEquals(model.getEmail(), userTrainingSession.getEmail());
        assertEquals(model.getTags(), new HashSet<>());
        LocalDateTime dateTime = model.getTimeAndDate();
        String day = dateTime.getDayOfWeek().toString();
        assertEquals("WEDNESDAY", day);


    }




}