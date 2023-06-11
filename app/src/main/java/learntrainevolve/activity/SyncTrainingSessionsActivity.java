package learntrainevolve.activity;

import com.google.api.services.calendar.model.Event;
import learntrainevolve.activity.requests.SyncTrainingSessionsRequest;
import learntrainevolve.activity.responses.SyncTrainingSessionsResponse;
import learntrainevolve.dynamodb.TrainingSessionDao;
import learntrainevolve.dynamodb.models.TrainingSession;
import learntrainevolve.externalApis.GoogleCalEventDao;
import learntrainevolve.models.UserTrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import javax.inject.Inject;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the SyncTrainingSessionsActivity for the LearnTrainEvolveService's SyncTrainingSessions API.
 *
 * This API allows the admin of the application to sync the academy training 
 * schedule from a Google Calendar to the DynamoDB TrainingSession table. 
 */
public class SyncTrainingSessionsActivity {

    private final Logger log = LogManager.getLogger();
    private final TrainingSessionDao trainingSessionDao;

    private final GoogleCalEventDao googleCalEventDao;


    /**
     * Instantiates a new SyncTrainingSessionsActivity object.
     *
     * @param trainingSessionDao The TrainingSessionDao object used to access the TrainingSessions table.
     */
    @Inject
    public SyncTrainingSessionsActivity(TrainingSessionDao trainingSessionDao, GoogleCalEventDao googleCalEventDao) {
        this.trainingSessionDao = trainingSessionDao;
        this.googleCalEventDao = googleCalEventDao;
    }
    /**
     * This method handles the incoming request by updating a user training session.
     * with the provided UserTrainingSession information (eventId, timeAndDate, type, coach, email, intensityRatung,
     * techniqueEnjoyment, performanceRating, noteNumber, goalNumber, tags, attended).
     *
     * It then returns the updated UserTrainingSession.
     * <p>
     * If the provided tags object is an invalid type, throws an
     * InvalidAttributeValueException
     *
     * @param request SyncTrainingSessions request object containing the eventId, timeAndDate, type, coach, email, intensityRating,
     *      * techniqueEnjoyment, performanceRating, noteNumber, goalNumber, tags, and attended information.
     * @return SyncTrainingSessionsResponse  object containing the API defined {@link UserTrainingSessionModel}
     * @throws learntrainevolve.exceptions.InvalidRequestException when tags or type are invalid.
     */

    public SyncTrainingSessionsResponse handleRequest(final SyncTrainingSessionsRequest request) throws GeneralSecurityException, IOException, InterruptedException {

        log.info("Received SyncTrainingSessionsRequest{}", request);

        // Use the information in the request to fetch a list of all google events

        List<Event> calendarEvents = googleCalEventDao.getAllEvents(request.getCalId());
        log.info("Received {} events from Google Calendar", calendarEvents.size());
        // convert the list of google events to a list of TrainingSession objects
        List<TrainingSession> sessionList = new ArrayList<>();
        for (Event event : calendarEvents) {
            TrainingSession session = new TrainingSession();
            session.setType(event.getSummary());
            session.setEventId(event.getId());
            session.setTimeAndDate(event.getStart().getDateTime().getValue() / 1000);
            session.setCoach(event.getDescription());
            sessionList.add(session);
        }
        //upload those sessions to DynamoDB
        String message = trainingSessionDao.saveList(sessionList);
        return SyncTrainingSessionsResponse.builder()
                .withMessage(message)
                .withNumberOfEventsProcessed(sessionList.size())
                .build();
        }

}


