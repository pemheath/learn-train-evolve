package learntrainevolve.lambda.infrastructure.auth;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Represents the response of an API Gateway authorization request.
 * This class represents the simplest possible response.
 * <a href="https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-lambda-authorizer-output.html">
 * AWS Docs.
 * </a>
 */
public class LambdaAuthorizerResult {
    private final String principalId;
    private final String apiArn;
    private final boolean isAuthorized;

    private LambdaAuthorizerResult(String principalId, String apiArn, boolean isAuthorized) {
        this.principalId = principalId;
        this.apiArn = apiArn;
        this.isAuthorized = isAuthorized;
    }

    /**
     * Construct a new LambdaAuthorizerResult in which the request is allowed.
     *
     * @param request     The request that is being allowed.
     * @param principalId The an id to use to group requests. Likely the cognito username.
     * @return An authorized result.
     */
    public static LambdaAuthorizerResult authorized(LambdaAuthorizerRequest request, UUID principalId) {
        return new LambdaAuthorizerResult(principalId.toString(), request.getMethodArn(), true);
    }

    /**
     * Construct a new LambdaAuthorizerResult in which the request is denied.
     *
     * @param request     The request that is being denied.
     * @param principalId The an id to use to group requests. Likely the cognito username.
     * @return An unauthorized result.
     */
    public static LambdaAuthorizerResult unauthorized(LambdaAuthorizerRequest request, UUID principalId) {
        return new LambdaAuthorizerResult(principalId.toString(), request.getMethodArn(), false);
    }

    /**
     * Construct a new LambdaAuthorizerResult in which the request is denied as the result of an error.
     *
     * @param request The request that is being denied.
     * @return An unauthorized result.
     */
    public static LambdaAuthorizerResult error(LambdaAuthorizerRequest request) {
        return new LambdaAuthorizerResult("ERROR", request.getMethodArn(), false);
    }

    public String getPrincipalId() {
        return principalId;
    }

    public Map<String, Object> getPolicyDocument() {
        return Map.of(
                "Version", "2012-10-17",
                "Statement", List.of(
                        Map.of(
                                "Action", "execute-api:Invoke",
                                "Effect", isAuthorized ? "Allow" : "Deny",
                                "Resource", apiArn
                        )
                )
        );
    }

    @Override
    public String toString() {
        return "LambdaAuthorizerResult{" +
                "principalId='" + principalId + '\'' +
                ", apiArn='" + apiArn + '\'' +
                ", isAuthorized=" + isAuthorized +
                '}';
    }
}
