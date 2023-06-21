package learntrainevolve.dynamodb;

import learntrainevolve.dynamodb.models.TrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import learntrainevolve.exceptions.TrainingSessionNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Accesses data for a TrainingSession using {@link TrainingSession} to represent the model in DynamoDB.
 */
@Singleton
public class TrainingSessionDao {

    private final DynamoDBMapper mapper;
    private final Logger log = LogManager.getLogger();

    /**
     * Instantiates an TrainingSessionDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the training session table
     */

    @Inject
    public TrainingSessionDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Saves (creates or updates) the given training session.
     *
     * @param trainingSession The training session to save
     * @return The TrainingSession object that was saved
     */

    public TrainingSession save(TrainingSession trainingSession) {
        this.mapper.save(trainingSession);
        return trainingSession;
    }

    /**
     * Saves (creates or updates) the given list of training sessions.
     *
     * @param trainingSessions, The list of training session to save
     * @return A string message announcing the number of training sessions saved.
     */


    public String saveList(List<TrainingSession> trainingSessions) {
        try{
        this.mapper.batchSave(trainingSessions);
        return "Training Sessions Successfully added";} catch (Exception e){
            return "Failure";
        }
    }

    /**
     * Retrieves a TrainingSession by EventId
     *
     * If not found, throws TrainingSessionNotFoundException.
     *
     *
     * @param eventId The eventId to look up
     * @return The corresponding TrainingSession, if found
     * @throws TrainingSessionNotFoundException if no training session exists
     */

    public TrainingSession getTrainingSessionById(String eventId) {
        TrainingSession trainingSession = this.mapper.load(TrainingSession.class, eventId);
        if(trainingSession!=null) {
            return trainingSession;
        }
        else{
            throw new TrainingSessionNotFoundException(String.format("Training session with eventID %s does not exist", eventId));
        }
    }
    /**
     * Retrieves a List of TrainingSessions
     *
     * The list of sessions filters and keeps sessions that are not cancelled and occur within the next week.
     * If no sessions are found in DynamoDB, throws TrainingSessionNotFoundException.
     * If no sessions remain after the list is filtered for upcoming not-cancelled sessions, returns an empty list.
     * @return The List of TrainingSessions, sorted chronologically
     * @throws TrainingSessionNotFoundException if no training sessions are found
     */

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
                throw new TrainingSessionNotFoundException("no training sessions were retrieved");
            }
            return sessionList.stream()
                    .filter(s-> !s.getIsCancelled())
                    .sorted(Comparator.comparing(TrainingSession::getTimeAndDate))
                    .collect(Collectors.toList());
    }

    /**
     * Calls the above method, then filters to only return training sessions matching the provided type.
     * If no sessions remain after the list is filtered, retruns an empty list.
     * @return The List of TrainingSessions, sorted chronologically
     * @throws TrainingSessionNotFoundException by propagating that exception from the method getUpcomingTrainingSessions() if no sessions are found in the dynamodb table.
     */

    public List<TrainingSession> getUpcomingTrainingSessionsByType(String type) {
  //      System.out.println("made it to dao");
        return getUpcomingTrainingSessions()
                .stream()
                .filter(s -> s.getType().equals(type))
                .filter(s-> !s.getIsCancelled())
                .sorted(Comparator.comparing(TrainingSession::getTimeAndDate))
                .collect(Collectors.toList());
    }

}


