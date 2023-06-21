package learntrainevolve.dependency;
import static learntrainevolve.utils.ExternalAPIUtils.getSecret;

import dagger.Module;
import dagger.Provides;
import learntrainevolve.exceptions.FailedExternalAPICallException;
import learntrainevolve.externalApis.Credentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Singleton;



@Module
public class GoogleCalEventDaoModule {
    private final Logger log = LogManager.getLogger();


    @Singleton
    @Provides
    public Credentials provideCredentials() throws RuntimeException {
        String key;
        String oathToken;

        try {
            key = getSecret("lte/gcal/key");
            oathToken = getSecret("lte/oath");
        } catch (Exception e) {
            log.info("Exception thrown trying to get key with message{} and cause {}", e.getMessage(), e.getCause());
            throw new FailedExternalAPICallException(e.getMessage(), e.getCause());
        }
        return new Credentials(key, oathToken);
    }
}
