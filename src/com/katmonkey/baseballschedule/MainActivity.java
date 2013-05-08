package com.katmonkey.baseballschedule;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ArrayAdapter;




public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<ArrayAdapter>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String[] schedule = {"This", "is", "a", "ridiculous", "list"};
        ArrayAdapter adapter = new ArrayAdapter(this, R.id.scheduleItem, schedule);
    }

	@Override
	public Loader<ArrayAdapter> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new Loader<ArrayAdapter>(this);
	}

	@Override
	public void onLoadFinished(Loader<ArrayAdapter> loader, ArrayAdapter data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoaderReset(Loader<ArrayAdapter> loader) {
		// TODO Auto-generated method stub
		
	}    
}
