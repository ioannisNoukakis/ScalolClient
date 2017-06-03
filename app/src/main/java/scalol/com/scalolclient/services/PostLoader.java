package scalol.com.scalolclient.services;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import scalol.com.scalolclient.DAO.PostsService;
import scalol.com.scalolclient.DAO.models.Post;

/**
 * Created by durza9390 on 02.06.2017.
 */

public class PostLoader extends AsyncTask<Void, String, List<Post>> {

    @Override
    protected List<Post> doInBackground(Void... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nixme.ddns.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostsService service = retrofit.create(PostsService.class);

        try {
            return service.getLatestPosts().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
