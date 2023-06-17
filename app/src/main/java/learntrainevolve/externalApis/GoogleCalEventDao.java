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
import java.util.List;


@Singleton
public class GoogleCalEventDao {

    private final Credentials credentials;
    private final Logger log = LogManager.getLogger();

    private static final String APPLICATION_NAME = "My First Project";
    private static final GsonFactory GSON_FACTORY = GsonFactory.getDefaultInstance();


    @Inject
    public GoogleCalEventDao(Credentials credentials) {
        this.credentials = credentials;
    }

    public List<Event> getAllEvents(String calendarId) throws GeneralSecurityException, IOException {

        // Set up the HTTP transport and credentials
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


        // Make the API request to get events
        Events events = calendarService.events().list(calendarId)
                .setKey(credentials.getApiKey())
                .setSingleEvents(true)
                .execute();
        log.info("{}events received", events.size());


        return events.getItems();

    }

}





