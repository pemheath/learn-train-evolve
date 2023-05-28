package LearnTrainEvolve.models;

import java.time.LocalDateTime;

import java.util.Objects;

public class TrainingSessionModel {

    private String eventId;
    private LocalDateTime timeAndDate;
    private String type;
    private Boolean isCancelled;

private TrainingSessionModel(String eventId, LocalDateTime timeAndDate, String type, Boolean isCancelled){
    this.eventId = eventId;
    this.timeAndDate = timeAndDate;
    this.type = type;
    this.isCancelled = isCancelled;
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

    public Boolean getIsCancelled() {
        return isCancelled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainingSessionModel that = (TrainingSessionModel) o;
        return Objects.equals(eventId, that.eventId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId);
    }

    public static Builder builder(){ return new Builder();}


    public static class Builder{
    private String eventId;
    private LocalDateTime timeAndDate;
    private String type;
    private Boolean isCancelled;
        public Builder withEventId(String eventId){
            this.eventId = eventId;
            return this;
        }

        public Builder withTimeAndDate(LocalDateTime timeAndDate){
            this.timeAndDate = timeAndDate;
            return this;
        }

        public Builder withType(String type){
            this.type = type;
            return this;
        }

        public Builder withIsCancelled(Boolean isCancelled){
            this.isCancelled = isCancelled;
            return this;
        }

        public TrainingSessionModel build(){
            return new TrainingSessionModel(eventId, timeAndDate, type, isCancelled);
        }
    }
}






