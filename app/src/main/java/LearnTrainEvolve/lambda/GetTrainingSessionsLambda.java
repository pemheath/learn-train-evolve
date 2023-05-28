package LearnTrainEvolve.lambda;

import LearnTrainEvolve.activity.requests.GetTrainingSessionsRequest;
import LearnTrainEvolve.activity.responses.GetTrainingSessionsResponse;
import LearnTrainEvolve.lambda.infrastructure.LambdaActivityRunner;
import LearnTrainEvolve.lambda.infrastructure.LambdaRequest;
import LearnTrainEvolve.lambda.infrastructure.LambdaResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.time.LocalDateTime;

public class GetTrainingSessionsLambda
        extends LambdaActivityRunner<GetTrainingSessionsRequest, GetTrainingSessionsResponse>
        implements RequestHandler<LambdaRequest<GetTrainingSessionsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetTrainingSessionsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPathAndQuery((path, query) ->
                        GetTrainingSessionsRequest.builder()
                                .withTimeAndDate(LocalDateTime.now())
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTrainingSessionsActivity().handleRequest(request)
        );
    }
}


