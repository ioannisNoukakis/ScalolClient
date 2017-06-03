package scalol.com.scalolclient.DAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;
import scalol.com.scalolclient.DAO.models.AuthToken;
import scalol.com.scalolclient.DAO.models.Post;
import scalol.com.scalolclient.DAO.models.User;
import scalol.com.scalolclient.DAO.models.UserAuth;

/**
 * Created by durza9390 on 03.06.2017.
 */

public interface UsersService {

    /**
     * Get the latests post. By default the api will return the 100 newer (API v1.0)
     *
     * @return the lsit of posts.
     */
    @POST("user")
    Call<AuthToken> postUser(@Body User user);

    @POST("auth")
    Call<AuthToken> postAuth(@Body UserAuth userAuth);
}
