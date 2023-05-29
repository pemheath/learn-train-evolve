package LearnTrainEvolve.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@DynamoDBTable(tableName="training_sessions")
public class TrainingSession {
    public static final String TIME_AND_DATE_INDEX = "TrainingSessionsDateIndex";

    private String eventId;
    private LocalDateTime timeAndDate;
    private String type;
    private Boolean isCancelled;

    @DynamoDBHashKey(attributeName = "eventId")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @DynamoDBAttribute(attributeName = "timeAndDate")
    public LocalDateTime getTimeAndDate() {
        return timeAndDate;
    }

    public void setDate(LocalDateTime timeAndDate) {
        this.timeAndDate = timeAndDate;}

    @DynamoDBAttribute(attributeName = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "isCancelled")
    public Boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }


    @Override
    public String toString() {
        return "TrainingSession{" +
                "eventId='" + eventId + '\'' +
                ", timeAndDate=" + timeAndDate +
                ", type='" + type + '\'' +
                ", isCancelled=" + isCancelled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingSession that = (TrainingSession) o;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }



}



