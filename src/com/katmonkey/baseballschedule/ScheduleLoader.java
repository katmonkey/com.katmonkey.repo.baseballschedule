package com.katmonkey.baseballschedule;

import java.util.List;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class ScheduleLoader extends AsyncTaskLoader<List<String>> {
	
	private List<String> schedule;
	
	public ScheduleLoader(Context context) {
		super(context);
	}

	@Override
	public List<String> loadInBackground() {
		// TODO Auto-generated method stub
		return null;
	}

}
