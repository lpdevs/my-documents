package com.fsoft.android;



import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class ProgessBArActivity extends Activity {

	Button btnStartProgress;
	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler = new Handler();

	private long fileSize = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		addListenerOnButton();

	}

	public void addListenerOnButton() {

		btnStartProgress = (Button) findViewById(R.id.btnStartProgress);
		btnStartProgress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// prepare for a progress bar dialog
				progressBar = new ProgressDialog(v.getContext());
				progressBar.setCancelable(true);
				progressBar.setMessage("File downloading ...");
				progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressBar.setProgress(0);
				progressBar.setMax(100);
				progressBar.show();

				//reset progress bar status
				progressBarStatus = 0;
				
				//reset filesize
				fileSize = 0;
				
				new Thread(new Runnable() {
					public void run() {
						while (progressBarStatus < 100) {

							// process some tasks
							progressBarStatus = doSomeTasks();

							// your computer is too fast, sleep 1 second
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							// Update the progress bar
							progressBarHandler.post(new Runnable() {
								public void run() {
									progressBar.setProgress(progressBarStatus);
								}
							});
						}

						// ok, file is downloaded,
						if (progressBarStatus >= 100) {

							// sleep 2 seconds, so that you can see the 100%
							try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							// close the progress bar dialog
							progressBar.dismiss();
						}
					}
				}).start();

			}

		});

	}

	

	// file download simulator... a really simple
	public int doSomeTasks() {

		while (fileSize <= 1000000) {

			fileSize++;

			if (fileSize == 100000) {
				return 10;
			} else if (fileSize == 200000) {
				return 20;
			} else if (fileSize == 300000) {
				return 30;
			}
			// ...add your own

		}

		return 100;

	}

}