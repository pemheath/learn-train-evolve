package learntrainevolve.dynamodb;

import learntrainevolve.dynamodb.models.UserTrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class UserTrainingSessionDao {

    private final DynamoDBMapper mapper;

    private final Logger log = LogManager.getLogger();

    @Inject
    public UserTrainingSessionDao(DynamoDBMapper mapper) {
        this.mapper=mapper;}

    public UserTrainingSession save(UserTrainingSession userTrainingSession) {
        this.mapper.save(userTrainingSession);
        return userTrainingSession;
    }

    public UserTrainingSession getUserTrainingSessionById(String eventId, String email) {
        return this.mapper.load(UserTrainingSession.class, eventId, email);
    }


    public List<UserTrainingSession> getNextWeekOfUserTrainingSessions(String email) {
        log.info("In DAO callign getNextWeekOfUSerTrainingSessions");
        long timeMin = System.currentTimeMillis()/1000;
        long timeMax = timeMin + 604800;
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":email", new AttributeValue().withS(email));
        valueMap.put(":startDate" , new AttributeValue().withN(Long.toString(timeMin)));
        valueMap.put(":endDate" , new AttributeValue().withN(Long.toString(timeMax)));
        DynamoDBQueryExpression<UserTrainingSession> queryExpression = new DynamoDBQueryExpression<UserTrainingSession>()
                .withKeyConditionExpression("email= :email and timeAndDate between :startDate and :endDate")
                .withExpressionAttributeValues(valueMap);
        PaginatedQueryList<UserTrainingSession> sessions= this.mapper.query(UserTrainingSession.class, queryExpression);
        log.info("Retrieved dynamodb response {}", sessions);
        return sessions.stream()
                .filter(userTrainingSession -> !userTrainingSession.getAttended())
                .collect(Collectors.toList());
    }


}


