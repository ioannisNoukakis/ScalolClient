package scalol.com.scalolclient.DAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import scalol.com.scalolclient.DAO.models.Post;

/**
 * Created by durza9390 on 02.06.2017.
 */

public interface PostsService {

    /**
     * Get the latests post. By default the api will return the 100 newer (API v1.0)
     *
     * @return the lsit of posts.
     */
    @GET("posts")
    Call<List<Post>> getLatestPosts();
}
