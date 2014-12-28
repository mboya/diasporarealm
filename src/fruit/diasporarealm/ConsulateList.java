package fruit.diasporarealm;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ConsulateList extends ActionBarActivity {
	ListView consulate_listView;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulate_list);

		Parse.initialize(this, "6FbL3iK92c3uXPJlQHcra4MVN9T4sD6yQro2KmHb",
				"fEKJ9yxYHw3ix3K2iWnMeCvwVG9wD03zvnj7CfxA");

		new RemoteDataTask().execute();

	}

	// RemoteDataTask AsyncTask
	private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(ConsulateList.this);

			// Set progressdialog title
			mProgressDialog.setTitle("Online Diaspora Service Portal");

			// Set progressdialog message
			mProgressDialog.setMessage("Loading...");
			mProgressDialog.setIndeterminate(false);
			// Show progressdialog
			mProgressDialog.show();

		}

		@Override
		protected Void doInBackground(Void... params) {
			// Locate the class table named "Country" in Parse.com
			ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
					"consulates");
			query.orderByAscending("country_name");
			try {
				ob = query.find();
			} catch (ParseException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// Locate the listview in listview_main.xml
			consulate_listView = (ListView) findViewById(R.id.consulate_listView);

			// Pass the results into an ArrayAdapter
			adapter = new ArrayAdapter<String>(ConsulateList.this,
					android.R.layout.simple_list_item_1, android.R.id.text1);

			// Retrieve object "name" from Parse.com database
			for (ParseObject country : ob) {
				adapter.add((String) country.get("country_name"));
			}
			// Binds the Adapter to the ListView
			consulate_listView.setAdapter(adapter);
			// Close the progressdialog
			mProgressDialog.dismiss();
			// Capture button clicks on ListView items
			consulate_listView
					.setOnItemClickListener(new OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent,
								View view, int position, long id) {
							// Send single item click data to SingleItemView
							// Class
							Intent i = new Intent(ConsulateList.this,
									SingleItem.class);
							// Pass data "name" followed by the position
							i.putExtra("name",
									ob.get(position).getString("country_name")
											.toString());
							// Open SingleItemView.java Activity
							startActivity(i);
						}
					});
		}
	}

	public void onStop() {
		super.onStop();
	}
}
