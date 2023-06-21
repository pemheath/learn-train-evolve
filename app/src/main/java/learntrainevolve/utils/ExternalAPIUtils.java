package learntrainevolve.utils;

 import learntrainevolve.exceptions.FailedSecretsAccessException;
 import software.amazon.awssdk.regions.Region;
 import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
 import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
 import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;


/**
 * A utility class for necessary operations to support  external API calls.
 */
public class ExternalAPIUtils {

    /**
     *A method for retrieving a secret from AWS Secrets Manager
     * @param secretName, the secret name as stored in the AWS Secrets Manager
     * @return Secret, the secret stored in the Secrets Manager
     * @throws FailedSecretsAccessException is the call to secrets manager is unsuccessful
     */

    public static String getSecret(String secretName) {

        //Specify the AWS region for the Secrets Manager Account
        Region region = Region.of("us-east-2");

        // Create a Secrets Manager client
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        //Build a GetSecretValueRequest object

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        // query the SecretsManagerClient for the secret value
        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            throw new FailedSecretsAccessException(e.getMessage(), e.getCause());
        }

        // return the secret

        return getSecretValueResponse.secretString();
    }
}
