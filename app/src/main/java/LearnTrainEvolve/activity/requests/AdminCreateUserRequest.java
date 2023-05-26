package LearnTrainEvolve.activity.requests;

import LearnTrainEvolve.lambda.infrastructure.auth.CognitoClaims;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = AdminCreateUserRequest.Builder.class)
public class AdminCreateUserRequest {
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String membership;
    private final String rank;



    public AdminCreateUserRequest(String email, String firstName, String lastName, String membership, String rank) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
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
        public Builder withMembership(String membership) {
            this.membership = membership;
            return this;
        }

        public Builder withRank(String rank) {
            this.rank = rank;
            return this;
        }


        public AdminCreateUserRequest build() {
            return new AdminCreateUserRequest(email, firstName, lastName, membership, rank
            );

        }
    }
}
