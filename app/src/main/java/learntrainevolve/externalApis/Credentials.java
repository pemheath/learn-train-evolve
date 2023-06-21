package learntrainevolve.externalApis;

import javax.inject.Inject;

/**
 * Object to store API access key/token from AWS Secrets Manager
 */
public class Credentials {
    private final String API_KEY;
    private final String OAUTH_TOKEN;

    /**
     * Instantiates a Credentials object with the secrets:
     * @param key
     * @param token
     */

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
