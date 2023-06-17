package learntrainevolve.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import learntrainevolve.activity.requests.SyncTrainingSessionsRequest;
import learntrainevolve.activity.responses.SyncTrainingSessionsResponse;
import learntrainevolve.lambda.infrastructure.LambdaActivityRunner;
import learntrainevolve.lambda.infrastructure.LambdaResponse;
import learntrainevolve.lambda.infrastructure.auth.AuthenticatedLambdaRequest;


public class SyncTrainingSessionsLambda
        extends LambdaActivityRunner<SyncTrainingSessionsRequest, SyncTrainingSessionsResponse>
        implements RequestHandler<AuthenticatedLambdaRequest<SyncTrainingSessionsRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<SyncTrainingSessionsRequest> input, Context context) {
        return super.runActivity(
                () ->
                        input.fromQuery(query ->
                                            SyncTrainingSessionsRequest.builder()
                                                    .withCalId(query.get("cal"))
                                                    .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideSyncTrainingSessionsActivity().handleRequest(request)

        );

    }
}
