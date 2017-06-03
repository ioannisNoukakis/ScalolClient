package scalol.com.scalolclient.services;

import android.os.AsyncTask;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import scalol.com.scalolclient.DAO.UsersService;
import scalol.com.scalolclient.DAO.models.AuthToken;
import scalol.com.scalolclient.DAO.models.User;

/**
 * Created by durza9390 on 03.06.2017.
 */

public class UserPost extends AsyncTask<User, String, Response<AuthToken>> {

    @Override
    protected Response<AuthToken> doInBackground(User... params) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nixme.ddns.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersService service = retrofit.create(UsersService.class);

        try {
            return service.postUser(params[0]).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
