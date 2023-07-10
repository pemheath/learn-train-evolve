package learntrainevolve.dynamodb;

import learntrainevolve.dynamodb.models.UserTrainingSession;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import learntrainevolve.exceptions.UserTrainingSessionNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Accesses data for a TrainingSession using {@link learntrainevolve.models.UserTrainingSessionModel} to represent the model in DynamoDB.
 */

@Singleton
public class UserTrainingSessionDao {

    private final DynamoDBMapper mapper;

    private final Logger log = LogManager.getLogger();


    /**
     * Instantiates an TrainingSessionDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the training session table
     */
    @Inject
    public UserTrainingSessionDao(DynamoDBMapper mapper) {
        this.mapper=mapper;}


    /**
     * Saves (creates or updates) the given user training session.
     *
     * @param userTrainingSession The training session to save
     * @return The UserTrainingSession object that was saved
     */

    public UserTrainingSession save(UserTrainingSession userTrainingSession) {

        this.mapper.save(userTrainingSession);
        return userTrainingSession;
    }

    /**
     * Retrieves a UserTrainingSession by Email and EventId
     *
     * If not found, throws TrainingSessionNotFoundException.
     *
     *
     * @param eventId The eventId for the user training session
     * @param email The email for the user training session
     *
     * @return The corresponding UserTrainingSession, if found
     * @throws UserTrainingSessionNotFoundException if no training session exists
     */

    public UserTrainingSession getUserTrainingSessionById(String email, String eventId) {
        UserTrainingSession result = this.mapper.load(UserTrainingSession.class, email, eventId);
        if(result!=null) {
            return result;
        }
        else{
            throw new UserTrainingSessionNotFoundException(String.format("No user training session matches the provided email %S and eventID %S", email, eventId));
        }
    }

    /**
     * Retrieves a list of UserTrainingSessions matching the given email occurring within one week on of the time of request
     *
     * If not found, throws TrainingSessionNotFoundException.
     *
     * This method uses email as the partition key to retrieve all user training sessions matching the email.
     *
     * The list of user training sessions is then sorted for sessions occurring from seven days in the past to seven days in the future.
     *
     *  This method should be revised to leverage a Local Secondary Index with email as partition key and timeAndDate as sort key.
     *  Revising it requires recreating the dynamodb table with the required LSI.
     *
     * @param email The email for the user training session
     *
     * @return The corresponding list of  UserTrainingSessions, if found
     * @throws UserTrainingSessionNotFoundException if no training session match the criteria
     */


    public List<UserTrainingSession> getTwoWeeksOfUserTrainingSessions(String email) {
        log.info("In UserTrainingSessionDao calling getNextWeekOfUSerTrainingSessions with email {}", email);
        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEmail(email);

        DynamoDBQueryExpression<UserTrainingSession> queryExpression = new DynamoDBQueryExpression<UserTrainingSession>()
                .withHashKeyValues(userTrainingSession);
        List<UserTrainingSession> sessions;
        try {
            sessions = this.mapper.query(UserTrainingSession.class, queryExpression);
            log.info("Retrieved dynamodb response {}", sessions);
            long currentTime = (System.currentTimeMillis()/1000)-604800;
            return sessions.stream()
                    .filter(s -> s.getTimeAndDate()>=currentTime)
                    .sorted(Comparator.comparing(UserTrainingSession::getTimeAndDate))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new UserTrainingSessionNotFoundException(e.getMessage(), e.getCause());
        }
    }


    /**
     * Retrieves a list of attended UserTrainingSessions fo provide data for graphical display and analysis of training.
     *
     * This method uses email as the partition key to retrieve all user training sessions matching the email.
     *
     * The list of user training sessions is then sorted for sessions that have been attended.
     *
     *  This method should be revised to leverage a Local Secondary Index with email as partition key and timeAndDate as sort key.
     *  Revising it requires recreating the dynamodb table with the required LSI.
     *
     * @param email The email for the user training session
     *
     * @return The corresponding list of  UserTrainingSessions, if found
     * @throws UserTrainingSessionNotFoundException if no training session match the criteria
     */

    public List<UserTrainingSession> getUserTrainingSessionsForDataVis(String email) {
        log.info("In UserTrainingSessionDao calling getUserTrainingSessionsForDataVis with email {}", email);

        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEmail(email);

        DynamoDBQueryExpression<UserTrainingSession> queryExpression = new DynamoDBQueryExpression<UserTrainingSession>()
                .withHashKeyValues(userTrainingSession);
        List<UserTrainingSession> sessions;

        try {
            sessions = this.mapper.query(UserTrainingSession.class, queryExpression);
            log.info("Retrieved dynamodb response {}", sessions);
            // filter out unnecessary information so that a smaller set of data is transmitted.
            List<UserTrainingSession> sessionsToReturn = new ArrayList<>();

            for (UserTrainingSession session : sessions) {

                try {
                    if (session.getAttended()) {
                        sessionsToReturn.add(session);
                    }
                } catch (NullPointerException e) {
                    log.info("In sorting the attended sessions, a null pointer exception occurred.");
                }
            }
            log.info("Retrieved sessionsToReturn for data visualization {}", sessionsToReturn);
            return sessionsToReturn;
        } catch (Exception e) {
            throw new UserTrainingSessionNotFoundException(e.getMessage(), e.getCause());
        }

    }


}


