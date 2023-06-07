package learntrainevolve.activity;

import learntrainevolve.activity.requests.LogTrainingRequest;
import learntrainevolve.activity.responses.LogTrainingResponse;

import learntrainevolve.converters.ModelConverter;

import learntrainevolve.dynamodb.UserTrainingSessionDao;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.models.UserTrainingSessionModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the LogTrainingActivity for the LearnTrainEvolveService's LogTraining API.
 *
 * This API allows the user to mark a UserTrainingSession as attended and record data for that session. 
 */
public class LogTrainingActivity {

    private final Logger log = LogManager.getLogger();
    private final UserTrainingSessionDao userTrainingSessionDao;


    /**
     * Instantiates a new LogTrainingActivity object.
     *
     * @param userTrainingSessionDao PlaylistDao to access the userTrainingSessions table.
     */
    @Inject
    public LogTrainingActivity(UserTrainingSessionDao userTrainingSessionDao) {
        this.userTrainingSessionDao = userTrainingSessionDao;
    }
    /**
     * This method handles the incoming request by updating a user training session.
     * with the provided UserTrainingSession information (eventId, timeAndDate, type, coach, email, intensityRatung,
     * techniqueEnjoyment, performanceRating, noteNumber, goalNumber, tags, attended).
     *
     * It then returns the updated UserTrainingSession.
     * <p>
     * If the provided tags object is an invalid type, throws an
     * InvalidAttributeValueException
     *
     * @param request LogTraining request object containing the eventId, timeAndDate, type, coach, email, intensityRating,
     *      * techniqueEnjoyment, performanceRating, noteNumber, goalNumber, tags, and attended information.
     * @return LogTrainingResponse  object containing the API defined {@link UserTrainingSessionModel}
     * @throws learntrainevolve.exceptions.InvalidRequestException when tags or type are invalid.
     */

    public LogTrainingResponse handleRequest(final LogTrainingRequest request) {
        log.info("Received LogTrainingRequest{}", request);

        UserTrainingSession userTrainingSession = new UserTrainingSession();
        userTrainingSession.setEventId(request.getEventId());
        userTrainingSession.setType(request.getType());
        userTrainingSession.setEmail(request.getEmail());
        userTrainingSession.setTimeAndDate(request.getTimeAndDate());
        userTrainingSession.setCoach(request.getCoach());
        userTrainingSession.setIntensityRating(request.getIntensityRating());
        userTrainingSession.setPerformanceRating(request.getPerformanceRating());
        userTrainingSession.setTechniqueEnjoyment(request.getTechniqueEnjoyment());
        userTrainingSession.setNoteNumber(request.getNoteNumber());
        userTrainingSession.setGoalNumber(request.getGoalNumber());
        userTrainingSession.setTags(request.getTags());
        userTrainingSession.setAttended(request.getAttended());

        userTrainingSessionDao.save(userTrainingSession);
        log.info("Saved UserTrainingSession {}", userTrainingSession);


        UserTrainingSessionModel userTrainingSessionModel = new ModelConverter().toUserTrainingSessionModel(userTrainingSession);

        return LogTrainingResponse.builder()
                .withUserTrainingSessionModel(userTrainingSessionModel)
                .build();
    }

}
