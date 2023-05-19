package LearnTrainEvolve.models;

import java.util.Date;
import java.util.TimeZone;

public class End {

    public Date datetime;
    public TimeZone timeZone;


    /**
     * Constructs a start or end object for a google calendar event resource
     *
     * @param datetime the date and time of the event
     * @param timeZone the time zone for the event
     */
    public End(Date datetime, TimeZone timeZone) {
        this.datetime = datetime;
        this.timeZone = timeZone;
    }

}