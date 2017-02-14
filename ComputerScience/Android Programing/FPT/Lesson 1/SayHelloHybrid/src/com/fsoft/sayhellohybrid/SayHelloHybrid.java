package com.fsoft.sayhellohybrid;

import com.fsoft.sayhellohybrid.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/** Gives a simple example of an Android app where the layout
 *  and strings are defined in an XML file (res/layouts/main.xml), 
 *  but where the event handler is defined in Java code.
 *  <p>
 */

public class SayHelloHybrid extends Activity {
    /** Initializes the app when it is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); // Must call this before findViewById. TODO: make other code consistent?
        Button greetingButton = 
                (Button)findViewById(R.id.greeting_button);
        greetingButton.setOnClickListener(new Toaster());
    }
    
    /** Implements View.ClickListener. Uses the named inner class
     *  approach: see later tutorial section on handling GUI events.
     */
    private class Toaster implements OnClickListener {
        /** Creates a Toast (temporary message) when button is pressed. */
        @Override
        public void onClick(View v) {
            String greetingText = getString(R.string.greeting_text);
            Toast tempMessage =
                    Toast.makeText(SayHelloHybrid.this, 
                                   greetingText, 
                                   Toast.LENGTH_SHORT);
            tempMessage.show();
        }
    }
}