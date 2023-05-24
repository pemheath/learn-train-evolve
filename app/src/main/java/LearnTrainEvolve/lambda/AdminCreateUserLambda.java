package LearnTrainEvolve.lambda;

import LearnTrainEvolve.activity.requests.AdminCreateUserRequest;
import LearnTrainEvolve.activity.responses.AdminCreateUserResponse;
import LearnTrainEvolve.lambda.infrastructure.auth.AuthenticatedLambdaRequest;
import LearnTrainEvolve.lambda.infrastructure.LambdaActivityRunner;
import LearnTrainEvolve.lambda.infrastructure.LambdaResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminCreateUserLambda
        extends LambdaActivityRunner<AdminCreateUserRequest, AdminCreateUserResponse>
        implements RequestHandler<AuthenticatedLambdaRequest<AdminCreateUserRequest>, LambdaResponse> {

    private final Logger log = LogManager.getLogger();
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AdminCreateUserRequest> input, Context context) {
        log.info("Received input: '{}'", input.toString());

        return super.runActivity(
            () -> {
                AdminCreateUserRequest authenticatedRequest = input.fromBody(AdminCreateUserRequest.class);
                log.info("Our request: '{}'", authenticatedRequest.toString());
                return AdminCreateUserRequest.builder()
                        .withEmail(authenticatedRequest.getEmail())
                        .withFirstName(authenticatedRequest.getFirstName())
                        .withLastName(authenticatedRequest.getLastName())
                        .withMembership(authenticatedRequest.getMembership())
                        .withRank(authenticatedRequest.getRank())
                        .build();
            },
            (request, serviceComponent) ->
                    serviceComponent.provideAdminCreateUserActivity().handleRequest(request)
        );
    }
}


