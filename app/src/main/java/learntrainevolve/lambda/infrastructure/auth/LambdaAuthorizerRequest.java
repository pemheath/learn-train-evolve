package learntrainevolve.lambda.infrastructure.auth;

/**
 * Represents an authorization request sent by API Gateway when it invokes a custom Lambda Authorizer.
 * API Gateway makes an authorization request when a user requests an API endpoint that is protected by an authorizer.
 * The authorizationToken is the truly important part. It should contain a JWT with the current user's claims.
 * <a href="https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-lambda-authorizer-input.html">AWS Docs</a>
 */
public class LambdaAuthorizerRequest {
    private String type;
    private String authorizationToken;
    private String methodArn;

    /**
     * The type of the authorization request.
     * Valid types of authorization requests are "TOKEN" and "REQUEST".
     * This class models a "TOKEN" type of request, so this value should always be "TOKEN".
     * @return value of the type of request, should always be "TOKEN".
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the request type.
     * @param type the new request type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * The JWT containing the claims of the user making the authorization request.
     * This value comes from the authorization header of the original API gateway request.
     * @return value of the JWT.
     */
    public String getAuthorizationToken() {
        return authorizationToken;
    }

    /**
     * Set the authorization token.
     * @param authorizationToken the new token
     */
    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }


    /**
     * The ARN of the incoming method request and is populated by API Gateway in accordance with the
     * Lambda authorizer configuration.
     * @return value of the method arn.
     */
    public String getMethodArn() {
        return methodArn;
    }

    /**
     * Set the method arn.
     * @param methodArn the new method arn.
     */
    public void setMethodArn(String methodArn) {
        this.methodArn = methodArn;
    }

    @Override
    public String toString() {
        return "LambdaAuthorizerRequest{" +
                "type='" + type + '\'' +
                ", authorizationToken='" + authorizationToken + '\'' +
                ", methodArn='" + methodArn + '\'' +
                '}';
    }
}
