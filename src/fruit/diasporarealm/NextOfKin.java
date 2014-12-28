package fruit.diasporarealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class NextOfKin extends ActionBarActivity {

	Button btn_create = null;
	private EditText kin_name;
	private EditText kin_tel;
	private EditText kin_add;
	private EditText kin_country;
	private EditText mUsername;
	private EditText mPassword;
	// private EditText mCPassword;

	public String name;
	public String id;
	public String sex;
	public String accptn;
	public String tal;
	public String mail;
	public String tel;
	public String nchi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_next_of_kin);

		// parse instance
		Parse.initialize(this, "6FbL3iK92c3uXPJlQHcra4MVN9T4sD6yQro2KmHb",
				"fEKJ9yxYHw3ix3K2iWnMeCvwVG9wD03zvnj7CfxA");

		// layout link for kin interface
		kin_name = (EditText) findViewById(R.id.kins_names_editText);
		kin_tel = (EditText) findViewById(R.id.kins_phone_editText);
		kin_add = (EditText) findViewById(R.id.kins_address_editText);
		kin_country = (EditText) findViewById(R.id.kins_country_editText);

		mUsername = (EditText) findViewById(R.id.username_create_editText);
		mPassword = (EditText) findViewById(R.id.password_create_editText);
		// mCPassword = (EditText)
		// findViewById(R.id.confirmpassword_create_editText);

		Intent i = getIntent();
		name = i.getStringExtra("full_names");
		id = i.getStringExtra("nat_id");
		sex = i.getStringExtra("gendr");
		accptn = i.getStringExtra("accupatn");

		tal = i.getStringExtra("postal");
		mail = i.getStringExtra("email");
		tel = i.getStringExtra("tel_no");
		nchi = i.getStringExtra("country");

		btn_create = (Button) findViewById(R.id.createAccount_button);
		btn_create.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Click();
			}
		});
	}

	public void Click() {
		String name_kin = kin_name.getText().toString();
		String tel_kin = kin_tel.getText().toString();
		String add_kin = kin_add.getText().toString();
		String country_kin = kin_country.getText().toString();
		String username = mUsername.getText().toString();
		String password = mPassword.getText().toString();
		// String cpassword = mCPassword.getText().toString();

		ParseObject obj = new ParseObject("mkimbizi");
		// inheriting from SignActivity class

		obj.put("full_name", name);
		obj.put("nat_id", id);
		obj.put("gender", sex);
		obj.put("occupation", accptn);

		obj.put("postal", tal);
		obj.put("telephone", tel);
		obj.put("country", nchi);

		obj.put("kin_name", name_kin);
		obj.put("kin_tel", tel_kin);
		obj.put("kin_address", add_kin);
		obj.put("kin_country", country_kin);
		obj.saveInBackground();

		ParseUser user = new ParseUser();
		user.setUsername(username);
		user.setPassword(password);
		// user.setPassword(cpassword);
		user.setEmail(mail);

		user.signUpInBackground(new SignUpCallback() {

			@Override
			public void done(ParseException e) {
				if (e == null) {
					Intent intent = new Intent(NextOfKin.this, Home.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Failed to Create the Account ... ",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	public void onStop(){
		super.onStop();
	}
}
