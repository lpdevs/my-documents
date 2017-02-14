package com.fsoft;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class CustomListViewActivity extends Activity {
	
	  private ListView listView1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SocialNetwork weather_data[] = new SocialNetwork[]
         {
             new SocialNetwork(R.drawable.facebook, "Facebook"),
             new SocialNetwork(R.drawable.linkedin, "Linkedin"),
             new SocialNetwork(R.drawable.msn, "MSN"),
             new SocialNetwork(R.drawable.skype, "Skype"),
             new SocialNetwork(R.drawable.yahoo, "Yahoo")
         };
        
         SocialNetworkAdapter adapter = new SocialNetworkAdapter(this,
                 R.layout.listview_item_row, weather_data);
        
        
         listView1 = (ListView)findViewById(R.id.listView1);
         
         View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
         listView1.addHeaderView(header);
        
         listView1.setAdapter(adapter);
    }
}