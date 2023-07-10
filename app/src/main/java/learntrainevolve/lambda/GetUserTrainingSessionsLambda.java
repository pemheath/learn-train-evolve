package learntrainevolve.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import learntrainevolve.activity.requests.GetUserTrainingSessionsRequest;
import learntrainevolve.activity.responses.GetUserTrainingSessionsResponse;
import learntrainevolve.exceptions.InvalidRequestException;
import learntrainevolve.lambda.infrastructure.LambdaActivityRunner;
import learntrainevolve.lambda.infrastructure.LambdaRequest;
import learntrainevolve.lambda.infrastructure.LambdaResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


public class GetUserTrainingSessionsLambda
        extends LambdaActivityRunner<GetUserTrainingSessionsRequest, GetUserTrainingSessionsResponse>
        implements RequestHandler<LambdaRequest<GetUserTrainingSessionsRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetUserTrainingSessionsRequest> input, Context context) {
        log.info("handleRequest");
        log.info(input.toString());

        String emailFromPath = input.getPathParameters().get("email");
        log.info("Prior to decoding, emailFromPath: " + emailFromPath);

        try {
            URLDecoder.decode(emailFromPath, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException(e);
        }

        log.info("emailFromPath decoded to: " + emailFromPath);


        return super.runActivity(
                ()-> input.fromPathAndQuery((p, q) ->
                                GetUserTrainingSessionsRequest.builder()
                                        .withEmail(emailFromPath)
                                        .withDataVis(q.get("dataVis"))
                                        .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetUserTrainingSessionsActivity().handleRequest(request)

        );

    }
}


