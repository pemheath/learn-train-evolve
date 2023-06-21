package learntrainevolve.converters;

import learntrainevolve.dynamodb.models.TrainingSession;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.models.TrainingSessionModel;
import learntrainevolve.models.UserTrainingSessionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * A converter class to translate Java POJOs to their corresponding model.
 */
public class ModelConverter {
    private final Logger log = LogManager.getLogger();

    /**
     *
     * @param userTrainingSession
     * @return a user training session model
     */
    public UserTrainingSessionModel toUserTrainingSessionModel(UserTrainingSession userTrainingSession) {
        UserTrainingSessionModel model = UserTrainingSessionModel.builder()
                .withEmail(userTrainingSession.getEmail())
                .withEventId(userTrainingSession.getEventId())
                .withTimeAndDate(userTrainingSession.getTimeAndDate())
                .withType(userTrainingSession.getType())
                .withCoach(userTrainingSession.getCoach())
                .withIntensityRating(userTrainingSession.getIntensityRating())
                .withTechniqueEnjoyment(userTrainingSession.getTechniqueEnjoyment())
                .withPerformanceRating(userTrainingSession.getPerformanceRating())
                .withNote(userTrainingSession.getNote())
                .withGoal(userTrainingSession.getGoal())
                .withTags(userTrainingSession.getTags())
                .withAttended(userTrainingSession.getAttended())
                .build();
        log.info("Model has data coach{}", model.getCoach());
        log.info("Model has email {}", model.getEmail());
        return model;
    }

    /**
     *
     * @param trainingSession
     * @return a training session model
     */

    public TrainingSessionModel toTrainingSessionModel(TrainingSession trainingSession) {
        return TrainingSessionModel.builder()
                .withEventId(trainingSession.getEventId())
                .withTimeAndDate(trainingSession.getTimeAndDate())
                .withType(trainingSession.getType())
                .withIsCancelled(trainingSession.getIsCancelled())
                .withCoach(trainingSession.getCoach())
                .build();
    }

    /**
     *
     * @param trainingSessions
     * @return a list of training session models
     */

    public List<TrainingSessionModel> toListOfTrainingSessionModels(List<TrainingSession> trainingSessions) {
        return trainingSessions.stream()
                .map(this::toTrainingSessionModel)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param userTrainingSessions
     * @return a list of user training session models
     */

    public List<UserTrainingSessionModel> toListOfUserTrainingSessionModels(List<UserTrainingSession> userTrainingSessions) {

        List<UserTrainingSessionModel> listToReturn = new ArrayList<>();
        for(UserTrainingSession session : userTrainingSessions) {
            UserTrainingSessionModel model = toUserTrainingSessionModel(session);
            log.info("session{} converted to model{}", session.toString(), model.toString());
            listToReturn.add(model);
        }
        return listToReturn;
    }

}