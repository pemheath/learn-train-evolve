package learntrainevolve.lambda.infrastructure.auth;

import learntrainevolve.utils.NullUtils;
import org.apache.commons.lang3.StringUtils;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;

import java.util.List;
import java.util.UUID;

/**
 * Represents claims data provided by cognito.
 *
 * NOTE: If you need additional claims fields, you'll need to add a getter to this class to expose the data.
 */
public class CognitoClaims {
    private final JwtClaims jwtClaims;

    /**
     * Create a new CognitoClaims using the data in the JwtClaims object.
     * @param jwtClaims Contains the claims data provided by the jose4j library.
     */
    public CognitoClaims(JwtClaims jwtClaims) {
        this.jwtClaims = jwtClaims;
    }

    /**
     * The cognito username.
     *
     * NOTE: The music playlist application is configured to allow users to login using thier email address.
     *       This means that the user does not select a username, so cognito generates a UUID.
     *       The user should never need to know their username, but it might be useful as a unique identifier in
     *       situations where email won't work or is inappropriate.
     *
     * @return the cognito username.
     */
    public UUID getUsername() {
        try {
            return UUID.fromString(jwtClaims.getSubject());
        } catch (MalformedClaimException e) {
            throw new RuntimeException("ERROR! Unable to get 'username' (aka 'sub') claim.", e);
        }
    }

    /**
     * A list of groups in which the user is a member.
     * @return a list of the user's groups, or an empty list if the user doesn't belong to a group.
     */
    public List<String> getCognitoGroups() {
        try {
            return NullUtils.ifNull(jwtClaims.getStringListClaimValue("cognito:groups"), List.of());
        } catch (MalformedClaimException e) {
            throw new RuntimeException("ERROR! Unable to get 'cognito:groups' claim.", e);
        }
    }

    /**
     * The cognito user's full name.
     * @return the user's full name.
     */
    public String getName() {
        String name = jwtClaims.getClaimValueAsString("name");
        if (StringUtils.isAllBlank(name)) {
            throw new RuntimeException("ERROR! Unable to get 'name' claim. Claim does not exist or is blank.");
        }
        return name;
    }

    /**
     * The cognito user's email.
     * @return the user's email.
     */
    public String getEmail() {
        String email = jwtClaims.getClaimValueAsString("email");
        if (StringUtils.isAllBlank(email)) {
            throw new RuntimeException("ERROR! Unable to get 'email' claim. Claim does not exist or is blank.");
        }
        return email;
    }
}
