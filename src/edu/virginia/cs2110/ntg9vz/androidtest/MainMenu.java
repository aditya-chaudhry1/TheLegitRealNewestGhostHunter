package edu.virginia.cs2110.ntg9vz.androidtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import android.media.AudioManager;
import android.media.MediaPlayer;
 

public class MainMenu extends Activity {

	Transition TransitionMgr;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		
		// If the Android version is lower than Jellybean, use this call to hide
		// the status bar.
		if (Build.VERSION.SDK_INT < 16) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			getActionBar().hide();

			View decorView = getWindow().getDecorView();
			// Hide the status bar.
			int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
			decorView.setSystemUiVisibility(uiOptions);
			

		}
		
		//Fade in transition:
		//TransitionMgr = TransitionInflater.from(this)
		//	    .inflateTransition(R.transition.transition);
	}
	
	/**
	 * Called when Play button is clicked
	 * @param v
	 */
	public void onPlayClick(View v) {
		//Context context = getApplicationContext();
		//CharSequence text = "This will launch the game";
		//int duration = Toast.LENGTH_SHORT;

		//Toast toast = Toast.makeText(context, text, duration);
		//toast.show();
		  Intent intent = new Intent(getApplicationContext(), Popup.class);
	    	Log.d("2110", "YOOOOOOO"); //d=debug message.   format: tag + message

		//Intent intent = new Intent (this, Popup.class);
    	this.startActivity(intent);
    	Log.d("2110", "The Button was clicked"); //d=debug message.   format: tag + message
    	//MainMenu.this.finish();
	}
	
	/**
	 * Called when the Options button is clicked
	 * @param v
	 */
	public void onOptionsClick(View v) {
		Context context = getApplicationContext();
		CharSequence text = "This will open the options menu";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
	
	/**
	 * Called when High Scores button is clicked
	 * @param v
	 */
	public void onScoresClick(View v) {
		Context context = getApplicationContext();
		CharSequence text = "This will open the high scores";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
