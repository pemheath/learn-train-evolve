package learntrainevolve.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import learntrainevolve.activity.requests.GetUserTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetUserTrainingSessionsResponse;
import learntrainevolve.lambda.infrastructure.LambdaActivityRunner;
import learntrainevolve.lambda.infrastructure.LambdaRequest;
import learntrainevolve.lambda.infrastructure.LambdaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


public class GetUserTrainingSessionsLambda
        extends LambdaActivityRunner<GetUserTrainingSessionsRequest, GetUserTrainingSessionsResponse>
        implements RequestHandler<LambdaRequest<GetUserTrainingSessionsRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetUserTrainingSessionsRequest> input, Context context) {
        log.info("handleRequest");

        return super.runActivity(
                ()-> input.fromPath(p ->
                                GetUserTrainingSessionsRequest.builder()
                                        .withEmail(URLDecoder.decode(p.get("email"), StandardCharsets.UTF_8))
                                        .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetUserTrainingSessionsActivity().handleRequest(request)

        );

    }
}


