package LearnTrainEvolve.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateUserTrainingSessionRequest.Builder.class)
public class CreateUserTrainingSessionRequest {
    private final String email;
    private final String eventId;


    public CreateUserTrainingSessionRequest(String email, String eventId) {
        this.email = email;
        this.eventId = eventId;
    }

    public String getEmail() {
        return email;
    }

    public String getEventId() {return eventId;}



    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "email='" + email + '\'' +
                ", eventId='" + eventId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String email;
        private String eventId;


        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withEventId(String eventId) {
            this.eventId = eventId;
            return this;
        }



        public CreateUserTrainingSessionRequest build() {
            return new CreateUserTrainingSessionRequest(email, eventId);


        }
    }
}
