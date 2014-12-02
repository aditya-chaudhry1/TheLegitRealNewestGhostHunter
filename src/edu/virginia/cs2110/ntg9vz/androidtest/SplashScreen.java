package edu.virginia.cs2110.ntg9vz.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class SplashScreen extends Activity {

	/**
	 * Amount of time in milliseconds to display the splash screen
	 */
	private static final int SPLASH_DELAY_MILLS = 3000;
	
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

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
		
		//Starts the Main Menu after the delay in 
		//milliseconds specified by SPLASH_DELAY_MILLS
		mHandler.postDelayed(mLaunchTask, SPLASH_DELAY_MILLS);
		
	}
	/**
	 * Launches the app's Main Menu
	 */
	private Runnable mLaunchTask = new Runnable() {
		   public void run() {
			   Intent startMenu = new Intent(getApplicationContext(), MainMenu.class);
			   startActivity(startMenu);
			   SplashScreen.this.finish();
		   }
		};
}
