package LearnTrainEvolve.lambda;

import LearnTrainEvolve.activity.requests.CreateUserTrainingSessionRequest;
import LearnTrainEvolve.activity.responses.CreateUserTrainingSessionResponse;
import LearnTrainEvolve.lambda.infrastructure.auth.AuthenticatedLambdaRequest;
import LearnTrainEvolve.lambda.infrastructure.LambdaActivityRunner;
import LearnTrainEvolve.lambda.infrastructure.LambdaResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminCreateUserLambda
        extends LambdaActivityRunner<CreateUserTrainingSessionRequest, CreateUserTrainingSessionResponse>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateUserTrainingSessionRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateUserTrainingSessionRequest> input, Context context) {
        log.info("Received input: '{}'", input.toString());

        return super.runActivity(
            () -> {
                CreateUserTrainingSessionRequest authenticatedRequest = input.fromBody(CreateUserTrainingSessionRequest.class);
                log.info("Our request: '{}'", authenticatedRequest.toString());
                return CreateUserTrainingSessionRequest.builder()
                        .withEmail(authenticatedRequest.getEmail())
                        .withEventId(authenticatedRequest.getEventId())
                        .build();
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateUserTrainingSessionActivity().handleRequest(request)
        );
    }
}


