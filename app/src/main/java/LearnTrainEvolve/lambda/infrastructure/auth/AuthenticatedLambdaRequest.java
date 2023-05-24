package LearnTrainEvolve.lambda.infrastructure.auth;

import LearnTrainEvolve.lambda.infrastructure.LambdaRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Represents a generic, authenticated "APIGateway" request made to a lambda function.
 * @param <T> The type of the concrete request that should be created from this LambdaRequest
 */
public class AuthenticatedLambdaRequest<T> extends LambdaRequest<T> {

    /**
     * Use the given converter to create an instance of T from the claims included in the request's JWT token.
     * @param converter Contains the conversion code
     * @return A instance of T that contains data from the request's claims.
     */
    public T fromUserClaims(Function<CognitoClaims, T> converter) {
        try {
            return converter.apply(getClaims());
        } catch (Exception e) {
            throw new RuntimeException("Unable to get user information from request.", e);
        }
    }

    private CognitoClaims getClaims() {
        CognitoJwtProcessor jwtProcessor = new NonvalidatingCognitoJwtProcessor();
        return jwtProcessor.processAuthorizationHeader(super.getHeaders().get("Authorization"));
    }
}
