package learntrainevolve.dynamodb;

import learntrainevolve.dynamodb.models.TrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.exceptions.TrainingSessionNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Singleton
public class TrainingSessionDao {

    private final DynamoDBMapper mapper;
    private final Logger log = LogManager.getLogger();

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
        return "Training Sessions Successfully added";} catch (Exception e){
            return "Failure";
        }
    }

    public TrainingSession getTrainingSessionById(String eventId) {
        TrainingSession trainingSession = this.mapper.load(TrainingSession.class, eventId);
        if(trainingSession!=null) {
            return trainingSession;
        }
        else{
            throw new TrainingSessionNotFoundException(String.format("Training session with eventID %s does not exist", eventId));
        }
    }




    public List<TrainingSession> getUpcomingTrainingSessions() {
            log.info("in dao");
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
            log.info("SessionList retrieved from mapper, size{}", sessionList.size());
            if (sessionList == null) {
                return new ArrayList<>();
            }
            return sessionList.stream()
                    .filter(s-> !s.getIsCancelled())
                    .sorted(Comparator.comparing(TrainingSession::getTimeAndDate))
                    .collect(Collectors.toList());
    }

    public List<TrainingSession> getUpcomingTrainingSessionsByType(String category) {
  //      System.out.println("made it to dao");
        return getUpcomingTrainingSessions()
                .stream()
                .filter(s -> s.getType().equals(category))
                .filter(s-> !s.getIsCancelled())
                .sorted(Comparator.comparing(TrainingSession::getTimeAndDate))
                .collect(Collectors.toList());

    }



}


