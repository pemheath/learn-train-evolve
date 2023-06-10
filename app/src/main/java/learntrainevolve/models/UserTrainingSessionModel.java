package learntrainevolve.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserTrainingSessionModel {

    private String email;
    private String eventId;
    private Long timeAndDate;
    private String type;
    private String coach;
    private int intensityRating;
    private int techniqueEnjoyment;
    private int performanceRating;
    private String note;
    private String goal;
    private Set<String> tags;
    private Boolean attended;

    public UserTrainingSessionModel(String email, String eventId, Long timeAndDate, String type, String coach) {
        this.email = email;
        this.eventId = eventId;
        this.timeAndDate = timeAndDate;
        this.type = type;
        this.coach = coach;
        tags = new HashSet<>();
    }

    public UserTrainingSessionModel(String email, String eventId, Long timeAndDate, String type, String coach,
                                    int intensityRating, int techniqueEnjoyment, int performanceRating,
                                    String noteNumber, String goalNumber,
                                    Set<String> tags, Boolean attended) {
        this(email, eventId, timeAndDate, type, coach);
        this.intensityRating = intensityRating;
        this.techniqueEnjoyment = techniqueEnjoyment;
        this.performanceRating = performanceRating;
        this.note = noteNumber;
        this.goal = goalNumber;
        this.tags = new HashSet<>(tags);
        this.attended = attended;
    }



    public String getEmail() {
        return email;
    }

    public String getEventId() {
        return eventId;
    }

    public Long getTimeAndDate() {
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
        private Long timeAndDate;
        private String type;
        private String coach;
        private int intensityRating;
        private int techniqueEnjoyment;
        private int performanceRating;
        private String note;
        private String goal;
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

        public Builder withTimeAndDate(Long timeAndDate) {
            this.timeAndDate = timeAndDate;
            return this;
        }

        public Builder withType(String type) {
            if (type!=null) {this.type = type;
                return this;}
            return this;

        }

        public Builder withCoach(String coach) {
            if (coach!=null) {
            this.coach = coach;
            return this;}
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
            if(note!= null) {
            this.note = note;
            return this; }
            return this;

        }

        public Builder withGoal(String goal) {
            if(goal!=null) {
            this.goal = goal;
            return this;}
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
            if (attended != null) {
            this.attended = attended;
            return this;}
            return this;
        }

        public UserTrainingSessionModel build() {
            return new UserTrainingSessionModel(email, eventId, timeAndDate, type, coach,
                    intensityRating, techniqueEnjoyment, performanceRating, note,
                    goal, tags, attended);
        }
    }
}
