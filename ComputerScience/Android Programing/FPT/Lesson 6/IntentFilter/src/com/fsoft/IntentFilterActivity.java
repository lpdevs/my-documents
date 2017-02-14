package com.fsoft;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class IntentFilterActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        

		setContentView(R.layout.main);
		Intent intent = getIntent();
		TextView text = (TextView) findViewById(R.id.textView);
		// To get the action of the intent use
		String action = intent.getAction();
		if (action != Intent.ACTION_VIEW) {
			throw new RuntimeException("Should not happen");
		}
		// To get the data use
		Uri data = intent.getData();
		URL url;
		try {
			url = new URL(data.getScheme(), data.getHost(), data.getPath());
			//BufferedReader rd = new BufferedReader(new InputStreamReader(
			//		url.openStream()));
			//String line = "";
			//while ((line = rd.readLine()) != null) {
			//	text.append(line);
			//}
			text.append("URL: "+ url.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}