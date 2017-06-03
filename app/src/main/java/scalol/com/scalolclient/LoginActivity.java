package scalol.com.scalolclient;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

import retrofit2.Response;
import scalol.com.scalolclient.DAO.AuthService;

import scalol.com.scalolclient.DAO.models.AuthToken;
import scalol.com.scalolclient.DAO.models.UserAuth;
import scalol.com.scalolclient.services.UserPostAuth;
import scalol.com.scalolclient.utils.Alerts;

/**
 * Created by durza9390 on 03.06.2017.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button lgginButton;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        username = (EditText) findViewById(R.id.usernameLogin);
        password = (EditText) findViewById(R.id.passwordLogin);
        lgginButton = (Button) findViewById(R.id.loginButton);
        builder = new AlertDialog.Builder(this);

        username.setText("user1232");
        password.setText("mylittlepassword");

        lgginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty())
                {
                    Alerts.createDialog(builder, R.string.AlertUsernameEmptyMessage, R.string.AlertTitle).show();
                    return;
                }

                if (password.getText().toString().isEmpty())
                {
                    Alerts.createDialog(builder, R.string.AlertPasswordEmptyMessage, R.string.AlertTitle).show();
                    return;
                }

                ProgressDialog pd = Alerts.createWaitingCircle(LoginActivity.this, "Please wait...", R.string.WaitingTitle);
                pd.show();
                Response<AuthToken> resp = null;
                try {
                    resp = new UserPostAuth().execute(new UserAuth(username.getText().toString(), password.getText().toString())).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    pd.hide();
                    Alerts.createDialog(builder, R.string.AlertSomethingWentWrongMessage, R.string.AlertTitle).show();
                    finish();
                }
                pd.hide();

                switch (resp.code()) {
                    case 200:
                        AuthService.getInstance().setToken(resp.body());
                        LoginActivity.this.setResult(FlowActivity.LOGIN_RESULT_OK);
                        finish();
                        break;
                    case 403:
                        Alerts.createDialog(builder, R.string.AlertWrongUsernamePasswordMessage, R.string.AlertTitle).show();
                        username.setText("");
                        password.setText("");
                        break;
                    default:
                        Alerts.createDialog(builder, R.string.AlertServerIsDrunk, R.string.AlertTitle).show();
                }
            }
        });
    }
}