package com.fsoft.DatabaseDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class GridList extends Activity {

	static public GridView grid;
	Spinner spinDept1;
	Cursor deptCursor;
	AlertDialog mdialog;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.gridview);
		grid = (GridView) findViewById(R.id.grid);
		spinDept1 = (Spinner) findViewById(R.id.spinDept1);

	}

	@Override
	public void onStart() {
		super.onStart();
		SQLiteAdapter adapter = new SQLiteAdapter(this);
		adapter.openToRead();
		deptCursor = adapter.getAllDepts();
		startManagingCursor(deptCursor);

		Utilities.ManageDeptSpinner(this.getParent(),deptCursor, spinDept1);
		adapter.close();
		
		try {

			spinDept1.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					LoadGrid();
					// sca.notifyDataSetChanged();

				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub

				}
			});

		} catch (Exception ex) {
			Utilities.catchError(GridList.this, ex.toString(),"Error");
		}

		try {
			grid.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
					// TODO Auto-generated method stub
					try {

						SQLiteCursor cursor = (SQLiteCursor) parent
								.getItemAtPosition(position);
						String name = cursor.getString(cursor
								.getColumnIndex(SQLiteAdapter.colName));
						int age = cursor.getInt(cursor
								.getColumnIndex(SQLiteAdapter.colAge));
						String Dept = cursor.getString(cursor
								.getColumnIndex(SQLiteAdapter.colDeptName));
						
						Employee emp = new Employee(GridList.this ,name, age, Dept);
						emp.SetID((int) id);
						
						mdialog = Utilities.ShowEditDialog(GridList.this, deptCursor,	emp);
						mdialog.setOnDismissListener(new OnDismissListener() {

							@Override
							public void onDismiss(DialogInterface dialog) {
								// TODO Auto-generated method stub
								
								LoadGrid();
							}
						});
						mdialog.show();
						
					} catch (Exception ex) {
						//Utilities.catchError(GridList.this, ex.toString(),"Error");
					}
				}

			});
		} catch (Exception ex) {

		}
	}
	@Override
	public void onDestroy() {

		super.onDestroy();
		if( mdialog != null) mdialog.dismiss();
	}
	
	public void LoadGrid() {
		
		try {

			View v = spinDept1.getSelectedView();
			TextView txt = (TextView) v.findViewById(R.id.txtDeptName);
			String Dept = String.valueOf(txt.getText());
			SQLiteAdapter adapter = new SQLiteAdapter(this);
			
			adapter.openToRead();
			Cursor c = adapter.getEmpByDept(Dept);
			
			startManagingCursor(c);

			String[] from = new String[] { SQLiteAdapter.colName,
					SQLiteAdapter.colAge, SQLiteAdapter.colDeptName };
			int[] to = new int[] { R.id.colName, R.id.colAge, R.id.colDept };
			SimpleCursorAdapter sca = new SimpleCursorAdapter(this,
					R.layout.gridrow, c, from, to);
			
			grid.setAdapter(sca);
			adapter.close();

		} catch (Exception ex) {
			
//			AlertDialog.Builder b = new AlertDialog.Builder(this);
//			b.setMessage(ex.toString());
//			b.show();
			
		}
	}

}
