package LearnTrainEvolve.lambda;

public class CreateUserLambda extends LambdaActivityRunner{<CreateUserRequest, CreateUserResponse>
    implements RequestHandler<LambdaRequest<CreateUserRequest>, LambdaResponse> {

    @Override
            public LambdaResponse handleRequest(LambdaRequest<CreateUserRequest> input, Context context) {
    return new LambdaResponse();

}
