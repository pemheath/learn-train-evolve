package LearnTrainEvolve.activity;

import LearnTrainEvolve.activity.requests.CreateUserRequest;
import LearnTrainEvolve.activity.responses.CreateUserResponse;
import LearnTrainEvolve.converters.ModelConverter;
import LearnTrainEvolve.dynamodb.UserDao;
import LearnTrainEvolve.dynamodb.models.User;
import LearnTrainEvolve.models.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;



/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 *
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreateUserActivity {

    private final UserDao userDao;
    private final Logger log = LogManager.getLogger();

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param userDao PlaylistDao to access the playlists table.
     */
    @Inject
    public CreateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }


    public CreateUserResponse handleRequest(final CreateUserRequest createuserRequest) {
        log.info("Received CreateUserRequest{}", createuserRequest);

        User newUser = new User();
        newUser.setEmail(createuserRequest.getEmail());
        newUser.setFirstName(createuserRequest.getFirstName());
        newUser.setLastName(createuserRequest.getLastName());
        newUser.setMotivationalWhy(createuserRequest.getMotivationalWhy());
        newUser.setMembership(createuserRequest.getMembership());
        newUser.setRank(createuserRequest.getRank());

        userDao.saveUser(newUser);

        UserModel userModel = new ModelConverter().toUserModel(newUser);

        return CreateUserResponse.builder()
                .withUser(userModel)
                .build();



    }

}
