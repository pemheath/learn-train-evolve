package learntrainevolve.lambda;

import learntrainevolve.activity.requests.LogTrainingRequest;
import learntrainevolve.activity.responses.LogTrainingResponse;
import learntrainevolve.lambda.infrastructure.LambdaActivityRunner;
import learntrainevolve.lambda.infrastructure.LambdaResponse;
import learntrainevolve.lambda.infrastructure.auth.AuthenticatedLambdaRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


public class LogTrainingLambda
        extends LambdaActivityRunner<LogTrainingRequest, LogTrainingResponse>
        implements RequestHandler<AuthenticatedLambdaRequest<LogTrainingRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<LogTrainingRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    LogTrainingRequest unauthenticatedRequest = input.fromBody(LogTrainingRequest.class);



                    System.out.println("The unauthenticated request from the body of the curl request is" + unauthenticatedRequest);

                    return  input.fromPath(path ->

                            LogTrainingRequest.builder()
                            .withEmail(URLDecoder.decode(path.get("email"), StandardCharsets.UTF_8))
                            .withEventId(path.get("eventId"))
                            .withTimeAndDate(unauthenticatedRequest.getTimeAndDate())
                            .withType(unauthenticatedRequest.getType())
                            .withCoach(unauthenticatedRequest.getCoach())
                            .withIntensityRating(unauthenticatedRequest.getIntensityRating())
                            .withTechniqueEnjoyment(unauthenticatedRequest.getTechniqueEnjoyment())
                            .withPerformanceRating(unauthenticatedRequest.getPerformanceRating())
                            .withNote(unauthenticatedRequest.getNote())
                            .withGoal(unauthenticatedRequest.getGoal())
                            .withTags(unauthenticatedRequest.getTags())
                            .withAttended(unauthenticatedRequest.getAttended())
                            .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideLogTrainingActivity().handleRequest(request)
        );
    }
}
