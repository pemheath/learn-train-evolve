package learntrainevolve.lambda;

import learntrainevolve.activity.requests.CreateUserTrainingSessionRequest;
import learntrainevolve.activity.responses.CreateUserTrainingSessionResponse;
import learntrainevolve.lambda.infrastructure.LambdaActivityRunner;
import learntrainevolve.lambda.infrastructure.LambdaResponse;
import learntrainevolve.lambda.infrastructure.auth.AuthenticatedLambdaRequest;
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
                    System.out.println(unauthenticatedRequest);
            //        return input.fromUserClaims(claims ->

                      return CreateUserTrainingSessionRequest.builder()
                                    .withEmail(unauthenticatedRequest.getEmail())
                                    .withTimeAndDate(unauthenticatedRequest.getTimeAndDate())
                                    .withEventId(unauthenticatedRequest.getEventId())
                                    .withType(unauthenticatedRequest.getType())
                                    .withCoach(unauthenticatedRequest.getCoach())
                                    .build();
                },

                (request, serviceComponent) ->
                        serviceComponent.provideCreateUserTrainingSessionActivity().handleRequest(request)
        );
    }
}


