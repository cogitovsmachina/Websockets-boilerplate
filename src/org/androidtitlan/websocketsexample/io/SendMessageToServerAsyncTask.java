package org.androidtitlan.websocketsexample.io;

import java.net.URI;
import java.net.URISyntaxException;

import org.androidtitlan.websocketsexample.R;
import org.androidtitlan.websocketsexample.WebsocketsExampleActivity;
import org.androidtitlan.websocketsexample.WebsocketsExampleApplication;

import de.roderick.weberknecht.WebSocket;
import de.roderick.weberknecht.WebSocketConnection;
import de.roderick.weberknecht.WebSocketEventHandler;
import de.roderick.weberknecht.WebSocketException;
import de.roderick.weberknecht.WebSocketMessage;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SendMessageToServerAsyncTask extends
		AsyncTask<String, Void, String> {
	private int randomNumber;
	private Context mContext = WebsocketsExampleApplication.getAppContext();
	private String mMessage = mContext.getString(R.string.hello);
	private String serverIp = mContext.getString(R.string.server_ip);

	private URI url;

	public SendMessageToServerAsyncTask(String message, int randomNumber,
			Context context) {
		this.mMessage = message;
		this.randomNumber = randomNumber;
		this.mContext = context;
	}

	@Override
	protected String doInBackground(String... params) {

		/*
		 * Creating a instance of Websocket implementation from library and
		 * creating a EventHandler to allow two-way communication.
		 */
		try {
			url = new URI("ws://" + serverIp);
			WebSocket mWebsocket = new WebSocketConnection(url);

			mWebsocket.setEventHandler(new WebSocketEventHandler() {

				public void onOpen() {
					Log.d(mContext.getString(R.string.app_name),
							"Opened connection in the server");
				}

				public void onMessage(WebSocketMessage mWebSocketMessage) {
					Log.d(mContext.getString(R.string.app_name), "Message:"
							+ mWebSocketMessage.toString());
				}

				public void onClose() {
					Log.d(mContext.getString(R.string.app_name),
							"The connection has been closed");
				}
			});

			mWebsocket.connect();
			mWebsocket.send(mMessage);

		} catch (WebSocketException wse) {
			wse.printStackTrace();
		} catch (URISyntaxException use) {
			use.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		Toast.makeText(mContext, "Message sent:", Toast.LENGTH_LONG).show();
	}

}
