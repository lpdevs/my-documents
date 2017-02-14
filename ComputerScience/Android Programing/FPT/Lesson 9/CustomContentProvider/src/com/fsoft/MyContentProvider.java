package com.fsoft;


import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.SensorManager;
import android.net.Uri;

public class MyContentProvider extends ContentProvider{
	private static final String DATABASE_NAME="mydb.db";
	private static final UriMatcher MATCHER;
	private static final int TABLE1 = 1;
	private static final int TABLE2 = 2;
	private static final int TABLE2_SINGLE_ROW = 3;
	
	
	private DatabaseHelper db;
	
	static {
		MATCHER=new UriMatcher(UriMatcher.NO_MATCH);
		MATCHER.addURI(MyContract.AUTHORITY, MyContract.Table1.TABLE_NAME, TABLE1);
		MATCHER.addURI(MyContract.AUTHORITY, MyContract.Table2.TABLE_NAME, TABLE2);
		MATCHER.addURI(MyContract.AUTHORITY, MyContract.Table2.TABLE_NAME+"/#", TABLE2_SINGLE_ROW);
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase qb = db.getWritableDatabase();
		int count;
		switch (MATCHER.match(uri)) {
			case TABLE1:
				count = qb.delete(MyContract.Table1.TABLE_NAME, selection, selectionArgs);
				break;
			case TABLE2:
				count = qb.delete(MyContract.Table2.TABLE_NAME, selection, selectionArgs);
				break;
			case TABLE2_SINGLE_ROW:
				String idSelection = "ID = " + uri.getLastPathSegment();
				selection = selection == null? idSelection: selection + " AND " + idSelection;
				count = qb.delete(MyContract.Table2.TABLE_NAME, selection, selectionArgs);
				break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
			 
	    getContext().getContentResolver().notifyChange(uri, null);
	    return count;

	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		switch (MATCHER.match(uri)) {
		case TABLE1:
			return MyContract.Table1.CONTENT_TYPE;
		case TABLE2:
			return MyContract.Table2.CONTENT_TYPE;
		case TABLE2_SINGLE_ROW:
			return MyContract.Table2.SINGLE_ROW_CONTENT_TYPE;
        default:
            throw null;
    }
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		String table ="";
		Uri inserturi=null;	 
		switch (MATCHER.match(uri)) {
			case TABLE1:
				table = MyContract.Table1.TABLE_NAME;
				inserturi = MyContract.Table1.CONTENT_URI;
				break;
			case TABLE2:
				table = MyContract.Table2.TABLE_NAME;
				inserturi = MyContract.Table2.CONTENT_URI;
				break;
	        default:
	        	throw new IllegalArgumentException("Unknown URI " + uri);
			}
	        ContentValues insertvalues;
	        if (values != null) {
	        	insertvalues = new ContentValues(values);
	        } else {
	        	insertvalues = new ContentValues();
	        }
	 
	        SQLiteDatabase qb = db.getWritableDatabase();
	        long rowId = qb.insert(table, null, insertvalues);
	        if (rowId > 0) {
	            Uri noteUri = ContentUris.withAppendedId(inserturi, rowId);
	            getContext().getContentResolver().notifyChange(noteUri, null);
	            return noteUri;
	        }
	        getContext().getContentResolver().notifyChange(uri, null);
			return Uri.parse(table + "/" + rowId);
	        //throw new SQLException("Failed to insert row into " + uri);
	
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		db=new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		String table = "";
		switch(MATCHER.match(uri)){
		case TABLE1:
			table = MyContract.Table1.TABLE_NAME;
			break;
		case TABLE2:
			table = MyContract.Table2.TABLE_NAME;
			break;
		case TABLE2_SINGLE_ROW:
			table = MyContract.Table2.TABLE_NAME;
			String idSelection = "ID = " + uri.getLastPathSegment();
				selection = selection == null? idSelection: selection + " AND " + idSelection;
			break;
		default:
			throw new IllegalArgumentException("wrong URI");
		}
		
		try{
			SQLiteDatabase qb = db.getReadableDatabase();
			Cursor c = qb.query(table, projection, selection, selectionArgs, null, null, sortOrder);
			c.setNotificationUri(getContext().getContentResolver(), uri);
			
			return c;
		}catch(SQLiteException e){
			return null;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		SQLiteDatabase qb = db.getWritableDatabase();
		int count;
		switch (MATCHER.match(uri)) {
			case TABLE1:
				count = qb.update(MyContract.Table1.TABLE_NAME, values, selection, selectionArgs);
				break;
			case TABLE2:
				count = qb.update(MyContract.Table2.TABLE_NAME, values, selection, selectionArgs);
				break;
			case TABLE2_SINGLE_ROW:
				String idSelection = "ID = " + uri.getLastPathSegment();
				selection = selection == null? idSelection: selection + " AND " + idSelection;
				count = qb.update(MyContract.Table2.TABLE_NAME, values, selection, selectionArgs);
				break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
			 
	    getContext().getContentResolver().notifyChange(uri, null);
	    return count;
	}

	private class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, 1);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
//			Cursor c=db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=" +MyContract.Table1.TABLE_NAME, null);
//			
//			try {
//				if (c.getCount()==0) {
//					db.execSQL("CREATE TABLE "+ MyContract.Table1.TABLE_NAME +"(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, value REAL);");
//					
//
//				}
//			}
//			finally {
//				c.close();
//			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			android.util.Log.w("Constants", "Upgrading database, which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS constants");
			onCreate(db);
		}
	}
}
