package learntrainevolve.externalApis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.util.List;

@Singleton
public class GoogleCalEventDao {

    private final HttpClient httpClient;
    private final Credentials credentials;
    private final Logger log = LogManager.getLogger();

    /**
     * @param httpClient
     * @param credentials
     */
    @Inject
    public GoogleCalEventDao(HttpClient httpClient, Credentials credentials) {
        this.httpClient = httpClient;
        this.credentials = credentials;
    }


    public List<Event> getAllEvents(String calendarId) throws GeneralSecurityException, IOException, InterruptedException {
        DateTime dateTime = new DateTime(System.currentTimeMillis());
        String url = "https://www.googleapis.com/calendar/v3/calendars/" + calendarId + "/events?timeMin=" + dateTime.toStringRfc3339() + "&key=" + credentials.getApiKey();
        log.info("Getting all events from calendar " + calendarId);
        log.info("URL: " + url);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + credentials.getOAUTH_TOKEN())
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        log.info("Response status: " + response.statusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        Events events = objectMapper.readValue(response.body(), Events.class);
        List<Event> items = events.getItems();


        return items;
    }

}