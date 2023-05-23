package LearnTrainEvolve.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = CreateUserRequest.Builder.class)
public class CreateUserRequest {
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String motivationalWhy;
    private final String membership;
    private final String rank;

    public CreateUserRequest(String email, String firstName, String lastName, String motivationalWhy, String membership, String rank) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.motivationalWhy = motivationalWhy;
        this.membership = membership;
        this.rank = rank;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMotivationalWhy() {
        return motivationalWhy;
    }

    public String getMembership() {
        return membership;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "CreateUserRequest{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", motivationalWhy='" + motivationalWhy + '\'' +
                ", membership='" + membership + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {

        private String email;
        private String firstName;
        private String lastName;
        private String motivationalWhy;
        private String membership;
        private String rank;

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder withMotivationalWhy(String motivationalWhy) {
            this.motivationalWhy = motivationalWhy;
            return this;
        }
        public Builder withMembership(String membership) {
            this.membership = membership;
            return this;
        }

        public Builder withRank(String rank) {
            this.rank = rank;
            return this;
        }

        public CreateUserRequest build() {
            return new CreateUserRequest(email, firstName, lastName, motivationalWhy, membership, rank
            );

        }
    }
}
