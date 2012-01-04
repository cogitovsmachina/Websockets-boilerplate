package org.androidtitlan.websocketsexample;

import java.net.URI;
import java.net.URISyntaxException;

import org.androidtitlan.websocketsexample.io.SendMessageToServerAsyncTask;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import de.roderick.weberknecht.WebSocket;
import de.roderick.weberknecht.WebSocketConnection;
import de.roderick.weberknecht.WebSocketEventHandler;
import de.roderick.weberknecht.WebSocketException;
import de.roderick.weberknecht.WebSocketMessage;

public class WebsocketsExampleActivity extends Activity {
	private TextView serverText;
	public Context mContext = WebsocketsExampleApplication
			.getWebsocketsExampleApplicationContext();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	public void sendMessage(View v) {
		EditText sendText = (EditText) findViewById(R.id.sendtext);
		new SendMessageToServerAsyncTask(sendText.getText().toString(), Math.random(), mContext).execute();
	}
	
}