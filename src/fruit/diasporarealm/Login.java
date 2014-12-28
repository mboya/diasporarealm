package fruit.diasporarealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends ActionBarActivity {
	EditText mUsername;
	EditText mPassword;

	Button btn_login;
	Button btn_createAccount;

	Intent intent, home;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Parse.initialize(this, "6FbL3iK92c3uXPJlQHcra4MVN9T4sD6yQro2KmHb",
				"fEKJ9yxYHw3ix3K2iWnMeCvwVG9wD03zvnj7CfxA");

		ParseUser user = ParseUser.getCurrentUser();
		if (user != null) {
			home = new Intent(Login.this, Home.class);
			startActivity(home);
			finish();
		}

		mUsername = (EditText) findViewById(R.id.username_login_editText);
		mPassword = (EditText) findViewById(R.id.password_login_editText);
		btn_login = (Button) findViewById(R.id.login_button);
		btn_login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				LoginProcess();
			}
		});

		btn_createAccount = (Button) findViewById(R.id.createaccount_redirect_button);
		btn_createAccount.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				SignUpProcess();
			}
		});
	}

	public void LoginProcess() {
		String username = mUsername.getText().toString();
		String password = mPassword.getText().toString();

		ParseUser.logInInBackground(username, password, new LogInCallback() {

			@Override
			public void done(ParseUser user, ParseException e) {
				if (user != null) {
					Home();
				} else {
					Toast.makeText(getApplicationContext(),
							"Username / Password not correct",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	public void Home() {
		intent = new Intent(Login.this, Home.class);
		startActivity(intent);
		finish();
	}

	public void SignUpProcess() {
		intent = new Intent(Login.this, SignUp.class);
		startActivity(intent);
	}
	
	public void onStop(){
		super.onStop();
	}
}
