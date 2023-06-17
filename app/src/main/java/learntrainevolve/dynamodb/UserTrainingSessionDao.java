package learntrainevolve.dynamodb;

import learntrainevolve.dynamodb.models.UserTrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import learntrainevolve.exceptions.TrainingSessionNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class UserTrainingSessionDao {

    private final DynamoDBMapper mapper;

    private final Logger log = LogManager.getLogger();

    private static final String TIME_DATE_INDEX = "UserTrainingSessionTimeDateIndex";

    @Inject
    public UserTrainingSessionDao(DynamoDBMapper mapper) {
        this.mapper=mapper;}

    public UserTrainingSession save(UserTrainingSession userTrainingSession) {
        this.mapper.save(userTrainingSession);
        return userTrainingSession;
    }

    public UserTrainingSession getUserTrainingSessionById(String email, String eventId) {
        UserTrainingSession result = this.mapper.load(UserTrainingSession.class, email, eventId);
        if(result!=null) {
            return result;
        }
        else{
            throw new TrainingSessionNotFoundException(String.format("No user training session matches the provided email %S and eventID %S", email, eventId));
        }
    }



    public List<UserTrainingSession> getNextWeekOfUserTrainingSessions(String email) {
        log.info("In UserTrainingSessionDao calling getNextWeekOfUSerTrainingSessions with email {}", email);
        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEmail(email);

        DynamoDBQueryExpression<UserTrainingSession> queryExpression = new DynamoDBQueryExpression<UserTrainingSession>()
                .withHashKeyValues(userTrainingSession);
        List<UserTrainingSession> sessions;
        try {
            sessions = this.mapper.query(UserTrainingSession.class, queryExpression);
            log.info("Retrieved dynamodb response {}", sessions);
            long currentTime = System.currentTimeMillis()/1000;
            return sessions.stream()
                    .filter(s -> s.getTimeAndDate()>=currentTime)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }


}


