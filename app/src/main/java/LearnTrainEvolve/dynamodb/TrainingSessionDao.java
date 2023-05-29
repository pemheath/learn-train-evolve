package LearnTrainEvolve.dynamodb;

import LearnTrainEvolve.dynamodb.models.TrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static LearnTrainEvolve.dynamodb.models.TrainingSession.TIME_AND_DATE_INDEX;

@Singleton
public class TrainingSessionDao {

    private final DynamoDBMapper mapper;

    @Inject
    public TrainingSessionDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public TrainingSession save(TrainingSession trainingSession) {
        this.mapper.save(trainingSession);
        return trainingSession;
    }

    public TrainingSession getTrainingSessionById(String eventId) {
        return this.mapper.load(TrainingSession.class, eventId);
    }


    public PaginatedQueryList<TrainingSession> getFutureTrainingSessions(LocalDateTime date) {


            Map<String, AttributeValue> valueMap = new HashMap<>();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            String startDate = dateFormatter.format(date);
            valueMap.put(":startDate", new AttributeValue().withS(startDate));
            DynamoDBQueryExpression<TrainingSession> queryExpression = new DynamoDBQueryExpression<TrainingSession>()
                    .withIndexName(TIME_AND_DATE_INDEX)
                    .withKeyConditionExpression("timeAndDate > :startDate")
                    .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<TrainingSession> list = this.mapper.query(TrainingSession.class, queryExpression);
            for (TrainingSession session : list) {
                System.out.println(session.toString());
            }

            return list;


    }

}


