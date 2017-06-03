package scalol.com.scalolclient.DAO.models;

/**
 * Created by durza9390 on 03.06.2017.
 */

public class AuthToken {
    private String token;

    public AuthToken() {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
