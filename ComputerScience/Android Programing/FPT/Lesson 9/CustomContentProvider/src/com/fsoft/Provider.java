 package com.fsoft;

import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.hardware.SensorManager;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import java.util.HashMap;


public class Provider extends ContentProvider {
	private static final String DATABASE_NAME="songs.db";
	private static final int SONGS=1;
	private static final int SONG_ID=2;
	private static final UriMatcher MATCHER;
	private static HashMap<String, String> CONSTANTS_LIST_PROJECTION;
	
	public static final class Songs implements BaseColumns {
		public static final Uri CONTENT_URI
				 =Uri.parse("content://com.fsoft.Provider/songs");
		public static final String DEFAULT_SORT_ORDER="title";
		public static final String TITLE="title";
		public static final String AUTHOR="author";
		public static final String TABLE_NAME="songs";
	}

	static {
		MATCHER=new UriMatcher(UriMatcher.NO_MATCH);
		MATCHER.addURI("com.fsoft.Provider", Songs.TABLE_NAME, SONGS);
		MATCHER.addURI("com.fsoft.Provider", Songs.TABLE_NAME+"/#", SONG_ID);

		CONSTANTS_LIST_PROJECTION=new HashMap<String, String>();
		CONSTANTS_LIST_PROJECTION.put(Provider.Songs._ID, Provider.Songs._ID);
		CONSTANTS_LIST_PROJECTION.put(Provider.Songs.TITLE, Provider.Songs.TITLE);
		CONSTANTS_LIST_PROJECTION.put(Provider.Songs.AUTHOR, Provider.Songs.AUTHOR);
	}

	public String getDbName() {
		return(DATABASE_NAME);
	}
	
	public int getDbVersion() {
		return(1);
	}
	
	private class DatabaseHelper extends SQLiteOpenHelper {
		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, 1);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			//Cursor c=db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name = "+Songs.TABLE_NAME, null);
			
			try {
				//if (c.getCount()==0) {
					db.execSQL("CREATE TABLE " + Songs.TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author TEXT);");
					
					ContentValues cv=new ContentValues();
					
					cv.put(Songs.TITLE, "Somebody That I Used To Know");
					cv.put(Songs.AUTHOR, "Gotye Featuring Kimbra");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Call Me Maybe");
					cv.put(Songs.AUTHOR, "Carly Rae Jepsen");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Payphone");
					cv.put(Songs.AUTHOR, "Maroon 5 Featuring Wiz Khalifa");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "We Are Young");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Where Have You Been");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Part Of Me");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Feel So Close");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Drive By");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Boyfriend");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Glad You Came");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "What Makes You Beautiful");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Wild Ones");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
					
					cv.put(Songs.TITLE, "Starships");
					cv.put(Songs.AUTHOR, "unknown");
					db.insert(Songs.TABLE_NAME, getNullColumnHack(), cv);
				//}
			}
			finally {
				//c.close();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			android.util.Log.w(Songs.TABLE_NAME, "Upgrading database, which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + Songs.TABLE_NAME);
			onCreate(db);
		}
	}
	
	private SQLiteDatabase db;
	
	@Override
	public boolean onCreate() {
		db=(new DatabaseHelper(getContext())).getWritableDatabase();
		
		return (db == null) ? false : true;
	}
	
	@Override
	public Cursor query(Uri url, String[] projection, String selection,
												String[] selectionArgs, String sort) {
		SQLiteQueryBuilder qb=new SQLiteQueryBuilder();

		qb.setTables(getTableName());
		
		if (isCollectionUri(url)) {
			qb.setProjectionMap(getDefaultProjection());
		}
		else {
			qb.appendWhere( getIdColumnName()+"="+url.getPathSegments().get(1) );
		}
		
		String orderBy;
		
		if (TextUtils.isEmpty(sort)) {
			orderBy=getDefaultSortOrder();
		} else {
			orderBy=sort;
		}

		Cursor c=qb.query(db, projection, selection, selectionArgs,
											null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), url);
		return c;
	}

	@Override
	public String getType(Uri url) {
		if (isCollectionUri(url)) {
			return(getCollectionType());
		}
		
		return(getSingleType());
	}

	@Override
	public Uri insert(Uri url, ContentValues initialValues) {
		long rowID;
		ContentValues values;
		
		if (initialValues!=null) {
			values=new ContentValues(initialValues);
		} else {
			values=new ContentValues();
		}

		if (!isCollectionUri(url)) {
			throw new IllegalArgumentException("Unknown URL " + url);
		}
		
		for (String colName : getRequiredColumns()) {
			if (values.containsKey(colName) == false) {
				throw new IllegalArgumentException("Missing column: "+colName);
			}
		}

		populateDefaultValues(values);

		rowID=db.insert(getTableName(), getNullColumnHack(), values);
		if (rowID > 0) {
			Uri uri=ContentUris.withAppendedId(getContentUri(), rowID);
			getContext().getContentResolver().notifyChange(uri, null);
			return uri;
		}

		throw new SQLException("Failed to insert row into " + url);
	}

	@Override
	public int delete(Uri url, String where, String[] whereArgs) {
		int count;
		//long rowId=0;
		
		if (isCollectionUri(url)) {
			count=db.delete(getTableName(), where, whereArgs);
		}
		else {
			String segment=url.getPathSegments().get(1);
			//rowId=Long.parseLong(segment);
			count=db
					.delete(getTableName(), getIdColumnName()+"="
							+ segment
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
		}

		getContext().getContentResolver().notifyChange(url, null);
		return count;
	}

	@Override
	public int update(Uri url, ContentValues values, String where, String[] whereArgs) {
		int count;
		
		if (isCollectionUri(url)) {
			count=db.update(getTableName(), values, where, whereArgs);
		}
		else {
			String segment=url.getPathSegments().get(1);
			count=db
					.update(getTableName(), values, getIdColumnName()+"="
							+ segment
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
		}
	
		getContext().getContentResolver().notifyChange(url, null);
		return count;
	}
	
	private boolean isCollectionUri(Uri url) {
		return(MATCHER.match(url)==SONGS);
	}
	
	private HashMap<String, String> getDefaultProjection() {
		return(CONSTANTS_LIST_PROJECTION);
	}
	
	private String getTableName() {
		return Songs.TABLE_NAME;
	}
	
	private String getIdColumnName() {
		return("_id");
	}
	
	private String getDefaultSortOrder() {
		return("title");
	}
	
	private String getCollectionType() {
		return("vnd.android.cursor.dir/vnd.fsoft.song");
	}
	
	private String getSingleType() {
		return("vnd.android.cursor.item/vnd.fsoft.song");
	}
	
	private String[] getRequiredColumns() {
		return(new String[] {"title"});
	}
	
	private void populateDefaultValues(ContentValues values) {
		//Long now=Long.valueOf(System.currentTimeMillis());
		//Resources r=Resources.getSystem();

		if (values.containsKey(Provider.Songs.AUTHOR) == false) {
			values.put(Provider.Songs.AUTHOR, "unknown");
		}
	}
	
	private String getNullColumnHack() {
		return("title");
	}
	
	private Uri getContentUri() {
		return(Provider.Songs.CONTENT_URI);
	}
}