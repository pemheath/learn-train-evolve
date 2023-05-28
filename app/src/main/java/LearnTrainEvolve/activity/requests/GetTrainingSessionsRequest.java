package LearnTrainEvolve.activity.requests;
import java.time.LocalDateTime;


public class GetTrainingSessionsRequest {

    private LocalDateTime timeAndDate;

    private GetTrainingSessionsRequest(){
        this.timeAndDate = LocalDateTime.now();
    }


    /**
     *  A GetTrainingSessionsRequest object to request a list of training sessions
     * @param timeAndDate, the date after which training sessions will be returned (default to current date)
     */

    private GetTrainingSessionsRequest(LocalDateTime timeAndDate) {
        this.timeAndDate = timeAndDate;
    }



    public LocalDateTime getTimeandDate() {
        return this.timeAndDate;
    }


    @Override
    public String toString() {
        return "GetTrainingSessionsRequest{" + "date=" + timeAndDate + '}';
    }

    public static Builder builder(){ return new Builder();}

    public static class Builder {

        private LocalDateTime timeAndDate;

        public Builder withTimeAndDate(LocalDateTime timeAndDate) {
            this.timeAndDate = timeAndDate;
            return this;
        }

        }

        public GetTrainingSessionsRequest build() {
            return new GetTrainingSessionsRequest(timeAndDate);
        }

    }

