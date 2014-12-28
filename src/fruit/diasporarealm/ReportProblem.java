package fruit.diasporarealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class ReportProblem extends ActionBarActivity {
	Button btn_writeMessage;
	Button btn_recordMessage;

	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_problem);

		btn_writeMessage = (Button) findViewById(R.id.write_message_button);
		btn_recordMessage = (Button) findViewById(R.id.record_message_button);

		btn_writeMessage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				intent = new Intent(ReportProblem.this, WriteMessage.class);
				startActivity(intent);
			}
		});

		btn_recordMessage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				intent = new Intent(ReportProblem.this, RecordMessage.class);
				startActivity(intent);
			}
		});

	}

	public void onStop() {
		super.onStop();
	}
}
