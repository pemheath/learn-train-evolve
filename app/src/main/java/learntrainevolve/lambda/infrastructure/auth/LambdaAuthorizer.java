package learntrainevolve.lambda.infrastructure.auth;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminGetUserRequest;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * You use a Lambda authorizer to use a Lambda function to control access to your HTTP API.
 * Then, when a client calls your API, API Gateway invokes your Lambda function.
 * API Gateway uses the response from your Lambda function to determine whether the client can access your API.
 * <a href="https://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-lambda-authorizer-input.html">AWS Docs</a>
 */
public class LambdaAuthorizer implements RequestHandler<LambdaAuthorizerRequest, LambdaAuthorizerResult> {
    private static final Logger log = LogManager.getLogger();

    //These fields are effectively constants, so use UPPER_CASE.
    //CHECKSTYLE:OFF:MemberName
    private final String REQUIRED_GROUP;
    private final String COGNITO_REGION;
    private final String COGNITO_USER_POOL_ID;
    private final String COGNITO_USER_POOL_CLIENT_ID;
    //CHECKSTYLE:ON

    /**
     * Construct a LambdaAuthorizer.
     * This constructor assumes the necessary environment variables exist.
     */
    public LambdaAuthorizer() {
        REQUIRED_GROUP = System.getenv("REQUIRED_GROUP");
        COGNITO_REGION = System.getenv("COGNITO_REGION");
        COGNITO_USER_POOL_ID = System.getenv("COGNITO_USER_POOL_ID");
        COGNITO_USER_POOL_CLIENT_ID = System.getenv("COGNITO_USER_POOL_CLIENT_ID");

        log.info("LambdaAuthorizer instantiated. Env vars: " +
                        "REQUIRED_GROUP: '{}', COGNITO_REGION: '{}', " +
                        "COGNITO_USER_POOL_ID: '{}', COGNITO_USER_POOL_CLIENT_ID: '{}'",
                REQUIRED_GROUP, COGNITO_REGION, COGNITO_USER_POOL_ID, COGNITO_USER_POOL_CLIENT_ID);
    }

    @Override
    public LambdaAuthorizerResult handleRequest(LambdaAuthorizerRequest input, Context context) {
        log.info("Received authorization request: {}", input);

        try {
            CognitoJwtProcessor jwtProcessor = new ValidatingCognitoJwtProcessor(
                    COGNITO_REGION, COGNITO_USER_POOL_ID, COGNITO_USER_POOL_CLIENT_ID);
            CognitoClaims userClaims = jwtProcessor.processAuthorizationHeader(input.getAuthorizationToken());

            LambdaAuthorizerResult result = userExists(userClaims) && userInRequiredGroup(userClaims) ?
                    LambdaAuthorizerResult.authorized(input, userClaims.getUsername()) :
                    LambdaAuthorizerResult.unauthorized(input, userClaims.getUsername());

            log.info("Returning LambdaAuthorizerRResult: {}", result);

            return result;
        } catch (Exception e) {
            log.error("ERROR! An error occurred while attempting to authorize API request. " +
                    "Returning 'unauthorized' result.", e);

            return LambdaAuthorizerResult.error(input);
        }
    }


    private boolean userInRequiredGroup(CognitoClaims claims) {
        return claims.getCognitoGroups().contains(REQUIRED_GROUP);
    }

    private boolean userExists(CognitoClaims claims) {
        AWSCognitoIdentityProvider cognito = AWSCognitoIdentityProviderClientBuilder.standard()
                .withRegion(COGNITO_REGION)
                .build();

        AdminGetUserRequest request = new AdminGetUserRequest()
                .withUsername(claims.getUsername().toString())
                .withUserPoolId(COGNITO_USER_POOL_ID);

        // I'm not proud of using exceptions for control flow.
        try {
            cognito.adminGetUser(request);
            return true;
        } catch (UserNotFoundException e) {
            return false;
        }
    }
}
