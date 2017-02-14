package com.fsoft.android;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.ToggleButton;
import android.widget.Toast;
import android.view.KeyEvent;
import android.widget.CheckBox;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.RatingBar;
public class MyAndroidAppActivity extends Activity {



	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);


		final EditText edittext = (EditText) findViewById(R.id.edittext);
		edittext.setOnKeyListener(new OnKeyListener() {
		    public boolean onKey(View v, int keyCode, KeyEvent event) {
		        // If the event is a key-down event on the "enter" button
		        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
		            (keyCode == KeyEvent.KEYCODE_ENTER)) {
		          // Perform action on key press
		          Toast.makeText(MyAndroidAppActivity.this, edittext.getText(), Toast.LENGTH_SHORT).show();
		          return true;
		        }
		        return false;
		    }
		});
		
		final RatingBar ratingbar = (RatingBar) findViewById(R.id.ratingbar);
		ratingbar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
		    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
		        Toast.makeText(MyAndroidAppActivity.this, "New Rating: " + rating, Toast.LENGTH_SHORT).show();
		    }
		});
	}


	public void onButtonClicked(View v) {
	    // Do something when the button is clicked
	    Toast.makeText(MyAndroidAppActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
	}
	public void onCheckboxClicked(View v) {
	    // Perform action on clicks, depending on whether it's now checked
	    if (((CheckBox) v).isChecked()) {
	        Toast.makeText(MyAndroidAppActivity.this, "Selected", Toast.LENGTH_SHORT).show();
	    } else {
	        Toast.makeText(MyAndroidAppActivity.this, "Not selected", Toast.LENGTH_SHORT).show();
	    }
	}
	public void onRadioButtonClicked(View v) {
	    // Perform action on clicks
	    RadioButton rb = (RadioButton) v;
	    Toast.makeText(MyAndroidAppActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
	}

	public void onToggleClicked(View v) {
	    // Perform action on clicks
	    if (((ToggleButton) v).isChecked()) {
	        Toast.makeText(MyAndroidAppActivity.this, "Toggle on", Toast.LENGTH_SHORT).show();
	    } else {
	        Toast.makeText(MyAndroidAppActivity.this, "Toggle off", Toast.LENGTH_SHORT).show();
	    }
	}

	
}