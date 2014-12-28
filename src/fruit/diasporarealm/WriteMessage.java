package fruit.diasporarealm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.parse.ParseObject;

public class WriteMessage extends ActionBarActivity {
	EditText messageFullnames;
	EditText messageEmail;
	EditText messageContents;

	ImageButton btn_messageSend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_message);

		messageFullnames = (EditText) findViewById(R.id.message_fullnames_editText);
		messageEmail = (EditText) findViewById(R.id.message_email_editText);
		messageContents = (EditText) findViewById(R.id.message_content_editText);

		btn_messageSend = (ImageButton) findViewById(R.id.message_send_imageButton);
		btn_messageSend.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				sendMessage();
			}
		});
	}

	public void sendMessage() {
		String names = messageFullnames.getText().toString();
		String email = messageEmail.getText().toString();
		String contents = messageContents.getText().toString();

		ParseObject obj = new ParseObject("reports");
		obj.put("full_name", names);
		obj.put("email_address", email);
		obj.put("message_content", contents);
		obj.saveInBackground();

		Intent intent = new Intent(WriteMessage.this, ReportProblem.class);
		startActivity(intent);
		finish();

	}

	public void onStop() {
		super.onStop();
	}
}
