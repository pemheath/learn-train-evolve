package learntrainevolve.lambda;

import learntrainevolve.activity.requests.GetTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetTrainingSessionsResponse;
import learntrainevolve.lambda.infrastructure.LambdaActivityRunner;
import learntrainevolve.lambda.infrastructure.LambdaRequest;
import learntrainevolve.lambda.infrastructure.LambdaResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class GetTrainingSessionsLambda
        extends LambdaActivityRunner<GetTrainingSessionsRequest, GetTrainingSessionsResponse>
        implements RequestHandler<LambdaRequest<GetTrainingSessionsRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetTrainingSessionsRequest> input, Context context) {


        return super.runActivity(
                ()-> input.fromQuery(query ->
                                GetTrainingSessionsRequest.builder()
                                        .withType(query.get("category"))
                                        .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTrainingSessionsActivity().handleRequest(request)

        );


    }
}


