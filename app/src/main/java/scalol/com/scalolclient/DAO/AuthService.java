package scalol.com.scalolclient.DAO;

import scalol.com.scalolclient.DAO.models.AuthToken;

/**
 * Created by durza9390 on 03.06.2017.
 */

public class AuthService {
    private AuthToken token;
    private static AuthService instance;

    private AuthService(){
    }

    public static AuthService getInstance(){
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    public AuthToken getToken() {
        return token;
    }

    public void setToken(AuthToken token) {
        this.token = token;
    }

    public static void setInstance(AuthService instance) {
        AuthService.instance = instance;
    }
}
