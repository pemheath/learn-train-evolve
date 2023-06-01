package LearnTrainEvolve.converters;

import LearnTrainEvolve.dynamodb.models.TrainingSession;
import LearnTrainEvolve.dynamodb.models.UserTrainingSession;
import LearnTrainEvolve.models.TrainingSessionModel;
import LearnTrainEvolve.models.UserTrainingSessionModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ModelConverter {

    public UserTrainingSessionModel toUserTrainingSessionModel(UserTrainingSession userTrainingSession) {

        return UserTrainingSessionModel.builder()
                .withEmail(userTrainingSession.getEmail())
                .withEventId(userTrainingSession.getEventId())
                .withTimeAndDate(userTrainingSession.getTimeandDate())
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
        LocalDateTime dateTime = LocalDateTime.parse(trainingSession.getTimeAndDate(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return TrainingSessionModel.builder()
                .withEventId(trainingSession.getEventId())
                .withTimeAndDate(dateTime)
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