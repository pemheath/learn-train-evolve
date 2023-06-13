package learntrainevolve.activity.requests;



public class GetUserTrainingSessionsRequest {

    private String type;


    /**
     *  A GetUserTrainingSessionsRequest object to request a list of training sessions
     * @param type, an optional parameter for filtering by type
     */
    private GetUserTrainingSessionsRequest(String type){
        this.type = type;
    }



    public String getType() {
        return this.type;
    }


    @Override
    public String toString() {
        return "GetUserTrainingSessionsRequest{" + "type=" + type + '}';
    }

    public static Builder builder(){ return new Builder();}

    public static class Builder {

        private String type;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public GetUserTrainingSessionsRequest build() {
            System.out.println("Building request object.");
            GetUserTrainingSessionsRequest request = new GetUserTrainingSessionsRequest(type);
            System.out.println("GetUserTrainingSessionsRequest object is " + request);
            return request;
        }

    }
}

