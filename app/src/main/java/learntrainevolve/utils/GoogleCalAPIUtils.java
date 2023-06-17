package learntrainevolve.utils;

 import dagger.Provides;
 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import software.amazon.awssdk.regions.Region;
 import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
 import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
 import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;



public class GoogleCalAPIUtils {


    /**
     * @param secretName
     * @return Secret
     * @throws Exception
     */

    public static String getSecret(String secretName) throws Exception{



        Region region = Region.of("us-east-2");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }

        return getSecretValueResponse.secretString();
    }
}
