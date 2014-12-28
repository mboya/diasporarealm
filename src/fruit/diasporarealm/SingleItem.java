package fruit.diasporarealm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class SingleItem extends ActionBarActivity {
	ProgressDialog pDialog;

	TextView txtname, txtphone, txtcity, txtwebsite, txtemail, txtfax, txtpostal_address;
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_item);
		
		// Locate the TextView in singleitemview.xml
		txtname = (TextView) findViewById(R.id.name);
		txtcity = (TextView) findViewById (R.id.city);
		txtphone = (TextView) findViewById(R.id.telephone);
		txtwebsite = (TextView) findViewById (R.id.website);
		txtemail = (TextView) findViewById (R.id.email);
		txtfax = (TextView) findViewById (R.id.fax);
		txtpostal_address = (TextView) findViewById (R.id.postal_address);

		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Loading");
		pDialog.show();

		// Retrieve data from MainActivity on item click event
		Intent i = getIntent();
		// Get the name
		name = i.getStringExtra("name");

		ParseQuery<ParseObject> query = ParseQuery.getQuery("embassies");
		query.whereEqualTo("country_name", name);
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			public void done(ParseObject object, ParseException e) {
				if (object == null) {
//					Log.d("score", "The getFirst request failed.");
					ParseQuery<ParseObject> query = ParseQuery.getQuery("consulates");
					query.whereEqualTo("country_name", name);
					query.getFirstInBackground(new GetCallback<ParseObject>() {
						public void done(ParseObject object, ParseException e) {
							if (object == null) {
								pDialog.dismiss();
								Toast.makeText(getApplicationContext(), "Failed to get Application Details", Toast.LENGTH_SHORT).show();
							}else{
								pDialog.dismiss();
								// Load the text into the TextView
								String phone = object.getString("phone");
								String city = object.getString("city");
								String website = object.getString("website");
								String email = object.getString("email");
								String fax = object.getString("fax");
								String postal_address = object.getString("address");
								
								txtname.setText(name);
								txtcity.setText(city);
								txtphone.setText(phone);
								txtwebsite.setText(website);
								txtemail.setText(email);
								txtfax.setText(fax);
								txtpostal_address.setText(postal_address);
							}
						}
					});

				} else {
					Log.d("score", "Retrieved the object.");
					pDialog.dismiss();
					// Load the text into the TextView
					String phone = object.getString("phone");
					String city = object.getString("city");
					String website = object.getString("website");
					String email = object.getString("email");
					String fax = object.getString("fax");
					String postal_address = object.getString("postal_address");
					
					txtname.setText(name);
					txtcity.setText(city);
					txtphone.setText(phone);
					txtwebsite.setText(website);
					txtemail.setText(email);
					txtfax.setText(fax);
					txtpostal_address.setText(postal_address);
				}
			}
		});
	}
	
	public void onStop(){
		super.onStop();
	}
}
