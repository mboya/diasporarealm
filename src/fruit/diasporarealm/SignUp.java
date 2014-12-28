package fruit.diasporarealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.Parse;

public class SignUp extends ActionBarActivity {
	Button btn_next;
	// personal details
	private EditText full_names;
	private EditText national_id;
	private EditText gender;
	private EditText occupation;
	// address details
	private EditText postal_address;
	private EditText email_address;
	private EditText telephone_number;
	private EditText current_country;
	// personal String details
	public String fullnames;
	public String nat_id;
	public String gendr;
	public String occupatn;
	// address String details
	public String postal;
	public String email;
	public String tel_no;
	public String country;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		Parse.initialize(this, "6FbL3iK92c3uXPJlQHcra4MVN9T4sD6yQro2KmHb",
				"fEKJ9yxYHw3ix3K2iWnMeCvwVG9wD03zvnj7CfxA");

		full_names = (EditText) findViewById(R.id.fullnames_editText);
		national_id = (EditText) findViewById(R.id.national_id_editText);
		gender = (EditText) findViewById(R.id.gender_editText);
		occupation = (EditText) findViewById(R.id.occupation_editText);
		// layout link for address details
		postal_address = (EditText) findViewById(R.id.postal_address_editText);
		email_address = (EditText) findViewById(R.id.email_editText);
		telephone_number = (EditText) findViewById(R.id.phone_number_editText);
		current_country = (EditText) findViewById(R.id.country_editText);

		btn_next = (Button) findViewById(R.id.sign_next_button);
		btn_next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				nextProcess();
				finish();
			}
		});
	}

	public void nextProcess() {
		fullnames = full_names.getText().toString();
		nat_id = national_id.getText().toString();
		gendr = gender.getText().toString();
		occupatn = occupation.getText().toString();

		postal = postal_address.getText().toString();
		email = email_address.getText().toString();
		tel_no = telephone_number.getText().toString();
		country = current_country.getText().toString();

		Intent intent = new Intent(this, NextOfKin.class);
		intent.putExtra("full_names", fullnames);
		intent.putExtra("nat_id", nat_id);
		intent.putExtra("gendr", gendr);
		intent.putExtra("accupatn", occupatn);

		intent.putExtra("postal", postal);
		intent.putExtra("email", email);
		intent.putExtra("tel_no", tel_no);
		intent.putExtra("country", country);
		startActivity(intent);
	}
	
	public void onStop(){
		super.onStop();
	}
}
