package com.fpt.sayhelloxml;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/** Gives a simple example of an Android app where the layout
 *  and strings are defined in XML files. An earlier example
 *  does a similar task with layout and strings defined in 
 *  the Java code.
 */

public class SayHelloXml extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    /** Creates a Toast (temporary message) when button is pressed. */
    
    public void showToast(View v) {
        String greetingText = getString(R.string.greeting_text);
        Toast tempMessage =
            Toast.makeText(this, greetingText, 
                           Toast.LENGTH_SHORT);
        tempMessage.show();
    }
}