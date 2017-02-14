package com.fsoft.DatabaseDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class EmployeeActivity extends Activity {
	
	EditText txtName;
	EditText txtAge;
	TextView txtEmps;
	private SQLiteAdapter mySQLiteAdapter;
	Spinner spinDept;
	AlertDialog mdialog;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addemployee);
		
		txtName = (EditText) findViewById(R.id.txtName);
		txtAge = (EditText) findViewById(R.id.txtAge);
		txtEmps = (TextView) findViewById(R.id.txtEmps);
		spinDept = (Spinner) findViewById(R.id.spinDept);
	}

	@Override
	public void onStart() {
		try {
			super.onStart();

	        mySQLiteAdapter = new SQLiteAdapter(this);
	        mySQLiteAdapter.openToRead();

			txtEmps.setText(txtEmps.getText()
					+ String.valueOf(mySQLiteAdapter.getEmployeeCount()));

			Cursor cursor = mySQLiteAdapter.getAllDepts();
			startManagingCursor(cursor);

			SimpleCursorAdapter ca = new SimpleCursorAdapter(this,
					R.layout.deptspinnerrow, cursor, new String[] {
					SQLiteAdapter.colDeptName, "_id" },
					new int[] { R.id.txtDeptName });

			spinDept.setAdapter(ca);
			
			mySQLiteAdapter.close();
			
			spinDept.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent,
						View selectedView, int position, long id) {
					// TODO Auto-generated method stub
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});

			// never close cursor
		} catch (Exception ex) {
			Utilities.catchError(this, ex.toString(),"Error");
		}
		Log.i("employee", "onstart");
	}

	@Override
	public void onDestroy() {

		super.onDestroy();
		if( mdialog != null) mdialog.dismiss();
	}

	public void btnAddEmp_Click(View view) {
		boolean ok = true;
		try {
			Spannable spn = txtAge.getText();
			String name = txtName.getText().toString();
			int age = Integer.valueOf(spn.toString());
			int deptID = Integer.valueOf((int) spinDept.getSelectedItemId());
			Employee emp = new Employee(name, age, deptID);
			
			mySQLiteAdapter = new SQLiteAdapter(this);
	        mySQLiteAdapter.openToWrite();
	        mySQLiteAdapter.addEmployee(emp);
	        mySQLiteAdapter.close();
	        
		} catch (Exception ex) {
			
			ok = false;
			Utilities.catchError(this, ex.toString(),"Error");
			
		} finally {
			if (ok) {

				mdialog = Utilities.ShowEmpAddedAlert(this);
				mdialog.show();
				
				mySQLiteAdapter = new SQLiteAdapter(this);
		        mySQLiteAdapter.openToRead();
		        int count = mySQLiteAdapter.getEmployeeCount();
		        mySQLiteAdapter.close();
		        
				txtEmps.setText("Number of employees "
						+ String.valueOf(count));
			}
		}
	}




}
