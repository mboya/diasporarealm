package fruit.diasporarealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class CitizensList extends ActionBarActivity {
	ListView citizen_listView;
	ArrayAdapter<String> adapter;
	String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_citizens_list);
		
		Intent i = getIntent();
		name = i.getStringExtra("name");
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("mkimbizi");
		query.whereEqualTo("kin_country", name);
		query.getFirstInBackground(new GetCallback<ParseObject>() {

			@Override
			public void done(ParseObject obj, ParseException e) {
				if (obj != null){
					Toast.makeText(getApplicationContext(), "Success User Available", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(getApplicationContext(), "Failed to Get Citizens in Selected country", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
}
