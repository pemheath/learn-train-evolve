package LearnTrainEvolve.activity.requests;

public class GetTrainingSessionRequest {

    private final String email;
    private final String eventId;

    /**
     *
     * @param email, the email associated with the user
     * @param eventId, the eventId associated with the training session
     */

    private GetTrainingSessionRequest(String email, String eventId) {
        this.email = email;
        this.eventId = eventId;
    }

    public String getEmail() {
        return email;
    }

    public String getEventId() {
        return eventId;
    }

    @Override
    public String toString() {
        return "GetTrainingSessionRequest{" + "email='" + email + '\'' +
                ", eventId ='" + eventId +
                '}';
    }

    public static Builder builder(){ return new Builder();}

    public static class Builder {
        private String email;
        private String eventId;

        public Builder withEmail(String email) {
            this.email=email;
            return this;
        }

        public Builder withEventId(String eventId) {
            this.eventId=eventId;
            return this;
        }

        public GetTrainingSessionRequest build() {
            return new GetTrainingSessionRequest(email, eventId);
        }

    }
}
