package com.fsoft.handlerthread;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class HandlerThread extends Activity{
   	
	private ProgressDialog progressDialog;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((Button) findViewById(R.id.Button01))
        	.setOnClickListener(new OnClickListener() {
        		public void onClick(View view) {
        				DoSomething();
			}      	
        });
    }

	protected void DoSomething() {
		// TODO Auto-generated method stub
		progressDialog = 
				ProgressDialog.show(this, "", "Doing something...");
		new Thread() {
			public void run() {
				try {
					Thread.sleep(5000);
				} 
				catch (InterruptedException e) {
				}
				messageHandler.sendEmptyMessage(0);
			}
		}.start();
	}

	private Handler messageHandler = new Handler() {
		
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			progressDialog.dismiss();
		}
	};
}