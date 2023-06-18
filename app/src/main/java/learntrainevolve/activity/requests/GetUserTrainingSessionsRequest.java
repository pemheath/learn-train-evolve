package learntrainevolve.activity.requests;



public class GetUserTrainingSessionsRequest {

    private String email;

    private String dataVis;


    /**
     *  A GetUserTrainingSessionsRequest object to request a list of training sessions
     * @param email, an  parameter for user's email
     */
    private GetUserTrainingSessionsRequest(String email, String dataVis){
        this.email = email;
        this.dataVis = dataVis;
    }



    public String getEmail() {
        return this.email;
    }

    public String getDataVis() {
        return this.dataVis;
    }


    @Override
    public String toString() {
        return "GetUserTrainingSessionsRequest{" +
                "email=" + email +
                "dataVis=" + dataVis +'}';
    }

    public static Builder builder(){ return new Builder();}

    public static class Builder {

        private String email;

        private String dataVis;


        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withDataVis(String dataVis) {
            this.dataVis = dataVis;
            return this;
        }



        public GetUserTrainingSessionsRequest build() {
            GetUserTrainingSessionsRequest request = new GetUserTrainingSessionsRequest(email, dataVis);
            System.out.println("GetUserTrainingSessionsRequest object is " + request);
            return request;
        }

    }
}

