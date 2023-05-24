package LearnTrainEvolve.activity.responses;

import LearnTrainEvolve.models.UserModel;

public class AdminCreateUserResponse {

    private final UserModel userModel;

    private AdminCreateUserResponse(UserModel userModel) {
        this.userModel = userModel;
    }

    public UserModel getUser() {return userModel;}

    @Override
    public String toString() {
        return "CreateUserResponse{" +
                "userModel=" + userModel +
                '}';
    }

    public static Builder builder() {return new Builder();}
    public static class Builder {
        private UserModel userModel;

        public Builder withUser(UserModel userModel) {
            this.userModel = userModel;
            return this;
        }

        public AdminCreateUserResponse build() {return new AdminCreateUserResponse(userModel);}
    }


}
