package com.fsoft.DatabaseDemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class SQLiteAdapter {

	public static final String MYDATABASE_NAME = "demoDB";
	public static final int MYDATABASE_VERSION = 1;
	
	
	static final String EMPLOYEE_TABLE = "Employees";
	static final String colID = "EmployeeID";
	static final String colName = "EmployeeName";
	static final String colAge = "Age";
	static final String colDept = "Dept";

	static final String DEPT_TABLE = "Dept";
	static final String colDeptID = "DeptID";
	static final String colDeptName = "DeptName";

	static final String EMPLOYEE_VIEW = "ViewEmps";


	
	private SQLiteHelper sqLiteHelper;
	private SQLiteDatabase sqLiteDatabase;

	private Context context;
	
	public SQLiteAdapter(Context c){
		context = c;
	}
	
	public SQLiteAdapter openToRead() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getReadableDatabase();
		return this;	
	}
	
	public SQLiteAdapter openToWrite() throws android.database.SQLException {
		sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
		sqLiteDatabase = sqLiteHelper.getWritableDatabase();
		return this;	
	}
	
	public void close(){
		sqLiteHelper.close();
	}
	
	public void addEmployee(Employee emp) {

		ContentValues contentValues = new ContentValues();

		contentValues.put(colName, emp.getName());
		contentValues.put(colAge, emp.getAge());
		contentValues.put(colDept, emp.getDept());
		sqLiteDatabase.insert(EMPLOYEE_TABLE, colName, contentValues);

	}

	public int getEmployeeCount() {

		Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + EMPLOYEE_TABLE, null);
		int x = cursor.getCount();
		cursor.close();
		return x;
	}
	public Cursor getAllEmployees() {

		Cursor cur = sqLiteDatabase.rawQuery("SELECT * FROM " + EMPLOYEE_VIEW, null);

		return cur;

	}
	public int updateEmp(Employee emp) {

		ContentValues cv = new ContentValues();
		
		cv.put(colName, emp.getName());
		cv.put(colAge, emp.getAge());
		cv.put(colDept, emp.getDept());

		return sqLiteDatabase.update(EMPLOYEE_TABLE, cv, colID + "=?",
				new String[] { String.valueOf(emp.getID()) });

	}
	public void deleteEmp(Employee emp) {

		sqLiteDatabase.delete(EMPLOYEE_TABLE, colID + "=?",
				new String[] { String.valueOf(emp.getID()) });

	}

	public Cursor getEmpByDept(String Dept) {

		String[] columns = new String[] { "_id", colName, colAge, colDeptName };
		Cursor c = sqLiteDatabase.query(EMPLOYEE_VIEW, columns, colDeptName + "=?",
				new String[] { Dept }, null, null, null);

		return c;
	}


	public Cursor getAllDepts() {
		
		Cursor cur = sqLiteDatabase.rawQuery("SELECT " + colDeptID + " as _id, "
				+ colDeptName + " from " + DEPT_TABLE, new String[] {});

		return cur;
	}

	public void insertDepts(SQLiteDatabase db) {
		ContentValues cv = new ContentValues();
		
		cv.put(colDeptID, 1);
		cv.put(colDeptName, "Sales");
		db.insert(DEPT_TABLE, colDeptID, cv);

		cv.put(colDeptID, 2);
		cv.put(colDeptName, "IT");
		db.insert(DEPT_TABLE, colDeptID, cv);

		cv.put(colDeptID, 3);
		cv.put(colDeptName, "HR");
		db.insert(DEPT_TABLE, colDeptID, cv);

	}
	public int getDeptID(String Dept) {

		Cursor c = sqLiteDatabase.query(DEPT_TABLE, new String[] { colDeptID + " as _id",
				colDeptName }, colDeptName + "=?", new String[] { Dept }, null,
				null, null);

		c.moveToFirst();
		int ID =  c.getInt(c.getColumnIndex("_id"));
		c.close();
		return ID;

	}
	public String getDept(int ID) {

		String[] params = new String[] { String.valueOf(ID) };
		Cursor c = sqLiteDatabase.rawQuery("SELECT " + colDeptName + " FROM" + DEPT_TABLE
				+ " WHERE " + colDeptID + "=?", params);
		c.moveToFirst();
		int index = c.getColumnIndex(colDeptName);
		String DeptName = c.getString(index);;
		return DeptName;
	}
	public class SQLiteHelper extends SQLiteOpenHelper {

		public SQLiteHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DEPT_TABLE + " (" + colDeptID
					+ " INTEGER PRIMARY KEY , " + colDeptName + " TEXT)");

			db.execSQL("CREATE TABLE " + EMPLOYEE_TABLE + " (" + colID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + colName + " TEXT, "
					+ colAge + " Integer, " + colDept
					+ " INTEGER NOT NULL ,FOREIGN KEY (" + colDept
					+ ") REFERENCES " + DEPT_TABLE + " (" + colDeptID + "));");

			db.execSQL("CREATE TRIGGER fk_empdept_deptid " + " BEFORE INSERT "
					+ " ON " + EMPLOYEE_TABLE +

					" FOR EACH ROW BEGIN" + " SELECT CASE WHEN ((SELECT "
					+ colDeptID + " FROM " + DEPT_TABLE + " WHERE " + colDeptID
					+ "=new." + colDept + " ) IS NULL)"
					+ " THEN RAISE (ABORT,'Foreign Key Violation') END;" + "  END;");

			db.execSQL("CREATE VIEW " + EMPLOYEE_VIEW + " AS SELECT " + EMPLOYEE_TABLE
					+ "." + colID + " AS _id," + " " + EMPLOYEE_TABLE + "."
					+ colName + "," + " " + EMPLOYEE_TABLE + "." + colAge + ","
					+ " " + DEPT_TABLE + "." + colDeptName + "" + " FROM "
					+ EMPLOYEE_TABLE + " JOIN " + DEPT_TABLE + " ON " + EMPLOYEE_TABLE
					+ "." + colDept + " =" + DEPT_TABLE + "." + colDeptID);
			insertDepts(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + DEPT_TABLE);

			db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger");
			db.execSQL("DROP TRIGGER IF EXISTS dept_id_trigger22");
			db.execSQL("DROP TRIGGER IF EXISTS fk_empdept_deptid");
			db.execSQL("DROP VIEW IF EXISTS " + EMPLOYEE_VIEW);
			onCreate(db);
		}

	}
	
}
