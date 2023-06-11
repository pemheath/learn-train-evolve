package learntrainevolve.dynamodb;

import learntrainevolve.dynamodb.models.TrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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

    public String saveList(List<TrainingSession> trainingSessions) {
        try{
        this.mapper.batchSave(trainingSessions);
        return "Success";} catch (Exception e){
            return "Failure";
        }
    }

    public TrainingSession getTrainingSessionById(String eventId) {
        return this.mapper.load(TrainingSession.class, eventId);
    }


    public List<TrainingSession> getUpcomingTrainingSessions() {
            Map<String, AttributeValue> valueMap = new HashMap<>();
            Long epochStart = System.currentTimeMillis()/1000;
            System.out.println(epochStart);
            Long epochEnd = epochStart + 604800;
            valueMap.put(":startDate", new AttributeValue().withN(String.valueOf(epochStart)));
            valueMap.put(":endDate", new AttributeValue().withN(String.valueOf(epochEnd)));
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                    .withFilterExpression("timeAndDate >= :startDate and timeAndDate <= :endDate")
                    .withExpressionAttributeValues(valueMap);

            PaginatedScanList<TrainingSession> sessionList = mapper.scan(TrainingSession.class, scanExpression);
            if (sessionList == null) {
                return new ArrayList<>();
            }
            return sessionList.stream()
                    .filter(s-> !s.getIsCancelled())
                    .collect(Collectors.toList());
    }

    public List<TrainingSession> getUpcomingTrainingSessionsByType(String category) {
  //      System.out.println("made it to dao");
        return getUpcomingTrainingSessions()
                .stream()
                .filter(s -> s.getType().equals(category))
                .filter(s-> !s.getIsCancelled())
                .collect(Collectors.toList());

    }



}


