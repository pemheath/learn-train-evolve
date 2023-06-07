package learntrainevolve.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import learntrainevolve.lambda.infrastructure.auth.CognitoClaims;

import java.time.LocalDateTime;
import java.util.Set;

@JsonDeserialize(builder = LogTrainingRequest.Builder.class)
public class LogTrainingRequest {

    private final String email;
    private final String eventId;
    private final Long timeAndDate;
    private final String type;
    private final String coach;
    private Double intensityRating;
    private int techniqueEnjoyment;
    private int performanceRating;
    private int noteNumber;
    private int goalNumber;
    private Set<String> tags;
    private Boolean attended;


    public LogTrainingRequest(String email, String eventId, long timeAndDate,
                              String type, String coach, Double intensityRating,
                              int techniqueEnjoyment, int performanceRating,
                              int noteNumber, int goalNumber, Set<String> tags,
                              Boolean attended)
    {
        this.email = email;
        this.eventId = eventId;
        this.timeAndDate = timeAndDate;
        this.type = type;
        this.coach = coach;
        this.intensityRating = intensityRating;
        this.techniqueEnjoyment = techniqueEnjoyment;
        this.performanceRating = performanceRating;
        this.noteNumber = noteNumber;
        this.goalNumber = goalNumber;
        this.tags = tags;
        this.attended = attended;
    }

    public String getEmail () {
        return email;
    }
    public String getEventId() {return eventId;}

    public Long getTimeAndDate() {return timeAndDate;}

    public String getType() {
        return type;
    }

    public String getCoach() {return coach;}

    public Double getIntensityRating() {return intensityRating;}


    public int getTechniqueEnjoyment() {return techniqueEnjoyment;}


    public int getPerformanceRating() {return performanceRating;}


    public int getNoteNumber() {return noteNumber;}


    public int getGoalNumber() {return goalNumber;}


    public Set<String> getTags() {return tags;}


    public Boolean getAttended() {return attended;}


    @Override
    public String toString() {
        return "LogTrainingRequest{" + '\'' +
                ", eventId='" + eventId + '\'' +
                ", timeAndDate=" + timeAndDate + ", type=" + type +
                ", coach=" + coach + ", intensityRating=" + intensityRating +
                ", techniqueEnjoyment=" + techniqueEnjoyment + ", performanceRating=" + performanceRating +
                ", noteNumber=" + noteNumber + ", goalNumber=" + goalNumber +
                ", tags=" + tags + ", attended=" + attended +'}';

    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String eventId;
        private String email;
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



        public Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withTimeAndDate(Long timeAndDate) {
            this.timeAndDate = timeAndDate;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withCoach(String coach) {
            this.coach = coach;
            return this;
        }

        public Builder withIntensityRating(Double intensityRating) {
            this.intensityRating = intensityRating;
            return this;
        }

        public Builder withTechniqueEnjoyment(int techniqueEnjoyment) {
            this.techniqueEnjoyment = techniqueEnjoyment;
            return this;
        }

        public Builder withPerformanceRating(int performanceRating) {
            this.performanceRating = performanceRating;
            return this;
        }

        public Builder withNoteNumber(int noteNumber) {

            this.noteNumber = noteNumber;
            return this;
        }

        public Builder withGoalNumber(int goalNumber) {
            this.goalNumber = goalNumber;
            return this;
        }

        public Builder withTags(Set<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder withAttended(Boolean attended) {
            this.attended = attended;
            return this;
        }


        public LogTrainingRequest build() {
            System.out.println("building the java request object");
            LogTrainingRequest request = new LogTrainingRequest(email, eventId, timeAndDate, type, coach,  intensityRating,
                    techniqueEnjoyment, performanceRating, noteNumber, goalNumber, tags, attended);
            System.out.println(request);
            return request;

        }
    }
}
