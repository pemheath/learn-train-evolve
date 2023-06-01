package LearnTrainEvolve.converters;

import LearnTrainEvolve.dynamodb.models.TrainingSession;
import LearnTrainEvolve.models.TrainingSessionModel;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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




}