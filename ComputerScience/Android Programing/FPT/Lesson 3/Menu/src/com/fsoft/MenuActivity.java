package com.fsoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends Activity {
	String[] phones={
			"HTC Rezound",  "Samsung Galaxy S II Skyrocket", 
			"Samsung Galaxy Nexus", "Motorola Droid Razr", 
			"Samsung Galaxy S", "Samsung Epic Touch 4G", 
			"iPhone 4S", "HTC Titan"
			};
		private static final int MENU_NEW_GAME = Menu.FIRST;
		private static final int MENU_QUIT = Menu.FIRST + 1;
		private static final int MENU_EDIT = Menu.FIRST + 2;
		private static final int MENU_DELETE = Menu.FIRST + 3;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.main);
	    	ListView list = (ListView)findViewById(R.id.list);  
	    	ArrayAdapter<String> adapter = 
	        		new ArrayAdapter<String>(this, R.layout.listitem, phones); 
	        	list.setAdapter(adapter);  
	    	list.setAdapter(adapter);  
	    	registerForContextMenu(list);
	    }

		 @Override 
		 public void onCreateContextMenu(ContextMenu menu, View v,  
				                         ContextMenuInfo menuInfo) {  
			 if (v.getId()==R.id.list) {  
				 AdapterView.AdapterContextMenuInfo info 
				      = (AdapterView.AdapterContextMenuInfo)menuInfo;  
				 menu.setHeaderTitle(phones[info.position]);  
				 menu.add(0, MENU_EDIT, 0, "Edit");  
				 menu.add(0, MENU_DELETE, 0,  "Delete");
		 	}  
		 } 

	    /* Creates the menu items */
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {    
	    	menu.add(0, MENU_NEW_GAME, 0, "New Game");     
	    	menu.add(0, MENU_QUIT, 0, "Quit").setIcon(R.drawable.ic_quit);
	    	return true;
	    }

	    /* Handles item selections */
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {    
	    	switch (item.getItemId()) {    
	    		case MENU_NEW_GAME:              
	    			return true;    
	    		case MENU_QUIT:              
	    			return true;    
	    	}    
	    	return false;
	    }

	    @Override
	    public boolean onContextItemSelected(MenuItem item) {  
	    	TextView text = (TextView)findViewById(R.id.footer); 
	    	switch (item.getItemId()) {  
	    		case MENU_EDIT:    
	    			text.setText("Edit selected");    
	    			return true;  
	    		case MENU_DELETE:    
	    			text.setText("Delete selected");  
	    			return true;  
	    		default:    
	    			return super.onContextItemSelected(item);  
	    	}
	    }
}