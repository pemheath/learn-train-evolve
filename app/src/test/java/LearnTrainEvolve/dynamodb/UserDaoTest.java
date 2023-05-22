package LearnTrainEvolve.dynamodb;

import LearnTrainEvolve.dynamodb.models.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @InjectMocks
    public UserDao userDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
        userDao = new UserDao(dynamoDBMapper);
    }

    @Test
    public void saveUser_validUser_returnsUser() {
        User userMocked = new User();
        userMocked.setFirstName("Benjie");
        userMocked.setLastName("Franklin");


    }



}