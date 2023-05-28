package LearnTrainEvolve.activity.requests;
import java.util.Date;

public class GetTrainingSessionsRequest {

    private Date date;

    private GetTrainingSessionsRequest(){
        this.date = new Date();
    }


    /**
     *  A GetTrainingSessionsRequest object to request a list of training sessions
     * @param date, the date after which training sessions will be returned (default to current date)
     */

    private GetTrainingSessionsRequest(Date date) {
        this.date = date;
    }



    public Date getDate() {
        return new Date(date.getTime());
    }


    @Override
    public String toString() {
        return "GetTrainingSessionsRequest{" + "date=" + date + '}';
    }

    public static Builder builder(){ return new Builder();}

    public static class Builder {

        private Date date;

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        }

        public GetTrainingSessionsRequest build() {
            return new GetTrainingSessionsRequest(date);
        }

    }

