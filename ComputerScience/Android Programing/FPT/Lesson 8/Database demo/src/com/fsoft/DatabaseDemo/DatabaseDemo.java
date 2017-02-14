package com.fsoft.DatabaseDemo;


import android.app.TabActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;




import android.widget.GridView;

import android.widget.TabHost;
import android.widget.TextView;



public class DatabaseDemo extends TabActivity {

	GridView grid;
	TextView txtTest;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SetupTabs();

    }

    
    void SetupTabs()
    {

    	TabHost host=getTabHost();

        TabHost.TabSpec spec=host.newTabSpec("tag1");
        Intent in1=new Intent(this, EmployeeActivity.class);
        spec.setIndicator("Add Employee");
        spec.setContent(in1);
        
        
        
        TabHost.TabSpec spec2=host.newTabSpec("tag2");
        Intent in2=new Intent(this, GridList.class);
        
        spec2.setIndicator("Employees");
        spec2.setContent(in2);
        
        host.addTab(spec);
        host.addTab(spec2);
        
       
    }
    
}