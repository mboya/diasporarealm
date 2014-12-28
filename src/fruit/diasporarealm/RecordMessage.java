package fruit.diasporarealm;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class RecordMessage extends ActionBarActivity {
	private MediaRecorder myAudioRecorder;
	private String outputFile = null;
	private ImageButton start, stop, play;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_message);

		start = (ImageButton) findViewById(R.id.start_imageButton);
		stop = (ImageButton) findViewById(R.id.stop_imageButton);
		play = (ImageButton) findViewById(R.id.play_imageButton);

		stop.setEnabled(false);
		play.setEnabled(false);

		outputFile = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/myrecording.3gp";
		;

		myAudioRecorder = new MediaRecorder();
		myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		myAudioRecorder.setOutputFile(outputFile);

		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				try {
					myAudioRecorder.prepare();
					myAudioRecorder.start();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				start.setEnabled(false);
				stop.setEnabled(true);
				Toast.makeText(getApplicationContext(), "Recording started",
						Toast.LENGTH_LONG).show();
			}
		});

		stop.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				myAudioRecorder.stop();
				myAudioRecorder.release();
				myAudioRecorder = null;
				stop.setEnabled(false);
				play.setEnabled(true);
				Toast.makeText(getApplicationContext(),
						"Audio recorded successfully", Toast.LENGTH_LONG)
						.show();
			}
		});

		play.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				MediaPlayer m = new MediaPlayer();
				try {
					m.setDataSource(outputFile);
					m.prepare();
					m.start();
					Toast.makeText(getApplicationContext(), "Playing audio",
							Toast.LENGTH_LONG).show();
				} catch (IllegalArgumentException | SecurityException
						| IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void onStop() {
		super.onStop();
	}
}
