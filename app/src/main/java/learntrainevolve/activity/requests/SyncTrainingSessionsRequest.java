package learntrainevolve.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;

import static learntrainevolve.utils.CollectionUtlis.copyToSet;

@JsonDeserialize(builder = SyncTrainingSessionsRequest.Builder.class)
public class SyncTrainingSessionsRequest {

    private final String calId;

    public SyncTrainingSessionsRequest(String calId) {
        this.calId = calId;

    }

    public String getCalId() {
        return calId;
    }

    @Override
    public String toString() {
        return "LogTrainingRequest{" + '\'' +
                ", calId='" + calId + '\'' + '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String calId;


        public Builder withCalId(String calId) {
            this.calId = calId;
            return this;
        }

        public SyncTrainingSessionsRequest build() {
            System.out.println("building the java request object with calId" + calId);
            return new SyncTrainingSessionsRequest(calId);

        }
    }
}
