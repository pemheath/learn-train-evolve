package learntrainevolve.dynamodb;

import learntrainevolve.dynamodb.models.UserTrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class UserTrainingSessionDao {

    private final DynamoDBMapper mapper;

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


    public PaginatedQueryList<UserTrainingSession> getNextWeekOfTrainingSessions(LocalDate today) {
        String startDate = today.toString();
        String endDate = today.plusDays(7).toString();
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":startDate" , new AttributeValue().withS(startDate));
        valueMap.put(":endDate" , new AttributeValue().withS(endDate));
        DynamoDBQueryExpression<UserTrainingSession> queryExpression = new DynamoDBQueryExpression<UserTrainingSession>()
                .withKeyConditionExpression("timeAndDate between :startDate and :endDate")
                .withExpressionAttributeValues(valueMap);
        return this.mapper.query(UserTrainingSession.class, queryExpression);
    }


}


