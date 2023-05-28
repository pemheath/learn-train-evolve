package LearnTrainEvolve.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Date;

@JsonDeserialize(builder = CreateUserTrainingSessionRequest.Builder.class)
public class CreateUserTrainingSessionRequest {
    private final String email;
    private final String eventId;
    private final Date date;
    private final String type;


    public CreateUserTrainingSessionRequest(String email, String eventId,  Date date, String type) {
        this.email = email;
        this.eventId = eventId;
        this.date = date;
        this.type = type;
    }


    public String getEmail() {
        return email;
    }

    public String getEventId() {return eventId;}

    public Date getDate() {return date;}

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "CreateUserTrainingSessionRequest{" + "email='" + email + '\'' +
                ", eventId='" + eventId + '\'' +
                ", date=" + date + ", type=" + type + '}';

    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String email;
        private String eventId;

        private Date date;

        private String type;


        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }



        public CreateUserTrainingSessionRequest build() {
            return new CreateUserTrainingSessionRequest(email, eventId, date, type);


        }
    }
}
