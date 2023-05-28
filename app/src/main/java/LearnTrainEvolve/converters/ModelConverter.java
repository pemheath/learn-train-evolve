package LearnTrainEvolve.converters;

import LearnTrainEvolve.dynamodb.models.TrainingSession;
import LearnTrainEvolve.dynamodb.models.UserTrainingSession;
import LearnTrainEvolve.models.TrainingSessionModel;
import LearnTrainEvolve.models.UserTrainingSessionModel;

import java.util.List;
import java.util.stream.Collectors;

public class ModelConverter {

    public UserTrainingSessionModel toUserTrainingSessionModel(UserTrainingSession userTrainingSession) {

        return UserTrainingSessionModel.builder()
                .withEmail(userTrainingSession.getEmail())
                .withEventId(userTrainingSession.getEventId())
                .withDate(userTrainingSession.getDate())
                .withType(userTrainingSession.getType())
                .withIntensityRating(userTrainingSession.getIntensityRating())
                .withTechniqueEnjoyment(userTrainingSession.getTechniqueEnjoyment())
                .withPerformanceRating(userTrainingSession.getPerformanceRating())
                .withNoteNumber(userTrainingSession.getNoteNumber())
                .withGoalNumber(userTrainingSession.getGoalNumber())
                .withTags(userTrainingSession.getTags())
                .withAttended(userTrainingSession.getAttended())
                .build();
    }

    public TrainingSessionModel toTrainingSessionModel(TrainingSession trainingSession) {
        return TrainingSessionModel.builder()
                .withEventId(trainingSession.getEventId())
                .withTimeAndDate(trainingSession.getTimeAndDate())
                .withType(trainingSession.getType())
                .withIsCancelled(trainingSession.getIsCancelled())
                .build();
    }

    public List<TrainingSessionModel> toListOfTrainingSessionModels(List<TrainingSession> trainingSessions) {
        return trainingSessions.stream()
                .map(this::toTrainingSessionModel)
                .collect(Collectors.toList());
    }

}