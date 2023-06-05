package learntrainevolve.lambda.infrastructure.auth;

import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;

/**
 * Represents a CognitoJwtProcessor that validates the JWT before extracting claims from it.
 * The "processXXX" methods in this class will throw an exception if the JWT is invalid.
 *
 * A JWT will be considered invalid in the following scenarios (this list may not be complete):
 * * The JWT signature is invalid.
 * * The JWT has expired.
 * * The JWT "issuer" is not equal to the cognito identity provider url.
 * * The JWT "audience" is not equal to the cognito user pool client id.
 * * The JWT does not contain these fields: exp, iss, aud, iat, sub, jti
 */
public class ValidatingCognitoJwtProcessor extends CognitoJwtProcessor {
    private static final String COGNITO_URL_FORMAT_STRING = "https://cognito-idp.%s.amazonaws.com/%s";
    private static final String JWKS_URL_FORMAT_STRING = COGNITO_URL_FORMAT_STRING + "/.well-known/jwks.json";

    /**
     * Construct a new ValidatingCognitoJwtProcessor.
     * @param region The aws region in which the cognito user pool is deployed.
     * @param userPoolId The id of the cognito user pool.
     * @param userPoolClientId The id of the cognito user pool client.
     */
    public ValidatingCognitoJwtProcessor(String region, String userPoolId, String userPoolClientId) {
        super(new JwtConsumerBuilder()
                .setVerificationKeyResolver(
                        new HttpsJwksVerificationKeyResolver(
                                new HttpsJwks(String.format(JWKS_URL_FORMAT_STRING, region, userPoolId))))
                .setExpectedIssuer(String.format(COGNITO_URL_FORMAT_STRING, region, userPoolId))
                .setExpectedAudience(userPoolClientId)
                .setRequireExpirationTime()
                .setRequireIssuedAt()
                .setRequireSubject()
                .setRequireJwtId()
                .build());
    }
}
