package learntrainevolve.activity.requests;



public class GetUserTrainingSessionsRequest {

    private String email;


    /**
     *  A GetUserTrainingSessionsRequest object to request a list of training sessions
     * @param email, an  parameter for user's email
     */
    private GetUserTrainingSessionsRequest(String email){
        this.email = email;
    }



    public String getEmail() {
        return this.email;
    }


    @Override
    public String toString() {
        return "GetUserTrainingSessionsRequest{" + "email=" + email +'}';
    }

    public static Builder builder(){ return new Builder();}

    public static class Builder {

        private String email;


        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }



        public GetUserTrainingSessionsRequest build() {
            GetUserTrainingSessionsRequest request = new GetUserTrainingSessionsRequest(email);
            System.out.println("GetUserTrainingSessionsRequest object is " + request);
            return request;
        }

    }
}

