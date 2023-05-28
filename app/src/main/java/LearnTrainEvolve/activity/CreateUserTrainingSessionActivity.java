package LearnTrainEvolve.activity;

import LearnTrainEvolve.activity.requests.CreateUserTrainingSessionRequest;
import LearnTrainEvolve.activity.responses.CreateUserTrainingSessionResponse;

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
public class CreateUserTrainingSessionActivity {

    private final Logger log = LogManager.getLogger();
    private final UserDao userDao;


    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param userDao PlaylistDao to access the playlists table.
     */
    @Inject
    public CreateUserTrainingSessionActivity(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * This method handles the incoming request by persisting a new user.
     * with the provided user email, first name, last name, membership, and rank from the request.
     * <p>
     * It then returns the newly created playlist.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param request AdminUserRequest object containing the playlist name and customer ID
     *                              associated with it
     * @return adminCreateUserResponse result object containing the API defined {@link UserModel}
     * @throws LearnTrainEvolve.exceptions.InvalidRequestException when playlist name or customerID is invalid.
     */

    public CreateUserTrainingSessionResponse handleRequest(final CreateUserTrainingSessionRequest request) {
        log.info("Received CreateUserRequest{}", request);

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setMembership(request.getMembership());
        newUser.setRank(request.getRank());

        userDao.saveUser(newUser);

        UserModel userModel = new ModelConverter().toUserModel(newUser);

        return null;


    }

}
