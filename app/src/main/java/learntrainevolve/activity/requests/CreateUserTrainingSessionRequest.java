package learntrainevolve.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDateTime;

@JsonDeserialize(builder = CreateUserTrainingSessionRequest.Builder.class)
public class CreateUserTrainingSessionRequest {
    private final String email;
    private final String eventId;
    private final LocalDateTime timeAndDate;
    private final String type;


    public CreateUserTrainingSessionRequest(String email, String eventId,  LocalDateTime timeAndDate, String type) {
        this.email = email;
        this.eventId = eventId;
        this.timeAndDate = timeAndDate;
        this.type = type;
    }


    public String getEmail() {
        return email;
    }

    public String getEventId() {return eventId;}

    public LocalDateTime getTimeAndDate() {return timeAndDate;}

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CreateUserTrainingSessionRequest{" + "email='" + email + '\'' +
                ", eventId='" + eventId + '\'' +
                ", timeAndDate=" + timeAndDate + ", type=" + type + '}';

    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String email;
        private String eventId;
        private LocalDateTime timeAndDate;
        private String type;


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



        public CreateUserTrainingSessionRequest build() {
            return new CreateUserTrainingSessionRequest(email, eventId, timeAndDate, type);


        }
    }
}
