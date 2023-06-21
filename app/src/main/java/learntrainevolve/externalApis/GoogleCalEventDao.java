package learntrainevolve.externalApis;


import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;

import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import com.google.api.services.calendar.Calendar;

import learntrainevolve.exceptions.FailedExternalAPICallException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

/**
 * Accesses data for upcoming Events using {@link Event} to represent the event.
 */
@Singleton
public class GoogleCalEventDao {

    private final Credentials credentials;
    private final Logger log = LogManager.getLogger();
    private static final String APPLICATION_NAME = "My First Project";
    private static final GsonFactory GSON_FACTORY = GsonFactory.getDefaultInstance();


    /**
     * Instantiates an GoogleCalEventDao object.
     *
     * @param credentials the {@link Credentials} object to obtain the API key stored with AWS Secrets Manager
     */

    @Inject
    public GoogleCalEventDao(Credentials credentials) {
        this.credentials = credentials;
    }

    /**
     *
     * @param calendarId, the calendar id for the Google calendar being queried
     * @return masterList, the list of events on the Google calendar that have not yet occurred
     * @throws FailedExternalAPICallException if the call to the Google Calendar API fails.
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public List<Event> getAllEvents(String calendarId) throws GeneralSecurityException, IOException {

        HttpTransport httpTransport = null;
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException | IOException e) {
            throw new FailedExternalAPICallException(e.getMessage(), e.getCause());
        }
        log.info("http transport created");
        HttpRequestInitializer requestInitializer = request -> {
        };
        log.info("request intializer initiated");

        // Set up the Calendar service

        Calendar calendarService = new Calendar.Builder(httpTransport, GSON_FACTORY, requestInitializer)
                .setApplicationName(APPLICATION_NAME)
                .build();
        log.info("calendar service created");

        log.info("Making call with API key{}", credentials.getApiKey());

        // Make the API request to get events, retrieving the API key from the credentials object

        String pageToken = null;
        ArrayList<Event> masterList = new ArrayList<>();
        do {
            Events events = calendarService.events().list(calendarId).setPageToken(pageToken).setKey(credentials.getApiKey()).setSingleEvents(true).execute();
            List<Event> items = events.getItems();
            masterList.addAll(items);
            pageToken = events.getNextPageToken();
        } while (pageToken != null);

        log.info("{}events received", masterList.size());

        return masterList;

    }

}





