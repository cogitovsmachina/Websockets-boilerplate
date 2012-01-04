package org.androidtitlan.websocketsexample;

import android.app.Application;
import android.content.Context;

public class WebsocketsExampleApplication extends Application {
	private static Context context;

	@Override
	public void onCreate() {
		WebsocketsExampleApplication.context = getApplicationContext();
		super.onCreate();
	}

	public static Context getAppContext() {
		return context;
	}
}
