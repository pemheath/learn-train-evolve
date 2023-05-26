package LearnTrainEvolve.converters;
import LearnTrainEvolve.dynamodb.models.User;
import LearnTrainEvolve.models.UserModel;

public class ModelConverter {

    public UserModel toUserModel(User user) {

        return UserModel.builder()
                .withEmail(user.getEmail())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withMembership(user.getMembership())
                .withRank(user.getRank())
                .build();

    }
}
