package LearnTrainEvolve.lambda;

import LearnTrainEvolve.activity.requests.CreateUserTrainingSessionRequest;
import LearnTrainEvolve.activity.responses.CreateUserTrainingSessionResponse;
import LearnTrainEvolve.lambda.infrastructure.LambdaActivityRunner;
import LearnTrainEvolve.lambda.infrastructure.LambdaResponse;
import LearnTrainEvolve.lambda.infrastructure.auth.AuthenticatedLambdaRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;


public class CreateUserTrainingSessionLambda
        extends LambdaActivityRunner<CreateUserTrainingSessionRequest, CreateUserTrainingSessionResponse>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateUserTrainingSessionRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateUserTrainingSessionRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateUserTrainingSessionRequest unauthenticatedRequest = input.fromBody(CreateUserTrainingSessionRequest.class);
                    return input.fromUserClaims(claims ->
                            CreateUserTrainingSessionRequest.builder()
                                    .withTimeAndDate(unauthenticatedRequest.getTimeAndDate())
                                    .withEventId(unauthenticatedRequest.getEventId())
                                    .withType(unauthenticatedRequest.getType())
                                    .withEmail(claims.getEmail())
                                    .build());
                },

                (request, serviceComponent) ->
                        serviceComponent.provideCreateUserTrainingSessionActivity().handleRequest(request)
        );
    }
}


