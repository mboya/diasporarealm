package fruit.diasporarealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class Home extends ActionBarActivity {
	Button btn_embassyConsulate;
	Button btn_reportProblem;
	Button btn_citizenPerCountry;

	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		btn_embassyConsulate = (Button) findViewById(R.id.embassy_consulate_button);
		btn_reportProblem = (Button) findViewById(R.id.report_problem_button);
		btn_citizenPerCountry = (Button) findViewById(R.id.citizen_country_button);

		btn_embassyConsulate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				intent = new Intent(Home.this, EmbassyConsulate.class);
				startActivity(intent);
			}
		});
		btn_reportProblem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				intent = new Intent(Home.this, ReportProblem.class);
				startActivity(intent);
			}
		});
		btn_citizenPerCountry.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				intent = new Intent(Home.this, CitizensPerCountryList.class);
				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.logout) {
			ParseUser.logOut();
			Intent intent = new Intent(this, Login.class);
			// close all view before opening Login.
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onStop(){
		super.onStop();
	}
}
