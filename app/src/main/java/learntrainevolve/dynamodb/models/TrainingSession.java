package learntrainevolve.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

@DynamoDBTable(tableName="training_sessions")
public class TrainingSession {

    private String eventId;
    private long timeAndDate;
    private String type;
    private Boolean isCancelled;
    private String coach;

    @DynamoDBHashKey(attributeName = "eventId")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @DynamoDBAttribute(attributeName = "timeAndDate")
    public long getTimeAndDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(long timeAndDate) {
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

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    @Override
    public String toString() {
        return "TrainingSession{" +
                "eventId='" + eventId + '\'' +
                ", timeAndDate=" + timeAndDate +
                ", type='" + type + '\'' +
                ", isCancelled=" + isCancelled +
                ", coach='" + coach + '\'' +
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



