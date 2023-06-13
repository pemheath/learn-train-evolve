package learntrainevolve.dependency;
import static learntrainevolve.utils.GoogleCalAPIUtils.getSecret;

import dagger.Module;
import dagger.Provides;
import learntrainevolve.externalApis.Credentials;

import javax.inject.Singleton;


@Module
public class GoogleCalEventDaoModule {



    @Singleton
    @Provides
    public Credentials provideCredentials() throws RuntimeException {
        String key = null;
        String oathToken = null;
        try {
            key = getSecret("lte/gcal/key");
            oathToken = getSecret("lte/oath");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
        return new Credentials(key, oathToken);
    }
}
