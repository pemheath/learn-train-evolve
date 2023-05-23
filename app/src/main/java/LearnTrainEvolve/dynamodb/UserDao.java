package LearnTrainEvolve.dynamodb;

import LearnTrainEvolve.dynamodb.models.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserDao {

    private final DynamoDBMapper dynamoDBMapper;

    @Inject
    public UserDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public User saveUser(User user) {
        this.dynamoDBMapper.save(user);
        return user;
    }

}
