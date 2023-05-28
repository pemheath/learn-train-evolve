package LearnTrainEvolve.activity;

import LearnTrainEvolve.activity.requests.CreateUserTrainingSessionRequest;
import LearnTrainEvolve.activity.responses.CreateUserTrainingSessionResponse;

import LearnTrainEvolve.converters.ModelConverter;

import LearnTrainEvolve.dynamodb.UserTrainingSessionDao;
import LearnTrainEvolve.dynamodb.models.UserTrainingSession;
import LearnTrainEvolve.models.UserTrainingSessionModel;
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
    private final UserTrainingSessionDao userTrainingSessionDao;


    /**
     * Instantiates a new CreatePlaylistActivity object.
     *
     * @param userTrainingSessionDao PlaylistDao to access the userTrainingSessions table.
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
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     *
     * @param request CreateUserTrainingSession request object containing the playlist name and customer ID
     *                              associated with it
     * @return adminCreateUserResponse result object containing the API defined {@link UserModel}
     * @throws LearnTrainEvolve.exceptions.InvalidRequestException when playlist name or customerID is invalid.
     */

    public CreateUserTrainingSessionResponse handleRequest(final CreateUserTrainingSessionRequest request) {
        log.info("Received CreateUserRequest{}", request);

        UserTrainingSession userTrainingSession = new UserTrainingSession();


        UserTrainingSessionModel userTrainingSessionModel = new ModelConverter().toUserTrainingSessionModel(userTrainingSession);

        return null;


    }

}
