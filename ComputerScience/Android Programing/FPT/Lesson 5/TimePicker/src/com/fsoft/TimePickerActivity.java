package com.fsoft;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TimePickerActivity extends Activity {
	private int hour, minute;
	 static final int ID_TIMEPICKER = 0;
	  
	  /** Called when the activity is first created. */
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.main);
	     
	      Button startTimePicker = (Button)findViewById(R.id.starttimepicker);
	      startTimePicker.setOnClickListener(new Button.OnClickListener(){
	 
	   @Override
	   public void onClick(View arg0) {
	    // TODO Auto-generated method stub
	    final Calendar c = Calendar.getInstance();
	       hour = c.get(Calendar.HOUR_OF_DAY);
	       minute = c.get(Calendar.MINUTE);
	       showDialog(ID_TIMEPICKER);
	   }});
	  }
	 
	  @Override
	 protected Dialog onCreateDialog(int id) {
	  // TODO Auto-generated method stub
	   switch(id){
	   case ID_TIMEPICKER:
	    return new TimePickerDialog(this, timeSetListener, hour, minute, false);
	   default:
	    return null;
	   }
	 }
	 
	 private TimePickerDialog.OnTimeSetListener timeSetListener
	  = new TimePickerDialog.OnTimeSetListener(){
	 
	  @Override
	  public void onTimeSet(android.widget.TimePicker arg0, int arg1, int arg2) {
	   // TODO Auto-generated method stub
	   Toast.makeText(getBaseContext(),
	           String.valueOf(arg1) + ":" + String.valueOf(arg2),
	           Toast.LENGTH_LONG).show();
	  }};
}