package learntrainevolve.lambda.infrastructure.auth;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;

/**
 * An abstract class that processes a cognito Authorization Header or JWT into Cognito claims.
 */
public abstract class CognitoJwtProcessor {
    protected final JwtConsumer jwtConsumer;

    /**
     * Construct a new processor that uses the given JtwConsumer.
     * @param jwtConsumer Used internally to handle the JWT.
     */
    protected CognitoJwtProcessor(JwtConsumer jwtConsumer) {
        this.jwtConsumer = jwtConsumer;
    }

    /**
     * Extract the JWT from a Authorization Bearer token and process it to get claims.
     * @param authHeader The authorization header that includes the word "Bearer".
     * @return Cognitoclaims extracted from the JWT.
     */
    public CognitoClaims processAuthorizationHeader(String authHeader) {
        String jwt;

        try {
            jwt = authHeader.split("\\s")[1];
        } catch (Exception e) {
            throw new RuntimeException("ERROR! Unable to extract JWT from authorization header.", e);
        }

        return processJwt(jwt);
    }

    /**
     * Extract the JWT from a Authorization Bearer token and process it to get claims.
     * @param jwt The token to process.
     * @return Cognitoclaims extracted from the JWT.
     */
    public CognitoClaims processJwt(String jwt) {
        JwtClaims jwtClaims;

        try {
            jwtClaims = jwtConsumer.processToClaims(jwt);
        } catch (InvalidJwtException e) {
            throw new RuntimeException("ERROR! Unable to process JWT.", e);
        }

        return new CognitoClaims(jwtClaims);
    }
}
