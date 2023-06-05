package learntrainevolve.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import learntrainevolve.lambda.infrastructure.auth.CognitoClaims;

import java.time.LocalDateTime;

@JsonDeserialize(builder = CreateUserTrainingSessionRequest.Builder.class)
public class CreateUserTrainingSessionRequest {

    private final String email;
    private final String eventId;
    private final Long timeAndDate;
    private final String type;
    private final String coach;


    public CreateUserTrainingSessionRequest(String email, String eventId,  Long timeAndDate, String type, String coach) {

        this.email = email;
        this.eventId = eventId;
        this.timeAndDate = timeAndDate;
        this.type = type;
        this.coach = coach;
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

    @Override
    public String toString() {
        return "CreateUserTrainingSessionRequest{" + '\'' +
                ", eventId='" + eventId + '\'' +
                ", timeAndDate=" + timeAndDate + ", type=" + type + '}';

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


        public CreateUserTrainingSessionRequest build() {
            System.out.println("building the java request object");
            CreateUserTrainingSessionRequest request = new CreateUserTrainingSessionRequest(email, eventId, timeAndDate, type, coach);
            System.out.println(request);
            return request;

        }
    }
}
