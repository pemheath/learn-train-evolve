package learntrainevolve.activity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import learntrainevolve.activity.requests.CreateUserTrainingSessionRequest;
import learntrainevolve.activity.responses.CreateUserTrainingSessionResponse;

import learntrainevolve.converters.ModelConverter;

import learntrainevolve.dynamodb.UserTrainingSessionDao;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.exceptions.InvalidRequestException;
import learntrainevolve.models.UserTrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;





/**
 * Implementation of the CreateUserTrainingSessionActivity for the LearnTrainEvolve API
 *
 * This API allows the customer to sign up for a training session, thereby creating a user training session unique to their email.
 */
public class CreateUserTrainingSessionActivity {

    private final Logger log = LogManager.getLogger();
    private final UserTrainingSessionDao userTrainingSessionDao;

    /**
     * Instantiates a new CreateUserTrainingSessionActivity object.
     *
     * @param userTrainingSessionDao UserTrainingSessionDao to access the userTrainingSessions table.
     */
    @Inject
    public CreateUserTrainingSessionActivity(UserTrainingSessionDao userTrainingSessionDao) {
        this.userTrainingSessionDao = userTrainingSessionDao;

    }

    /**
     * This method handles the incoming request by persisting a new user training session.
     * with the provided TrainingSession information (eventId, type, date and isCancelled and the user email.
     *
     * It then returns the newly created UserTrainingSession.
     *
     * If the provided request does not contain both an email and event ID, throws an
     * InvalidRequestException
     *
     * @param request CreateUserTrainingSession request object containing the
     *                email, eventId, type of class, coach, and time/date associated with the TrainingSession
     *                the user is signing up for.
     *
     * @return CreateUserTrainingSessionResponse response object containing the API defined {@link UserTrainingSessionModel}
     * @throws learntrainevolve.exceptions.InvalidRequestException when key attributes are undefined or invalid.
     */

    public CreateUserTrainingSessionResponse handleRequest(final CreateUserTrainingSessionRequest request) {
        log.info("Received CreateUserTrainingSessionRequest{}", request);
        if (request.getEventId()==null || request.getEmail()==null) {
            throw new InvalidRequestException("Key values must be provided");
        }
        if (!request.getEmail().contains("@")) {
            throw new InvalidRequestException("The email provided does not appear to be a properly formatted email");
        }

        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEventId(request.getEventId());
        userTrainingSession.setEmail(request.getEmail());
        userTrainingSession.setType(request.getType());
        userTrainingSession.setCoach(request.getCoach());
        userTrainingSession.setTimeAndDate(request.getTimeAndDate());
        userTrainingSession.setAttended(false);

        try{userTrainingSessionDao.save(userTrainingSession);
        log.info("Saved UserTrainingSession {}", userTrainingSession);} catch (DynamoDBMappingException e) {
            log.error(e.getMessage());
            throw new InvalidRequestException(e.getMessage(), e.getCause());
        }

        UserTrainingSessionModel userTrainingSessionModel = new ModelConverter().toUserTrainingSessionModel(userTrainingSession);

        return CreateUserTrainingSessionResponse.builder()
                .withUserTrainingSessionModel(userTrainingSessionModel)
                .build();

    }

}
