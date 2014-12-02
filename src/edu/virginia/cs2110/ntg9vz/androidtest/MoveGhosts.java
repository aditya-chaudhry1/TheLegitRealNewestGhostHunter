package edu.virginia.cs2110.ntg9vz.androidtest;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;

public class MoveGhosts extends AsyncTask<Character, Character, Double> {

	// Local Variable
	Character theGuy;
	
	@Override
	// Enables the circles to "move" (get re-created) with 1 sec intervals
	protected Double doInBackground(Character... params) 
	{
		int i = 0;
		while (!this.isCancelled()) {		// while not cancelled
			this.publishProgress(params);	//calls onProgressUpdate
//			Log.i(i + "From doInBackroung", "Yo this is the worker thread and i'm doing stuff in the background right now");
			try {
			Thread.sleep(500); // .5 sec		// sleep (wait) one second
			} catch (InterruptedException e) {
			
			}
			 i ++;
		}
		return 0.0;
	}
		
		
	@Override
	protected void onProgressUpdate(Character... params){
		int j = 0;
		Log.i(j + "From doInBackroung", "Yo this is the UI thread and i'm doing stuff  right now");
		for(Character thisGhost : params) 
		{
			thisGhost.setCurrentDirection(0);
			thisGhost.move();
			//GridLayout gL = (GridLayout) findViewById(1);
			Log.d("2110", "" + thisGhost.getyCoordinate() );
			thisGhost.invalidate(); // re-draw (delete current and replace)
			                 // current image is no longer valid
		}
		j ++;
	}
	
	@Override
	protected void onPostExecute(Double result) { //param based on return type
		
	}
	
	@Override
	protected void onPreExecute() {
		
	}
	
	@Override
	protected void onCancelled() {
		
	}

	
	
}
