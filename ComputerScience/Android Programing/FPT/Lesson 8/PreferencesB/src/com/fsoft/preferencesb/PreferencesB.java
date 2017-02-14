package com.fsoft.preferencesb;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


public class PreferencesB extends Activity {
	//private static final int MENU_EDIT_PREF = Menu.FIRST;
	
	private TextView checkbox=null;
	private TextView ringtone=null;
	private TextView checkbox2=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		checkbox=(TextView)findViewById(R.id.checkbox);
		ringtone=(TextView)findViewById(R.id.ringtone);
		checkbox2=(TextView)findViewById(R.id.checkbox2);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		SharedPreferences prefs=PreferenceManager
				.getDefaultSharedPreferences(this);
		checkbox.setText(new Boolean(prefs.getBoolean("checkbox", false))
				.toString());
		ringtone.setText(prefs.getString("ringtone", "<unset>"));
		checkbox2.setText(new Boolean(prefs.getBoolean("checkbox2", false))
				.toString());
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {       
    		case R.id.edit_pref:
    			startActivity(new Intent(this, EditPreferences.class));        
    			return true;      
    	}  
		return(super.onOptionsItemSelected(item));
	}
}
