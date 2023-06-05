package learntrainevolve.converters;

import learntrainevolve.dynamodb.models.TrainingSession;
import learntrainevolve.dynamodb.models.UserTrainingSession;
import learntrainevolve.models.TrainingSessionModel;
import learntrainevolve.models.UserTrainingSessionModel;

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
                .withCoach(userTrainingSession.getCoach())
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
                .withCoach(trainingSession.getCoach())
                .build();
    }

    public List<TrainingSessionModel> toListOfTrainingSessionModels(List<TrainingSession> trainingSessions) {
        return trainingSessions.stream()
                .map(this::toTrainingSessionModel)
                .collect(Collectors.toList());
    }

}