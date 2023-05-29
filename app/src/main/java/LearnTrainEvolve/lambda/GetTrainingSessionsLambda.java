package LearnTrainEvolve.lambda;

import LearnTrainEvolve.activity.requests.GetTrainingSessionsRequest;
import LearnTrainEvolve.activity.responses.GetTrainingSessionsResponse;
import LearnTrainEvolve.lambda.infrastructure.LambdaActivityRunner;
import LearnTrainEvolve.lambda.infrastructure.LambdaRequest;
import LearnTrainEvolve.lambda.infrastructure.LambdaResponse;
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
        System.out.println("do we get here?");
        System.out.println("input is " + input);

        return super.runActivity(
                ()-> input.fromQuery(query ->
                                GetTrainingSessionsRequest.builder()
                                        .withType(query.get("type"))
                                        .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetTrainingSessionsActivity().handleRequest(request)

        );


    }
}


