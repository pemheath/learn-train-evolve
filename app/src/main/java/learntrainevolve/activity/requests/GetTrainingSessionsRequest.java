package learntrainevolve.activity.requests;



public class GetTrainingSessionsRequest {

    private String type;


    /**
     *  A GetTrainingSessionsRequest object to request a list of training sessions
     * @param type, an optional parameter for filtering by type
     */
    private GetTrainingSessionsRequest(String type){
        this.type = type;
    }



    public String getType() {
        return this.type;
    }


    @Override
    public String toString() {
        return "GetTrainingSessionsRequest{" + "type=" + type + '}';
    }

    public static Builder builder(){ return new Builder();}

    public static class Builder {

        private String type;

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public GetTrainingSessionsRequest build() {
            System.out.println("Building request object.");
            GetTrainingSessionsRequest request = new GetTrainingSessionsRequest(type);
            System.out.println("GetTrainingSessionsRequest object is " + request);
            return request;
        }

    }
}

