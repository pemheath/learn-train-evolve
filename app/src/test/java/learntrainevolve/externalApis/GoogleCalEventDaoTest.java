package learntrainevolve.externalApis;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;



class GoogleCalEventDaoTest {

    @Mock
    private Credentials credentials;

    @Mock
    private HttpTransport httpTransport;

    @Mock
    private HttpRequestInitializer httpRequestInitializer;

    @Mock
    private Calendar calendarService;


    private GoogleCalEventDao googleCalEventDao;


    private static final String APPLICATION_NAME = "My First Project";
    private static final GsonFactory GSON_FACTORY = GsonFactory.getDefaultInstance();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        googleCalEventDao = new GoogleCalEventDao(credentials);
    }
}
//
//    @Test
//    public void getAllEvents_whenInvoked_callsCalendarService() throws GeneralSecurityException, IOException {
//        //Given
//        String calId = "validCalendar123";
//
//        HttpTransport httpTransport = null;
//        try {
//            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        } catch (GeneralSecurityException | IOException e) {
//            throw new FailedExternalAPICallException(e.getMessage(), e.getCause());
//        }
//
//        HttpRequestInitializer requestInitializer = request -> {
//        };
//   //WHEN
//        try {
//            List<Event> result = googleCalEventDao.getAllEvents(calId);
//        } catch (NullPointerException ignored) {
//        }
//
//        //
//        verify(calendarService.events().list(calId)).setPageToken(null).setKey("apiKey").setSingleEvents(true).execute();
//
//    }
//
//}