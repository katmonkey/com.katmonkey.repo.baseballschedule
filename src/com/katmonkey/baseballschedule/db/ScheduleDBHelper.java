package com.katmonkey.baseballschedule.db;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ScheduleDBHelper extends SQLiteOpenHelper {
	
	private static final String TAG = "ScheduleDBHelper";
	private final static int DB_VERSION = 4;
	private final static String DB_NAME = "baseball_schedule.s3db";
	private static ScheduleDBHelper dbHelperInstance = null;
	private static SharedPreferences firstTimeSp;
	private static final String FIRST_TIME_DB = "first_time_db";
	private Context context;

	private ScheduleDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		Log.d(TAG, "Constructor");
		this.context = context;
		firstTimeSp = this.context.getSharedPreferences(FIRST_TIME_DB, Context.MODE_PRIVATE);
	}
	
	public static SQLiteDatabase getInstance(Context context) {
		if(dbHelperInstance == null) {
			dbHelperInstance = new ScheduleDBHelper(context.getApplicationContext());
		}
		return dbHelperInstance.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "Entering onCreate!");
		boolean firstTime = firstTimeSp.getBoolean("firstTime", true);
		if(firstTime) {
			Log.d(TAG, "This user's first time in the application!");
			//Execute our SQL to build the DB, show splash, activity, etc
			executeSQLScript(db, "schedule_create.sql");
			Editor editor = firstTimeSp.edit();
			editor.putBoolean("firstTime", true);
			editor.commit();
		} else {
			Log.d(TAG, "Not this user's first time in this application!");
		}
		Log.d(TAG, "Created the DB!");
	}
	
	private void executeSQLScript(SQLiteDatabase db, String dbName) {
		Log.d(TAG, "********Entering executeSQLScript!");
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		AssetManager assetManager = context.getAssets();
		InputStream inStream = null;
		try {
			inStream = assetManager.open(dbName);
			while((len = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			outStream.close();
			inStream.close();
			String[] createScript = outStream.toString().split(";");
			for(String str : createScript) {
				String statement = str.trim();
				if(statement.length() > 0) {
					Log.d(TAG, statement);
					db.execSQL(statement + ";");
				}
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}
