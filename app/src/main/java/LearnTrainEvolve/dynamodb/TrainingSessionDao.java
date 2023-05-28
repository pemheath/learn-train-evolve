package LearnTrainEvolve.dynamodb;

import LearnTrainEvolve.dynamodb.models.TrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class TrainingSessionDao {

    private final DynamoDBMapper mapper;

    @Inject
    public TrainingSessionDao(DynamoDBMapper mapper) {
        this.mapper=mapper;}

    public TrainingSession save(TrainingSession trainingSession) {
        this.mapper.save(trainingSession);
        return trainingSession;
    }

    public TrainingSession getTrainingSessionById(String eventId) {
        return this.mapper.load(TrainingSession.class, eventId);
    }


    public PaginatedQueryList<TrainingSession> getNextWeekOfTrainingSessions(LocalDate today) {
        String startDate = today.toString();
        String endDate = today.plusDays(7).toString();
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":startDate" , new AttributeValue().withS(startDate));
        valueMap.put(":endDate" , new AttributeValue().withS(endDate));
        DynamoDBQueryExpression<TrainingSession> queryExpression = new DynamoDBQueryExpression<TrainingSession>()
                .withKeyConditionExpression("timeAndDate between :startDate and :endDate")
                .withExpressionAttributeValues(valueMap);
        return this.mapper.query(TrainingSession.class, queryExpression);
    }


}


