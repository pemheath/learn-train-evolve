package LearnTrainEvolve.lambda.infrastructure.auth;

import org.jose4j.jwt.consumer.JwtConsumerBuilder;

/**
 * Represents a CognitoJwtProcessor that does NOT validate the JWT.
 * This class should ONLY be used to extract claims from TRUSTED JWTs.
 */
public class NonvalidatingCognitoJwtProcessor extends CognitoJwtProcessor {

    /**
     * Construct a new NonvalidatingCognitoJwtProcessor.
     */
    public NonvalidatingCognitoJwtProcessor() {
        super(new JwtConsumerBuilder()
                .setSkipAllValidators()
                .setDisableRequireSignature()
                .setSkipSignatureVerification()
                .build());
    }
}
