package LearnTrainEvolve.models;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserTrainingSessionModel {

    private String email;
    private String eventId;
    private LocalDateTime timeAndDate;
    private String type;
    private Double intensityRating;
    private int techniqueEnjoyment;
    private int performanceRating;
    private int noteNumber;
    private int goalNumber;
    private Set<String> tags;
    private Boolean attended;

    public UserTrainingSessionModel(String email, String eventId, LocalDateTime timeAndDate, String type) {
        this.email = email;
        this.eventId = eventId;
        this.timeAndDate = timeAndDate;
        this.type = type;
        tags = new HashSet<>();
    }

    public UserTrainingSessionModel(String email, String eventId, LocalDateTime timeAndDate, String type, Double intensityRating,
                                    int techniqueEnjoyment, int performanceRating,
                                    int noteNumber, int goalNumber,
                                    Set<String> tags, Boolean attended) {
        this(email, eventId, timeAndDate, type);
        this.intensityRating = intensityRating;
        this.techniqueEnjoyment = techniqueEnjoyment;
        this.performanceRating = performanceRating;
        this.noteNumber = noteNumber;
        this.goalNumber = goalNumber;
        this.tags = new HashSet<>(tags);
        this.attended = attended;
    }



    public String getEmail() {
        return email;
    }

    public String getEventId() {
        return eventId;
    }

    public LocalDateTime getTimeAndDate() {
        return timeAndDate;
    }

    public String getType() {
        return type;
    }

    public Double getIntensityRating() {
        return intensityRating;
    }

    public int getTechniqueEnjoyment() {
        return techniqueEnjoyment;
    }

    public int getPerformanceRating() {
        return performanceRating;
    }

    public int getNoteNumber() {
        return noteNumber;
    }

    public int getGoalNumber() {
        return goalNumber;
    }

    public Set<String> getTags() {
        return tags;
    }

    public Boolean getAttended() {
        return attended;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTrainingSessionModel that = (UserTrainingSessionModel) o;
        return Objects.equals(email, that.email) && Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, eventId);
    }


    public static  Builder builder() { return new Builder(); }

    public static class Builder{
        private String email;
        private String eventId;
        private LocalDateTime timeAndDate;
        private String type;
        private Double intensityRating;
        private int techniqueEnjoyment;
        private int performanceRating;
        private int noteNumber;
        private int goalNumber;
        private Set<String> tags;
        private Boolean attended;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder withTimeAndDate(LocalDateTime timeAndDate) {
            this.timeAndDate = timeAndDate;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
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

        public UserTrainingSessionModel build() {
            return new UserTrainingSessionModel(email, eventId, timeAndDate, type,
                    intensityRating, techniqueEnjoyment, performanceRating, noteNumber,
                    goalNumber, tags, attended);
        }
    }
}
