package com.fsoft.DatabaseDemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Utilities {
	
	static public void ManageDeptSpinner(Context context,Cursor c , Spinner view) {

		
		SimpleCursorAdapter ca = new SimpleCursorAdapter(context,
				R.layout.deptspinnerrow, c, new String[] {
				SQLiteAdapter.colDeptName, "_id" },
				new int[] { R.id.txtDeptName });
		view.setAdapter(ca);

	}
	
	public static AlertDialog  ShowEmpAddedAlert(Context con)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(con);
		builder.setTitle("Add new Employee");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		DialogListner listner=new DialogListner();
		builder.setMessage("Employee Added successfully");
		builder.setPositiveButton("ok", listner);
		
		return builder.create();
		
	}
	
	public static AlertDialog ShowEditDialog(final Context con, Cursor c, final Employee emp)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(con);
		builder.setTitle("Employee Details");
		LayoutInflater li=LayoutInflater.from(con);
		View v=li.inflate(R.layout.editdialog, null);
		
		final TextView txtName=(TextView)v.findViewById(R.id.txtDelName);
		final TextView txtAge=(TextView)v.findViewById(R.id.txtDelAge);
		final Spinner spin=(Spinner)v.findViewById(R.id.spinDiagDept);
		
		builder.setIcon(android.R.drawable.ic_input_get);
		builder.setView(v);

		
		Utilities.ManageDeptSpinner(con, c, spin);
		for(int i=0;i<spin.getCount();i++)
		{
			long id=spin.getItemIdAtPosition(i);
			if(id==emp.getDept())
			{
				spin.setSelection(i, true);
				break;
			}
		}

		txtName.setText(emp.getName());
		txtAge.setText(String.valueOf(emp.getAge()));
		
		builder.setPositiveButton("Modify", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				emp.setName(txtName.getText().toString());
				emp.setAge(Integer.valueOf(txtAge.getText().toString()));
				emp.setDept((int)spin.getItemIdAtPosition(spin.getSelectedItemPosition()));
				
				try
				{
						SQLiteAdapter adapter = new SQLiteAdapter(con);
						adapter.openToWrite();
						adapter.updateEmp(emp);
						adapter.close();
				
				}
				catch(Exception ex)
				{
					catchError(con, ex.toString(),"Error");
				}
			}
		});
		
		builder.setNeutralButton("Delete", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				SQLiteAdapter adapter = new SQLiteAdapter(con);
				adapter.openToWrite();
				adapter.deleteEmp(emp);
				adapter.close();
			}
		});
		builder.setNegativeButton("Cancel", null);
		
		return builder.create();
		//diag.show();
		
	}
	
	static public void catchError(Context con, String Exception, String title)
	{
		Dialog diag=new Dialog(con);
		diag.setTitle(title);
		TextView txt=new TextView(con);
		txt.setText(Exception);
		diag.setContentView(txt);
		diag.show();
	}
}
