package learntrainevolve.activity;

import com.google.api.services.calendar.model.Event;
import learntrainevolve.activity.requests.SyncTrainingSessionsRequest;
import learntrainevolve.activity.responses.SyncTrainingSessionsResponse;
import learntrainevolve.dynamodb.TrainingSessionDao;
import learntrainevolve.dynamodb.models.TrainingSession;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.exceptions.FailedExternalAPICallException;
import learntrainevolve.exceptions.InvalidRequestException;
import learntrainevolve.externalApis.GoogleCalEventDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SyncTrainingSessionsActivityTest {

    @Mock
    TrainingSessionDao trainingSessionDao;

    @Mock
    GoogleCalEventDao googleCalEventDao;

    private SyncTrainingSessionsActivity syncTrainingSessionsActivity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        syncTrainingSessionsActivity = new SyncTrainingSessionsActivity(trainingSessionDao, googleCalEventDao);
    }

    @Test
    public void handleRequest_MissingCalId_throwsInvalidRequestException() {
        SyncTrainingSessionsRequest request = SyncTrainingSessionsRequest.builder().withCalId(null).build();
        assertThrows(InvalidRequestException.class, ()-> syncTrainingSessionsActivity.handleRequest(request));
    }

    @Test
    public void handleRequest_InvalidCalId_throwsFailedExternalAPICallException() throws GeneralSecurityException, IOException {
        SyncTrainingSessionsRequest request = SyncTrainingSessionsRequest.builder().withCalId("mycalendarid").build();
        when(googleCalEventDao.getAllEvents("mycalendarid")).thenThrow(IOException.class);
        assertThrows(FailedExternalAPICallException.class, ()-> syncTrainingSessionsActivity.handleRequest(request));
    }

//    @Test
//    public void handleRequest_givenRequestWithCalendarId_convertsListOfEventsToTrainingSessions() throws GeneralSecurityException, IOException {
//        //GIVEN
//        String calId = "calID123";
//        String eventId1 = "123";
//        String eventId2= "456";
//        String type1 = "fundamentals";
//        String type2 = "advanced";
//        SyncTrainingSessionsRequest request = SyncTrainingSessionsRequest.builder().withCalId(calId).build();
//        Event event1 = new Event();
//        Event event2 = new Event();
//        event1.set("Id", eventId1);
//        event1.set("Summary", type1);
//        event2.set("Id", eventId2);
//        event2.set("Summary", type2);
//
//        List<Event> eventList = new ArrayList<>();
//        eventList.add(event1);
//        eventList.add(event2);
//
//
//        TrainingSession trainingSession1 = new TrainingSession();
//        trainingSession1.setEventId(eventId1);
//        trainingSession1.setType(type1);
//
//        TrainingSession trainingSession2 = new TrainingSession();
//        trainingSession2.setEventId(eventId2);
//        trainingSession2.setType(type2);
//
//        List<TrainingSession> trainingSessionList = new ArrayList<>();
//        trainingSessionList.add(trainingSession1);
//        trainingSessionList.add(trainingSession2);
//
//        when(googleCalEventDao.getAllEvents(calId)).thenReturn(eventList);
//        when(trainingSessionDao.saveList(trainingSessionList)).thenReturn("Training Sessions Successfully added");
//
//        SyncTrainingSessionsResponse response = syncTrainingSessionsActivity.handleRequest(request);
//
//        verify(trainingSessionDao).saveList(trainingSessionList);
//
//
//
//
//
//
//    }







}