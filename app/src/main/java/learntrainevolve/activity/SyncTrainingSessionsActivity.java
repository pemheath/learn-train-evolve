package learntrainevolve.activity;

import com.google.api.services.calendar.model.Event;
import learntrainevolve.activity.requests.SyncTrainingSessionsRequest;
import learntrainevolve.activity.responses.SyncTrainingSessionsResponse;
import learntrainevolve.dynamodb.TrainingSessionDao;
import learntrainevolve.dynamodb.models.TrainingSession;
import learntrainevolve.exceptions.FailedExternalAPICallException;
import learntrainevolve.exceptions.IllegalEventFormatException;
import learntrainevolve.exceptions.InvalidRequestException;
import learntrainevolve.externalApis.GoogleCalEventDao;
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
     * @param googleCalEventDao The GoogleCalEventDao object used to retrieve events from the Google Calendar through the Google Calendar API.
     *
     */
    @Inject
    public SyncTrainingSessionsActivity(TrainingSessionDao trainingSessionDao, GoogleCalEventDao googleCalEventDao) {
        this.trainingSessionDao = trainingSessionDao;
        this.googleCalEventDao = googleCalEventDao;
    }
    /**
     * This method handles the incoming request by retrieving all events from the Google Calendar, converting each event to a TrainingSession, and adding the List of TrainingSessions to DynamoDB

     * The method accepts the Calendar ID, and, if successful, returns a message of how many events have been synced to the table.
     * If the request includes an invalid CalId, throws an InvalidRequestException.
     * If the calendar contain events that are not properly formatted with a title, start time, and optional caoch, throws an IllegalEventFormatException.
     *
     * @param request SyncTrainingSessions request object containing the CalendarID belonging to the admin submitting the request.
     * @return SyncTrainingSessionsResponse  object with a message returned from GoogleCaklEventDao reporting how many events were successfully synced.
     * @throws learntrainevolve.exceptions.FailedExternalAPICallException when Google Calendar API call fails.
     * @throws learntrainevolve.exceptions.InvalidRequestException when CalendarID is invalid.
     * @throws learntrainevolve.exceptions.IllegalEventFormatException when event is not properly formatted.
     *
     */

    public SyncTrainingSessionsResponse handleRequest(final SyncTrainingSessionsRequest request) {

        log.info("Received SyncTrainingSessionsRequest{}", request);

        // Verify that the request contains a calendar ID.
        if (request.getCalId()==null) {
            throw new InvalidRequestException("Cal Id must be specified");
        }

        // Use the googleCalEventDao to retrieve all events

        List<Event> calendarEvents = null;
        try {
            calendarEvents = googleCalEventDao.getAllEvents(request.getCalId());
        }catch (GeneralSecurityException | IOException e) {
            throw new FailedExternalAPICallException(e.getMessage(), e.getCause());
        }

        // convert the list of Google events to a list of TrainingSession objects
        List<TrainingSession> sessionList = new ArrayList<>();
        for (Event event : calendarEvents) {
            try {
                TrainingSession session = new TrainingSession();
                session.setType(event.getSummary());
                session.setEventId(event.getId());
                session.setTimeAndDate(event.getStart().getDateTime().getValue() / 1000);
                session.setCoach(event.getDescription());
                session.setIsCancelled(false);
                sessionList.add(session);
            } catch (NullPointerException e) {
                throw new IllegalEventFormatException("Event may not have been properly formatted" +
                        "leading to a NPE in transition to a Training Session", e.getCause());
            }
        }
        //upload the list of TrainingSessions to DynamoDB and return response
        String message = trainingSessionDao.saveList(sessionList);
        return SyncTrainingSessionsResponse.builder()
                .withMessage(message)
                .build();
        }

}


