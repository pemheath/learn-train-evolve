package LearnTrainEvolve.dynamodb;

import LearnTrainEvolve.dynamodb.models.TrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


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


    public PaginatedScanList<TrainingSession> getFutureTrainingSessions() {
            System.out.println("reached getFutureTrainingSessions method in TrainingDao");

            Map<String, AttributeValue> valueMap = new HashMap<>();
            valueMap.put(":startDate", new AttributeValue().withS(LocalDateTime.now().toString()));
            System.out.println("value map created" + valueMap);
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                    .withFilterExpression("timeAndDate > :startDate")
                    .withExpressionAttributeValues(valueMap);

            System.out.println("scan-expression created.");

            PaginatedScanList<TrainingSession> sessionList = mapper.scan(TrainingSession.class, scanExpression);

            System.out.println("list exists");

            return sessionList;



    }

}


