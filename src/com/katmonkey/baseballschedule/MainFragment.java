package com.katmonkey.baseballschedule;

import com.katmonkey.baseballschedule.db.ScheduleDBHelper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;

public class MainFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
	
	private static final int LOADER_ID = 1;
	private SimpleCursorAdapter adapter;
	Cursor cursor;
	private SQLiteDatabase db = null;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//dataColumns are the columns in the database that we need the
		// values from
		String[] dataColumns = { "DATE", "AWAY_TEAM_NAME", "HOME_TEAM_NAME" };
		String[] selectColumns = { "_id", "DATE", "AWAY_TEAM_NAME", "HOME_TEAM_NAME"};
		
		//viewIds are the XML View ids that display the data from
		// dataColumns
		int[] viewIds = { R.id.date, R.id.awayTeamName, R.id.homeTeamName };
		
		db = ScheduleDBHelper.getInstance(getActivity().getApplicationContext());
		cursor = db.query(false, "SCHEDULE_MASTER", selectColumns, null, null, null, null, null, null);
		adapter = new SimpleCursorAdapter(getActivity(), R.layout.schedule_list_item, cursor, dataColumns, viewIds, 0);
		setListAdapter(adapter);
		
		LoaderManager lm = getLoaderManager();
		lm.initLoader(LOADER_ID, null, this);
		
        
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
}
