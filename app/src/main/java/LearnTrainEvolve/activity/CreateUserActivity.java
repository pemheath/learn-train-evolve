package LearnTrainEvolve.activity;

import LearnTrainEvolve.activity.requests.CreateUserRequest;
import LearnTrainEvolve.activity.responses.CreateUserResponse;
import LearnTrainEvolve.dynamodb.UserDao;
import javax.inject.Inject;



/**
 * Implementation of the CreatePlaylistActivity for the MusicPlaylistService's CreatePlaylist API.
 *
 * This API allows the customer to create a new playlist with no songs.
 */
public class CreateUserActivity {

    private final UserDao userDao;

    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param userDao PlaylistDao to access the playlists table.
     */
    @Inject
    public CreateUserActivity(UserDao userDao) {
        this.userDao = userDao;
    }


    public CreateUserResponse handleRequest(final CreateUserRequest createuserRequest)
    {
        return new CreateUserResponse();

    }

}
