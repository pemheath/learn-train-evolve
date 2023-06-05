package learntrainevolve.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName="user_training_sessions")
public class UserTrainingSession {

    private String email;
    private String eventId;
    private Long timeAndDate;
    private String type;
    private String coach;
    private Double intensityRating;
    private int techniqueEnjoyment;
    private int performanceRating;
    private int noteNumber;
    private int goalNumber;
    private Set<String> tags;
    private Boolean attended;

    @DynamoDBHashKey(attributeName = "email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @DynamoDBRangeKey(attributeName = "eventId")
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @DynamoDBAttribute(attributeName = "timeAndDate")
    public Long getTimeandDate() {
        return timeAndDate;
    }

    public void setTimeAndDate(Long timeAndDate) {
        this.timeAndDate = timeAndDate;
    }

    @DynamoDBAttribute(attributeName = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "coach")
    public String getCoach() {return coach;}

    public void setCoach(String coach) {
        this.coach = coach;
    }
    @DynamoDBAttribute(attributeName = "intensityRating")
    public Double getIntensityRating() {
        return intensityRating;
    }

    public void setIntensityRating(Double intensityRating) {
        this.intensityRating = intensityRating;
    }

   @DynamoDBAttribute(attributeName = "techniqueEenjoyment")
    public int getTechniqueEnjoyment() {
        return techniqueEnjoyment;
    }

    public void setTechniqueEnjoyment(int techniqueEnjoyment) {
        this.techniqueEnjoyment = techniqueEnjoyment;
    }

   @DynamoDBAttribute(attributeName = "performanceRating")
    public int getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }

    @DynamoDBAttribute(attributeName = "noteNumber")
    public int getNoteNumber() {
        return noteNumber;
    }

    public void setNoteNumber(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    @DynamoDBAttribute(attributeName = "goalNumber")
    public int getGoalNumber() {
        return goalNumber;
    }

    public void setGoalNumber(int goalNumber) {
        this.goalNumber = goalNumber;
    }

    @DynamoDBAttribute(attributeName = "tags")
    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    @DynamoDBAttribute(attributeName = "attended")
    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    @Override
    public String toString() {

        return "UserTrainingSession{" +
                "email='" + email + '\'' +
                ", eventId='" + eventId + '\'' +
                ", timeAndDate=" + timeAndDate +
                ", type='" + type + '\'' +
                ", coach='" + coach + '\'' +
                ", intensityRating=" + intensityRating +
                ", techniqueEnjoyment=" + techniqueEnjoyment +
                ", performanceRating=" + performanceRating +
                ", noteNumber=" + noteNumber +
                ", goalNumber=" + goalNumber +
                ", tags=" + tags +
                ", attended=" + attended +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTrainingSession that = (UserTrainingSession) o;
        return Objects.equals(email, that.email) && Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, eventId);
    }
}



