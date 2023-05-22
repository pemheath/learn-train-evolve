package LearnTrainEvolve.lambda;

import LearnTrainEvolve.activity.requests.CreateUserRequest;
import LearnTrainEvolve.activity.responses.CreateUserResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateUserLambda
        extends LambdaActivityRunner<CreateUserRequest, CreateUserResponse>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateUserRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateUserRequest> input, Context context) {
        log.info("Received input: '{}'", input.toString());

        return super.runActivity(
            () -> {
                CreateUserRequest authenticatedRequest = input.fromBody(CreateUserRequest.class);
                log.info("Our request: '{}'", authenticatedRequest.toString());
                return CreateUserRequest.builder()
                        .withEmail(authenticatedRequest.getEmail())
                        .withFirstName(authenticatedRequest.getFirstName())
                        .withLastName(authenticatedRequest.getLastName())
                        .withMembership(authenticatedRequest.getMembership())
                        .withRank(authenticatedRequest.getRank())
                        .build();
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateUserActivity().handleRequest(request)
        );
    }
}

