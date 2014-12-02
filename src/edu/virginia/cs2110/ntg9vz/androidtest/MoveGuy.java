package edu.virginia.cs2110.ntg9vz.androidtest;


import android.os.AsyncTask;
import android.util.Log;

public class MoveGuy extends AsyncTask<Character, Character, Double> {

	// Local Variable
	Character theGuy;
	
	@Override
	// Enables the circles to "move" (get re-created) with 1 sec intervals
	protected Double doInBackground(Character... params) {
		int i = 0;
		//while (!this.isCancelled()) {		// while not cancelled
			this.publishProgress(params);	//calls onProgressUpdate
//			Log.i(i + "From doInBackroung", "Yo this is the worker thread and i'm doing stuff in the background right now");
//			try {
//			Thread.sleep(10000); // 1sec		// sleep (wait) one second
//			} catch (InterruptedException e) {
//				
//			}
//			 i ++;
	//	}
		return 0.0;
	}
		
	@Override
	protected void onProgressUpdate(Character... params){
		int j = 0;
		Log.i(j + "From doInBackroung", "Yo this is the UI thread and i'm doing stuff  right now");
		for(Character guy : params) 
		{
			guy.setCurrentDirection(0);
			guy.move();
			Log.d("2110", "" + guy.getyCoordinate() );
			guy.invalidate(); // re-draw (delete current and replace)
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
