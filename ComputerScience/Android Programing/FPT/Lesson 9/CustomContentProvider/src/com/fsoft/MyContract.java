package com.fsoft;

import android.net.Uri;

public class MyContract {
	
	private static final String SCHEME ="content://";
	public static final String AUTHORITY ="com.fsoft.MyContentProvider";
	
	public static final class Table1{
		public static final String TABLE_NAME ="table1";
		private static final String PATH ="/"+TABLE_NAME;
		public static final Uri CONTENT_URI =Uri.parse(SCHEME+AUTHORITY+PATH);
		public static final String CONTENT_TYPE ="vnd.android.cursor.dir/vnd.fsoft.table1";
		private Table1(){}
	}
	
	public static final class Table2{
		public static final String TABLE_NAME ="table2";
		private static final String PATH ="/"+TABLE_NAME;
		public static final Uri CONTENT_URI =Uri.parse(SCHEME+AUTHORITY+PATH);
		public static final String CONTENT_TYPE ="vnd.android.cursor.dir/vnd.fsoft.table2";
		public static final String SINGLE_ROW_CONTENT_TYPE ="vnd.android.cursor.item/vnd.fsoft.table2";
		private Table2(){}
	}
	private MyContract(){}
}
