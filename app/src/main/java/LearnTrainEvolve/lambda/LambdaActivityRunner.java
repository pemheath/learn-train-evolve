package LearnTrainEvolve.lambda;

import LearnTrainEvolve.dependency.ServiceComponent;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Parent class for Lambda functions that run Activities..
 * @param <TRequest> The type of the Activity Request.
 * @param <TResult> The type of the AActivity Result.
 */
public abstract class LambdaActivityRunner<TRequest, TResult> {
    private ServiceComponent service;

    /**
     * Handles running the activity and returning a LambdaResponse (either success or failure).
     * @param requestSupplier Provides the activity request.
     * @param handleRequest Runs the activity and provides a response.
     * @return A LambdaResponse
     */
    protected LambdaResponse runActivity(
            Supplier<TRequest> requestSupplier,
            BiFunction<TRequest, ServiceComponent, TResult> handleRequest) {
        try {
            TRequest request = requestSupplier.get();
            ServiceComponent serviceComponent = getService();
            TResult result = handleRequest.apply(request, serviceComponent);
            return LambdaResponse.success(result);
        } catch (Exception e) {
            return LambdaResponse.error(e);
        }
    }

    private ServiceComponent getService() {
        return null;
     //   if (service == null) {
        //    service = DaggerServiceComponent.create();
    //    }
     //   return service;
    }
}
