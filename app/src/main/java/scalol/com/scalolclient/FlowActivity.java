package scalol.com.scalolclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import scalol.com.scalolclient.DAO.AuthService;
import scalol.com.scalolclient.DAO.models.AuthToken;

public class FlowActivity extends AppCompatActivity {

    private final static int LOGIN_INTENT_RESULT = 1;
    private final static int SIGNUP_INTENT_RESULT = 2;
    public final static int LOGIN_RESULT_OK = 2000;
    public final static int SIGNUP_RESULT_OK = 2001;

    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authService = AuthService.getInstance();
        if(savedInstanceState != null) {
            AuthToken token = (AuthToken)savedInstanceState.get("token");
            if (token != null) {
                authService.setToken(token);
            }
        }
        setContentView(R.layout.activity_flow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(authService.getToken() == null)
            inflater.inflate(R.menu.flow_menu_no_login, menu);
        else
            inflater.inflate(R.menu.flow_menu_logged, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.login:
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivityForResult(loginIntent, LOGIN_INTENT_RESULT);
                return true;
            case R.id.singup:
                Intent signupIntent = new Intent(this, SignUpActivity.class);
                startActivityForResult(signupIntent, SIGNUP_INTENT_RESULT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN_INTENT_RESULT || requestCode == SIGNUP_INTENT_RESULT) {
            if (resultCode == LOGIN_RESULT_OK || resultCode == SIGNUP_RESULT_OK) {
                finish();
                startActivity(getIntent());
            }
        }
    }
}
