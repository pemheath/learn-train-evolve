package LearnTrainEvolve.models;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Objects;

public class UserModel {
    private String email;
    private String firstName;
    private String lastName;
    private String motivationalWhy;
    private String membership;
    private String rank;

    public UserModel(String email, String firstName, String lastName, String motivationalWhy, String membership, String rank) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(email, userModel.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
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

        public UserModel build() {
            return new UserModel(email, firstName, lastName, motivationalWhy, membership, rank
            );
        }
    }
}
