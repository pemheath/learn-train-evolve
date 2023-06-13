package learntrainevolve.externalApis;

import javax.inject.Inject;

public class Credentials {
    private final String API_KEY;
    private final String OAUTH_TOKEN;

    @Inject
    public Credentials(String key, String token) {
        this.API_KEY = key;
        this.OAUTH_TOKEN = token;
    }

    public String getApiKey() {
        return API_KEY;
    }

    public String getOAUTH_TOKEN() {
        return OAUTH_TOKEN;
    }

}
