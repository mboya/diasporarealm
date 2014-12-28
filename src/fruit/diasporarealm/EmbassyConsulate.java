package fruit.diasporarealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class EmbassyConsulate extends ActionBarActivity {
	Intent intent;

	Button btn_EmbassyList;
	Button btn_ConsulateList;
	Button btn_Closest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_embassy_consulate);

		btn_EmbassyList = (Button) findViewById(R.id.embassy_list_button);
		btn_ConsulateList = (Button) findViewById(R.id.consulate_list_button);
		btn_Closest = (Button) findViewById(R.id.closest_button);

		btn_EmbassyList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				intent = new Intent(EmbassyConsulate.this, EmbassyList.class);
				startActivity(intent);
			}
		});

		btn_ConsulateList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				intent = new Intent(EmbassyConsulate.this, ConsulateList.class);
				startActivity(intent);
			}
		});

		btn_Closest.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				intent = new Intent(EmbassyConsulate.this,
						ClosestEmbassyConsulate.class);
				startActivity(intent);
			}
		});
	}

	public void onStop() {
		super.onStop();
	}
}
