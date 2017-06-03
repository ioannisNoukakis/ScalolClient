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
import scalol.com.scalolclient.DAO.models.User;
import scalol.com.scalolclient.services.UserPost;
import scalol.com.scalolclient.utils.Alerts;

/**
 * Created by durza9390 on 03.06.2017.
 */

public class SignUpActivity extends AppCompatActivity {

    private EditText username;
    private EditText mail;
    private EditText password;
    private EditText password2;
    private Button signUpButton;

    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        username = (EditText) findViewById(R.id.usernameSingup);
        mail = (EditText) findViewById(R.id.mailSignUp);
        password = (EditText) findViewById(R.id.passwordSignup);
        password2 = (EditText) findViewById(R.id.confirmPassword);
        signUpButton = (Button) findViewById(R.id.signupButton);
        builder = new AlertDialog.Builder(this);

        username.setText("user12321");
        mail.setText("user12321@gmail.com");
        password.setText("mylittlepassword");
        password2.setText("mylittlepassword");

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty())
                {
                    Alerts.createDialog(builder, R.string.AlertUsernameEmptyMessage, R.string.AlertTitle).show();
                    return;
                }

                if (mail.getText().toString().isEmpty())
                {
                    Alerts.createDialog(builder, R.string.AlertMailEmptyMessage, R.string.AlertTitle).show();
                    return;
                }

                if (password.getText().toString().isEmpty())
                {
                    Alerts.createDialog(builder, R.string.AlertPasswordEmptyMessage, R.string.AlertTitle).show();
                    return;
                }

                if (password2.getText().toString().isEmpty())
                {
                    Alerts.createDialog(builder, R.string.AlertCheckPasswordEmptyMessage, R.string.AlertTitle).show();
                    return;
                }

                if (!password2.getText().toString().equals(password.getText().toString()))
                {
                    Alerts.createDialog(builder, R.string.AlertCheckPasswordFailedMessage, R.string.AlertTitle).show();
                    return;
                }

                ProgressDialog pd = Alerts.createWaitingCircle(SignUpActivity.this, "Please wait...", R.string.WaitingTitle);
                pd.show();
                Response<AuthToken> resp = null;
                try {
                    resp = new UserPost().execute(new User(username.getText().toString(), mail.getText().toString(), password.getText().toString())).get();
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
                        SignUpActivity.this.setResult(FlowActivity.LOGIN_RESULT_OK);
                        finish();
                        break;
                    case 400:
                        Alerts.createDialog(builder, R.string.AlertUsernameTooLongMessage, R.string.AlertTitle).show();
                        username.setText("");
                    case 409:
                        Alerts.createDialog(builder, R.string.AlertWrongUsernamePasswordMessage, R.string.AlertTitle).show();
                        username.setText("");
                        mail.setText("");
                        password.setText("");
                        password2.setText("");
                        break;
                    default:
                        Alerts.createDialog(builder, R.string.AlertServerIsDrunk, R.string.AlertTitle).show();
                }
            }
        });
    }
}