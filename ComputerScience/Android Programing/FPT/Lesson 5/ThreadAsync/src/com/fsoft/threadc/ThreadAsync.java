package com.fsoft.threadc;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;

public class ThreadAsync extends ListActivity {
	private static String[] items={
		"Hello, Android: Introducing Google's Mobile Development Platform ", 
		"Professional Android 4 Application Development ", 
		"Unlocking Android: A Developer's Guide",
		"Android Application Development: Programming with the Google SDK", 
		"Pro Android 4", 
		"Beginning Android 4",
		"Android Programming Tutorials, 3rd Edition", 
		"Android Wireless Application Development", 
		"Pro Android Games",
		"Beginning Smartphone Web Development", 
		"The Busy Coder's Guide to Advanced Android Development", 
		"Head First Java, 3rd Edition",
		"Effective Java (3rdd Edition)",
		"Sams Teach Yourself Java in 24 Hours (5th Edition)",
		"Core Java(TM), Volume I--Fundamentals (8th Edition)",
		"Java In A Nutshell, 5th Edition",
		"Thinking in Java (4th Edition)",
		"Java Concurrency in Practice",
		"SCJP Sun Certified Programmer for Java 7 Exam 310-065",
		"Java How to Program, 7th Edition",
		"Learning Java",
		"Head First Design Patterns",
		"Beginning Java EE 7 Platform with GlassFish 3",
		"Head First Servlets and JSP",
		"Java Message Service",
		"Core Java(TM), Volume I--Fundamentals (9th Edition)",
		"Beginning Programming with Java For Dummies"
			};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		setListAdapter(
				new ArrayAdapter<String>(this,
								android.R.layout.simple_list_item_1,
								new ArrayList<String>()));
		new AddStringTask().execute();
	}
	
	class AddStringTask extends AsyncTask<Void, String, Void> {
		@Override
		protected Void doInBackground(Void... unused) {
			for (String item : items) {
				publishProgress(item);
				SystemClock.sleep(200);
			}
			
			return(null);
		}
		
		@Override
		protected void onProgressUpdate(String... item) {
			((ArrayAdapter<String>)getListAdapter()).add(item[0]);
		}
		
		@Override
		protected void onPostExecute(Void unused) {
			Toast
				.makeText(ThreadAsync.this, 
						"Done - Finished updating Java Book List!",
						Toast.LENGTH_SHORT)
				.show();
		}
	}
}
