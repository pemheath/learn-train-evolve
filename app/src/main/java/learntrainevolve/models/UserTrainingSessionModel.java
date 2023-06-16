package learntrainevolve.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserTrainingSessionModel {

    private String email;
    private String eventId;
    @JsonProperty("timeAndDate")
    private long timeAndDate;
    private String type;
    private String coach;
    private int intensityRating;
    private int techniqueEnjoyment;
    private int performanceRating;
    private String note;
    private String goal;
    private Set<String> tags;
    private Boolean attended;

//    public UserTrainingSessionModel(String email, String eventId, long timeAndDate, String type, String coach) {

//        tags = new HashSet<>();
//    }

    public UserTrainingSessionModel(String email, String eventId, long timeAndDate, String type, String coach,
                                    int intensityRating, int techniqueEnjoyment, int performanceRating,
                                    String note, String goal,
                                    Set<String> tags, Boolean attended) {
        this.email = email;
        this.eventId = eventId;
        this.timeAndDate = timeAndDate;
        this.type = type;
        this.coach = coach;
        this.intensityRating = intensityRating;
        this.techniqueEnjoyment = techniqueEnjoyment;
        this.performanceRating = performanceRating;
        this.note = note;
        this.goal = goal;
        this.tags = new HashSet<>(tags);
        this.attended = attended;
    }



    public String getEmail() {
        return email;
    }

    public String getEventId() {
        return eventId;
    }

    public long getTimeAndDate() {
        return timeAndDate;
    }

    public String getType() {
        return type;
    }

    public String getCoach() { return coach ;}

    public int getIntensityRating() {
        return intensityRating;
    }

    public int getTechniqueEnjoyment() {
        return techniqueEnjoyment;
    }

    public int getPerformanceRating() {
        return performanceRating;
    }

    public String getNote() {
        return note;
    }

    public String getGoal() {
        return goal;
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
        private long timeAndDate;
        private String type;
        private String coach;
        private int intensityRating;
        private int techniqueEnjoyment;
        private int performanceRating;
        private String note;
        private String goal;
        private Set<String> tags;
        private Boolean attended;

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
                    ", note=" + note +
                    ", goal=" + goal +
                    ", tags=" + tags +
                    ", attended=" + attended +
                    '}';
        }


        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder withTimeAndDate(long timeAndDate) {
            this.timeAndDate = timeAndDate;
            return this;
        }

        public Builder withType(String type) {
            if (type!=null) {this.type = type;
                } else{this.type="";}
            return this;
        }

        public Builder withCoach(String coach) {
            if (coach!=null) {this.coach = coach;
            } else{this.coach="";}
            return this;
        }

        public Builder withIntensityRating(int intensityRating) {
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

        public Builder withNote(String note) {
            if (note!=null) {this.note = note;
            } else{this.note="";}
            return this;
        }

        public Builder withGoal(String goal){
            if (goal!=null) {this.goal = goal;
            } else{this.goal="";}
            return this;
        }

        public Builder withTags(Set<String> tags) {
            if (tags != null) {
            this.tags = tags;
            return this;}
            else {
                this.tags = new HashSet<>();
            }
            return this;
        }

        public Builder withAttended(Boolean attended) {
            if (attended!=null) {this.attended = attended;
            } else{this.attended=Boolean.FALSE;}
            return this;
        }

        public UserTrainingSessionModel build() {
            return new UserTrainingSessionModel(email, eventId, timeAndDate, type, coach,
                    intensityRating, techniqueEnjoyment, performanceRating, note,
                    goal, tags, attended);
        }
    }
}
