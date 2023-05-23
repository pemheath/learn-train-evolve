package LearnTrainEvolve.dynamodb;

import LearnTrainEvolve.dynamodb.models.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;


class UserDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    public UserDao userDao;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userDao = new UserDao(dynamoDBMapper);
    }

    @Test
    public void saveUser_validUser_callsDynamoDBMapper() {
        User userMocked = new User();
        userMocked.setFirstName("Benjie");
        userMocked.setLastName("Franklin");
        userMocked.setEmail("benjieonehundo@alonttimeago.com");
        userMocked.setMembership("Unlimited");
        userMocked.setRank("Brown");
        doNothing().when(dynamoDBMapper).save(any(User.class));

        //WHEN savePlaylist method is called
        User user = userDao.saveUser(userMocked);

        // Verify call was made to dynamodb

        verify(dynamoDBMapper).save(userMocked);

    }


}