package edu.virginia.cs2110.ntg9vz.androidtest;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
 
public class Popup extends Activity {


	Player mainCharacter;
	GridLayout gl;
	LinearLayout l2;
	ArrayList<LinearLayout> arrayListOfItemLayouts = new ArrayList<LinearLayout>();
	AsyncTask<LinearLayout,LinearLayout,Double> runningBulletTask = null;
	ArrayList<LinearLayout> listOfBulletLayouts = new ArrayList<LinearLayout>();
	ArrayList<Wall> arrayListOfWalls;
	Activity thisActivity = this;
	
	Wall leftWall;
	Wall topWall;
	Wall bottomWall;
	Wall rightWall;
	
	protected int kills = 0;
	int level = 1;
	int numberOfGhostsLeft;
	
	int width;
	int height;
	
	int delay = 1000;
	int damageIncrease = 0;
	
	private MediaPlayer mp;
	int resID = R.raw.theme;
	
	static final String STATE_LEVEL = "playerLevel";
	static final String STATE_SCORE = "playerScore";
	static final String STATE_HEALTH = "playerHealth";

	
	// Override the onCreate method
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		if (Build.VERSION.SDK_INT < 16) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		} else {
			getActionBar().hide();

			View decorView = getWindow().getDecorView();
			// Hide the status bar.
			int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
			decorView.setSystemUiVisibility(uiOptions);
			

			mp = MediaPlayer.create(this, resID);
			mp.start();
		}
		//d=debug message.   format: tag + message
		Log.d("2110", "slkjfbdskfndsl");
		super.onCreate(savedInstanceState);
		
		//restore game state if there is a saved game
		if(savedInstanceState !=  null) {
			this.level = savedInstanceState.getInt(STATE_LEVEL);
			mainCharacter.kills = savedInstanceState.getInt(STATE_SCORE);
			mainCharacter.remainingHealth = savedInstanceState.getInt(STATE_HEALTH);
		}
		
		this.setRequestedOrientation(
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		mainCharacter = new Player(100, 5, 5, 1, this); //ID = 2
		mainCharacter.setId(2);
		
		
		//this- the savedInstanceState passed into onCreate method

		// For dynamic display (added)
		//ChangeCircles changer = new ChangeCircles(); // new class

		//setContentView(R.layout.popup);
		Log.d("2110", "Do we get here 1?");


		//setContentView(mainCharacter); //set the ContentView to be the circle view
		Log.d("2110", "Do we get here 2?");


		Button button = new Button(this); //Up
		Log.d("2110", "Do we get here 3?");
		button.setBackgroundResource(R.drawable.up);
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				moveMainPlayer(0);
				//moveUp();

			}
		});

		Button button2 = new Button(this); //Down
		button2.setBackgroundResource(R.drawable.down);
		button2.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				moveMainPlayer(2);

				//moveDown();

			}
		});

		Button button3 = new Button(this); //Left
		button3.setBackgroundResource(R.drawable.left);
		button3.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				moveMainPlayer(1);

				//moveLeft();

			}
		});

		Button button4 = new Button(this);
		button4.setBackgroundResource(R.drawable.right);
		button4.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				moveMainPlayer(3);

				//moveRight();

			}
		});

		Button button5 = new Button(this); //Y
		button5.setBackgroundResource(R.drawable.buttony);
		button5.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				fireWeapon(mainCharacter);
			}
		});

		Button button6 = new Button(this); //A
		button6.setBackgroundResource(R.drawable.buttona);
		button6.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				//mainCharacter.fireCurrentWeapon();
			}
		});

		Button button7 = new Button(this); //X
		button7.setBackgroundResource(R.drawable.buttonx);
		button7.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				//mainCharacter.fireCurrentWeapon();
			}
		});

		Button button8 = new Button(this); //B
		button8.setBackgroundResource(R.drawable.buttonb);
		button8.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v) 
			{
				//mainCharacter.fireCurrentWeapon();
			}
		});

		Log.d("2110", "Do we get here 5?");


		
		gl = new GridLayout(this);	//id of 1
		gl.setId(1);
		gl.setColumnCount(10);
		gl.setRowCount(15);	

		setContentView(gl);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		width = size.x;
		height = size.y;

		Log.d("2110", width + "\t" + height);


		for(int row = 0; row < 10; row++) {
			for(int col = 0; col < 10; col++) {

				ImageView box = new ImageView(this);
				box.setImageResource(R.drawable.box);
				
				LinearLayout l = new LinearLayout(this);
		
				LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width/10, height/15);
				
				GridLayout.LayoutParams p = new GridLayout.LayoutParams(GridLayout.spec(row), GridLayout.spec(col));

				box.setLayoutParams(imageParams);
				
				l.addView(box);
				
				//Log.d("2110", "height x width of screen" + height + "\t" + width);

				gl.addView(l, p);


//				Log.d("2110", "height x width of screen" + height + "\t" + width);
//				Log.d("2110", "height x width of box" + box.getHeight() + "\t" + box.getWidth());
//				Log.d("2110", "height x width of layout" + l.getHeight() + "\t" + l.getWidth());

			}
		}


		GridLayout.LayoutParams glp = new GridLayout.LayoutParams (GridLayout.spec(11), GridLayout.spec(2)); //Up
		GridLayout.LayoutParams glp5 = new GridLayout.LayoutParams (GridLayout.spec(13), GridLayout.spec(2)); //Down
		GridLayout.LayoutParams glp3 = new GridLayout.LayoutParams (GridLayout.spec(12), GridLayout.spec(1)); //Left
		GridLayout.LayoutParams glp4 = new GridLayout.LayoutParams (GridLayout.spec(12), GridLayout.spec(3)); //Right
		GridLayout.LayoutParams glp6 = new GridLayout.LayoutParams (GridLayout.spec(11), GridLayout.spec(7)); //Y
		GridLayout.LayoutParams glp7 = new GridLayout.LayoutParams (GridLayout.spec(13), GridLayout.spec(7)); //A
		GridLayout.LayoutParams glp8 = new GridLayout.LayoutParams (GridLayout.spec(12), GridLayout.spec(6)); //X
		GridLayout.LayoutParams glp9 = new GridLayout.LayoutParams (GridLayout.spec(12), GridLayout.spec(8)); //B

		Log.d("2110", "Do we get here 8?");

		LinearLayout l1 = new LinearLayout(this);
		LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(width/10, height/15);
		button.setLayoutParams(lp1);

		LinearLayout l5 = new LinearLayout(this);
		button2.setLayoutParams(lp1);

		LinearLayout l3 = new LinearLayout(this);
		button3.setLayoutParams(lp1);

		LinearLayout l4= new LinearLayout(this);
		button4.setLayoutParams(lp1);

		LinearLayout l6 = new LinearLayout(this);
		button5.setLayoutParams(lp1);

		LinearLayout l7 = new LinearLayout(this);
		button6.setLayoutParams(lp1);

		LinearLayout l8 = new LinearLayout(this);
		button7.setLayoutParams(lp1);

		LinearLayout l9 = new LinearLayout(this);
		button8.setLayoutParams(lp1);
		
		l1.addView(button);
		l5.addView(button2);
		l3.addView(button3);
		l4.addView(button4);
		l6.addView(button5);
		l7.addView(button6);
		l8.addView(button7);
		l9.addView(button8);
		
		
		gl.addView(l1, glp);
		gl.addView(l5, glp5);
		gl.addView(l3, glp3);
		gl.addView(l4, glp4);
		gl.addView(l6, glp6);
		gl.addView(l7, glp7);
		gl.addView(l8, glp8);
		gl.addView(l9, glp9);
		
		
		Log.d("2110", "height x width of button layout" + l1.getHeight() + "\t" + l1.getWidth());
		Log.d("2110", "height x width of button " + button.getHeight() + "\t" + button.getWidth());


		l2 = new LinearLayout(this);
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(width/10, height/15);
		mainCharacter.setLayoutParams(lp2);
		l2.addView(mainCharacter);

		GridLayout.LayoutParams glp2 = new GridLayout.LayoutParams (GridLayout.spec(mainCharacter.getyCoordinate()), GridLayout.spec(mainCharacter.getxCoordinate()));
		gl.addView(l2, glp2);
		Log.d("2110", "Do we get here 8?");


//		Ghost ghost1 = new Ghost(20, 6, 6, 1, 5,  this);
//
//
//		LinearLayout ghostLayout = new LinearLayout(this);
//		LinearLayout.LayoutParams ghostParams = new LinearLayout.LayoutParams(width/10, height/15);
//		ghost1.setLayoutParams(lp2);
//		ghostLayout.addView(ghost1);
//
//		GridLayout.LayoutParams glpGhost = new GridLayout.LayoutParams (GridLayout.spec(ghost1.getyCoordinate()), GridLayout.spec(ghost1.getxCoordinate()));
//		gl.addView(ghostLayout, glpGhost);
//
//
//		Ghost ghost2 = new Ghost(20, 8, 8, 1, 5, this);
//
//
//		LinearLayout ghostLayout2 = new LinearLayout(this);
//		ghost2.setLayoutParams(lp2);
//		ghostLayout2.addView(ghost2);
//
//		GridLayout.LayoutParams glpGhost2 = new GridLayout.LayoutParams (GridLayout.spec(ghost1.getyCoordinate()), GridLayout.spec(ghost1.getxCoordinate()));
//		gl.addView(ghostLayout2, glpGhost2);
//
//		LinearLayout[] arrayOfGhostLayouts = new LinearLayout[]{ghostLayout, ghostLayout2};
//		
//		numberOfGhostsLeft = arrayOfGhostLayouts.length;
//		MoveAllGhosts asyncTaskThatMovesGhosts = new MoveAllGhosts();
//		asyncTaskThatMovesGhosts.execute(arrayOfGhostLayouts);
//		
//		
		
		
		//Items
		
//		Item ak47Pickup = new Item(this, 2, 2, R.drawable.ak47, new AK47(50));
//		
//		
//		LinearLayout lOrig = new LinearLayout(this);
//
//		
//
//		
//		Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 1");
//		LinearLayout.LayoutParams imageParamsOrig = new LinearLayout.LayoutParams(width/10, height/15);
//		GridLayout.LayoutParams pOrig = new GridLayout.LayoutParams(GridLayout.spec(ak47Pickup.getxCoordinate()), GridLayout.spec(ak47Pickup.getyCoordinate()));
//		
//		ak47Pickup.setLayoutParams(imageParamsOrig);
//
//		lOrig.addView(ak47Pickup);
//		Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 2");
//		Log.d("2110", ak47Pickup.getxCoordinate() + "\t" + ak47Pickup.getyCoordinate());
//
//		
//		gl.addView(lOrig, pOrig);
//		Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 3");
//
//		arrayListOfItemLayouts.add(lOrig);
	

		arrayListOfWalls = new ArrayList<Wall>();
		 leftWall = new Wall(0, 0, 0, 9, width/10, height/15, this );
		 topWall = new Wall(0, 0, 9, 0, width/10, height/15, this );
		 bottomWall = new Wall(0, 9, 9, 9, width/10, height/15, this );
		 rightWall = new Wall(9, 0, 9, 9, width/10, height/15, this );

		arrayListOfWalls.add(new Wall(2, 3, 3, 3, width/10, height/15, this ));
		arrayListOfWalls.add(new Wall(6, 3, 6, 4, width/10, height/15, this ));

		setUpNextLevel();
		ImageView healthBar = new ImageView(this);
		healthBar.setImageResource(R.drawable.h6);
		
		LinearLayout l10 = new LinearLayout(this);

		LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width/10, height/15);
		
		GridLayout.LayoutParams p10 = new GridLayout.LayoutParams(GridLayout.spec(14), GridLayout.spec(4));

		healthBar.setLayoutParams(imageParams);
		
		l10.addView(healthBar);
		
		l10.setId(100);
		
		//Log.d("2110", "height x width of screen" + height + "\t" + width);

		gl.addView(l10, p10);
		Context context = getApplicationContext();
		CharSequence text = "Begin Level " + level;
		int duration = Toast.LENGTH_SHORT;
		
		

		Toast toast1 = Toast.makeText(context, text, duration);
		toast1.show();

	}
	
	@Override
	public void onBackPressed() 
	{
		Popup.this.finish();
	}
	
	protected void onSaveInstanceState(Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);
		
		savedInstanceState.putInt(STATE_LEVEL, this.level);
		savedInstanceState.putInt(STATE_SCORE, mainCharacter.kills );
		savedInstanceState.putInt(STATE_HEALTH, mainCharacter.getHealth());
	}
	
	public void setUpNextLevel()
	{
		//Do Ghosts
		Log.d("instantiateGhosts", "20");
		LinearLayout l2 = new LinearLayout(this);
		LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(width/10, height/15);
		
		int numberOfGhosts = level % 5 + 1;
		
		
		
		LinearLayout[] arrayOfGhostLayouts = new LinearLayout[numberOfGhosts];

		Log.d("instantiateGhosts", "21");

		for(int i = 0; i < arrayOfGhostLayouts.length; i ++)
		{
				int startX = (int) (Math.random() * 8 + 1);
				int startY = (int) (Math.random() * 8 + 1);
				
			
			if(level % 5 == 0)
				damageIncrease = level/5 * 1;
			
			Ghost currentGhost = new Ghost(7 ,startX , startY, 1, 1 + damageIncrease,  this);
			Log.d("instantiateGhosts", "22");
			Log.d("instantiateGhosts", "damage increase = " + damageIncrease);

			
			
			for(Wall w: arrayListOfWalls)
			{
				w.block(currentGhost);
			}
			
			Log.d("instantiateGhosts", "23");

			LinearLayout ghostLayout = new LinearLayout(this);
			LinearLayout.LayoutParams ghostParams = new LinearLayout.LayoutParams(width/10, height/15);
			currentGhost.setLayoutParams(lp2);
			ghostLayout.addView(currentGhost);

			Log.d("instantiateGhosts", "24");

			GridLayout.LayoutParams glpGhost = new GridLayout.LayoutParams (GridLayout.spec(currentGhost.getyCoordinate()), GridLayout.spec(currentGhost.getxCoordinate()));
			gl.addView(ghostLayout, glpGhost);
			
			arrayOfGhostLayouts[i] = ghostLayout;
			
			Log.d("instantiateGhosts", "25");

			
			//HealthPacks
			
			if(level > 5)
			{
				

				Item healthPackPickup = new Item(this, 1, 1, R.drawable.healthpack, new HealthPack(100));
				
				
				LinearLayout lOrig = new LinearLayout(this);

				

				
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 1");
				LinearLayout.LayoutParams imageParamsOrig = new LinearLayout.LayoutParams(width/10, height/15);
				GridLayout.LayoutParams pOrig = new GridLayout.LayoutParams(GridLayout.spec(1), GridLayout.spec(1));
				
				healthPackPickup.setLayoutParams(imageParamsOrig);

				lOrig.addView(healthPackPickup);
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 2");
				Log.d("Health Pack", healthPackPickup.getxCoordinate() + "\t" + healthPackPickup.getyCoordinate());
				

				gl.addView(lOrig, pOrig);
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 3");

				arrayListOfItemLayouts.add(lOrig);
			

			}
			
		}
		numberOfGhostsLeft = arrayOfGhostLayouts.length;
		
		
		MoveAllGhosts asyncTaskThatMovesGhosts = new MoveAllGhosts();
		asyncTaskThatMovesGhosts.execute(arrayOfGhostLayouts);
//
//		Ghost ghost2 = new Ghost(20, 8, 8, 1, 5, this);
//
//
//		LinearLayout ghostLayout2 = new LinearLayout(this);
//		ghost2.setLayoutParams(lp2);
//		ghostLayout2.addView(ghost2);
//
//		GridLayout.LayoutParams glpGhost2 = new GridLayout.LayoutParams (GridLayout.spec(ghost1.getyCoordinate()), GridLayout.spec(ghost1.getxCoordinate()));
//		gl.addView(ghostLayout2, glpGhost2);
//
//		LinearLayout[] arrayOfGhostLayouts = new LinearLayout[]{ghostLayout, ghostLayout2};
//		
//		numberOfGhostsLeft = arrayOfGhostLayouts.length;
//		MoveAllGhosts asyncTaskThatMovesGhosts = new MoveAllGhosts();
//		asyncTaskThatMovesGhosts.execute(arrayOfGhostLayouts);
//		
		
	}
	public void fireWeapon(Character character) { //currently only works with instances of guns
		
		Log.d("720", "User fired weapon");
		if(!(character.getCurrentWeapon() instanceof Gun)) {
			return;
		}
		Bullet flyingLead = new Bullet(character.getxCoordinate(), character.getyCoordinate(), character.getCurrentDirection(), 100, this);
		Log.d("720", "flyingLead has been created");
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;

		LinearLayout bulletLayout = new LinearLayout(this);

		LinearLayout.LayoutParams bulletLayoutParams = new LinearLayout.LayoutParams(width/10, height/15);
		flyingLead.setLayoutParams(bulletLayoutParams);
		bulletLayout.addView(flyingLead);

		GridLayout.LayoutParams bulletGridLayoutParams = new GridLayout.LayoutParams (GridLayout.spec(flyingLead.getyCoordinate()), GridLayout.spec(flyingLead.getxCoordinate()));
		gl.addView(bulletLayout, bulletGridLayoutParams);

		listOfBulletLayouts.add(bulletLayout);
		
		Log.d("720", "AsyncTask has been created, about to execute");
		LinearLayout[] arrayOfFlyingBullets = new LinearLayout[listOfBulletLayouts.size()];
		
		int i = 0;
		for(LinearLayout currentBullet : listOfBulletLayouts){
			Log.d("720", "number of children: " + currentBullet.getChildCount());
			arrayOfFlyingBullets[i] = currentBullet;
			i++;
		}
		
		
		if (runningBulletTask != null) {
			Log.d("720", "Cancelling runningBulletTask");
			
			runningBulletTask.cancel(true);
		}
		
		Log.d("720", "Creating MoveAllBullets with " + arrayOfFlyingBullets.length + " bullets");
		MoveAllBullets asyncTaskThatMovesBullets = new MoveAllBullets();
		Log.d("720", "About to call execute on runningBulletTask");
		runningBulletTask = asyncTaskThatMovesBullets.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, arrayOfFlyingBullets);
		Log.d("720", "execute called, status is " + runningBulletTask.getStatus().toString());
		
		//AsyncTask<LinearLayout,LinearLayout,Double> runningBulletTask = asyncTaskThatMovesBullets.execute(arrayOfFlyingBullets);
		
		Log.d("720", "MoveAllBullets execute has been called");
		Log.d("720", "Status is: " + asyncTaskThatMovesBullets.getStatus().toString());
	}
	
	public void moveMainPlayer(int direction)
	{
		mainCharacter.changeDirection(direction);
		mainCharacter.move();
		Log.d("2110", "x: " + mainCharacter.getxCoordinate() + "\t y: " + mainCharacter.getyCoordinate());

		if(mainCharacter.getxCoordinate() <= 0)
		{
			Log.d("WALLWALLWALL", "Left Wall should be blocking + atLeftWall = " + (mainCharacter.getxCoordinate() <= 0));
			Log.d("2110", "x: " + mainCharacter.getxCoordinate() + "\t y: " + mainCharacter.getyCoordinate());

			leftWall.block(mainCharacter);
		}
		if(mainCharacter.getxCoordinate() >= 9)
		{
			Log.d("WALLWALLWALL", "RIght Wall should be blocking + atRightWall = " + (mainCharacter.getxCoordinate() >=9));
			Log.d("2110", "x: " + mainCharacter.getxCoordinate() + "\t y: " + mainCharacter.getyCoordinate());

			rightWall.block(mainCharacter);
			
		}
		if(mainCharacter.getyCoordinate() <= 0)
		{
			Log.d("WALLWALLWALL", "Top Wall should be blocking + atTopWall = " + (mainCharacter.getyCoordinate() <=0));
			Log.d("2110", "x: " + mainCharacter.getxCoordinate() + "\t y: " + mainCharacter.getyCoordinate());

			topWall.block(mainCharacter);
		}
		if(mainCharacter.getyCoordinate() >= 9)
		{
			Log.d("WALLWALLWALL", "Bottom Wall should be blocking + atBottomWall = " + (mainCharacter.getyCoordinate() >=9));
			Log.d("2110", "x: " + mainCharacter.getxCoordinate() + "\t y: " + mainCharacter.getyCoordinate());

			bottomWall.block(mainCharacter);
			
		}
		
	
		if(mainCharacter.getX() <= 0 || mainCharacter.getY() <= 0 || mainCharacter.getX() >= 9 ||  mainCharacter.getY() >= 0 )
			for(Wall w : arrayListOfWalls)
			{
				w.block(mainCharacter);
				
			}
		
		GridLayout.LayoutParams glp = new GridLayout.LayoutParams (GridLayout.spec(mainCharacter.getyCoordinate()), GridLayout.spec(mainCharacter.getxCoordinate()));
		Log.d("2110", "x: " + mainCharacter.getxCoordinate() + "\t y: " + mainCharacter.getyCoordinate());

		Log.d("2110", "2"); //d=debug message.   format: tag + message

		gl.removeView(l2);
		Log.d("2110", "3"); //d=debug message.   format: tag + message

		gl.addView(l2, glp);
		for(LinearLayout currentItemLayout : arrayListOfItemLayouts)
		{
			
			Item currentItem = (Item) currentItemLayout.getChildAt(0);

			int xCoordinateOfThisItem = currentItem.getxCoordinate();
			int yCoordinateOfThisItem = currentItem.getyCoordinate();
			
			if(xCoordinateOfThisItem == mainCharacter.getxCoordinate() && yCoordinateOfThisItem == mainCharacter.getyCoordinate() )
			{
				currentItem.setIsVisible(false);
				gl.removeView(currentItemLayout);
				
				if(currentItem.getWhatThisItemActuallyIs() instanceof Weapon)
				{
					Log.d("2110", mainCharacter.getCurrentWeapon() + "");

					mainCharacter.setCurrentWeapon((Weapon) currentItem.getWhatThisItemActuallyIs());
					Log.d("2110", mainCharacter.getCurrentWeapon() + "");
				}
				
				if(currentItem.getWhatThisItemActuallyIs() instanceof HealthPack)
				{
					Log.d("Health before health pack", mainCharacter.getHealth() + "");
					Log.d("Health  pack has ", ((HealthPack) currentItem.getWhatThisItemActuallyIs()).getHealthImprovement() + "");

					mainCharacter.changeHealth(((HealthPack) currentItem.getWhatThisItemActuallyIs()).getHealthImprovement());
					Log.d("Health after health pack", mainCharacter.getHealth() + "");
				}
					
			}
		}
		
		
		
	}


	class MoveAllGhosts extends AsyncTask<LinearLayout, LinearLayout, Double> {

		// Local Variable
		Character theGuy;

		@Override
		// Enables the circles to "move" (get re-created) with 1 sec intervals
		protected Double doInBackground(LinearLayout... params) 
		{
			int i = 0;
			while (!this.isCancelled()) 
			{		// while not cancelled

				this.publishProgress(params);	//calls onProgressUpdate
				//					Log.i(i + "From doInBackroung", "Yo this is the worker thread and i'm doing stuff in the background right now");
				try {
					Thread.sleep(delay); // 1 sec		// sleep (wait) one second
				} catch (InterruptedException e) {

				}
				i ++;
			}
			return 0.0;
		}


		@Override
		protected void onProgressUpdate(LinearLayout... params)
		{
			
			
			
			
			if(delay == 5000)
			{
				delay = 1000;
			}
			
			if(numberOfGhostsLeft <= 0)
			{
				
				level++;

				Context context = getApplicationContext();
				CharSequence text = "Begin Level " + level;
				int duration = Toast.LENGTH_SHORT;
				
				

				Toast toast1 = Toast.makeText(context, text, duration);
				toast1.show();
				
				try
				{
					Toast toast4 = Toast.makeText(context, "Ghosts Levelling Up...", duration);

					toast4.show();

					Thread.sleep(2000); // 5 sec		// sleep (wait) one second
					Log.d("In MoveALlGhosts", "we should be waiting right now");

				} 
				catch (InterruptedException e) 
				{

				}
				

				try
				{
					Toast toast2 = Toast.makeText(context, text, duration);

					toast2.show();

					Thread.sleep(2000); // 5 sec		// sleep (wait) one second
					Log.d("In MoveALlGhosts", "we should be waiting right now");

				} 
				catch (InterruptedException e) 
				{

				}
			


				
				
				//delay = 5000;
				this.cancel(true);
				Log.d("In MoveALlGhosts", "we good");
				setUpNextLevel();

			}
				
			int j = 0;
			//Log.i(j + "From doInBackroung", "Yo this is the UI thread and i'm doing stuff  right now");
			for(LinearLayout layoutForCurrentGhost : params) 
			{

				GridLayout gL = (GridLayout) findViewById(1);

				Ghost currentGhost = (Ghost) layoutForCurrentGhost.getChildAt(0);
				

				int xCoordinateOfThisGhost = currentGhost.getxCoordinate();
				int yCoordinateOfThisGhost = currentGhost.getyCoordinate();

				
				for(LinearLayout layoutForCurrentBullet : listOfBulletLayouts) 
				{
					Bullet currentBullet = (Bullet)layoutForCurrentBullet.getChildAt(0);
					
					if(currentBullet.getxCoordinate() == xCoordinateOfThisGhost && currentBullet.getyCoordinate() == yCoordinateOfThisGhost)
					{
						currentGhost.changeHealth(currentBullet.getDamage());
						
						currentBullet.setVisible(false);
						gl.removeView(layoutForCurrentBullet);
						
						
					}
				}
				
				if(currentGhost.getHealth() <= 0)
				{
					if(currentGhost.isVisible())
					{
						kills ++;
						numberOfGhostsLeft --;
						currentGhost.setIsVisible(false);
						gL.removeView(layoutForCurrentGhost);

					}
					
					continue;
					
				}
				
				//TODO
				//REMOVE ONCE NATHAN FINISHES COLLISIONS
				//currentGhost.changeHealth(-1);
				
				Log.d("Ghost Healths", "Health of Ghost #"+ j + " = " + currentGhost.getHealth());

				
				

				int xCoordinateOfPlayer = ((Character)findViewById(2)).getxCoordinate();
				int yCoordinateOfPlayer = ((Character)findViewById(2)).getyCoordinate();

				//Log.d("2110", "Ghost: " + xCoordinateOfThisGhost + " , " + yCoordinateOfThisGhost);

				if(xCoordinateOfThisGhost < xCoordinateOfPlayer)
					currentGhost.changeDirection(3);

				else if(xCoordinateOfThisGhost > xCoordinateOfPlayer)
					currentGhost.changeDirection(1);

				else if(yCoordinateOfThisGhost < yCoordinateOfPlayer)
					currentGhost.changeDirection(2);

				else if(yCoordinateOfThisGhost > yCoordinateOfPlayer)
					currentGhost.changeDirection(0);

				else
				{
					//Log.d("2110", "Ghost: " + xCoordinateOfThisGhost + " , " + yCoordinateOfThisGhost);
					mainCharacter.changeHealth(currentGhost.getDamage() * -1);
					Log.d("MainCharacter Health", mainCharacter.getHealth() + "");
					LinearLayout healthBarLayout = (LinearLayout) findViewById(100);
					
					ImageView healthBar = (ImageView) healthBarLayout.getChildAt(0);
					
					gL.removeView(healthBarLayout);
					
					
					if (mainCharacter.getHealth() == 6)
					{

						healthBar.setImageResource(R.drawable.h6);
				    }
				    if (mainCharacter.getHealth() == 5) {
						healthBar.setImageResource(R.drawable.h5);
				    }
				    if (mainCharacter.getHealth() == 4) {
						healthBar.setImageResource(R.drawable.h4);
				    }
				    if (mainCharacter.getHealth() == 3) {
						healthBar.setImageResource(R.drawable.h3);
				    }
				    if (mainCharacter.getHealth() == 2) {
						healthBar.setImageResource(R.drawable.h2);
				    }
				    if (mainCharacter.getHealth() == 1) {
						healthBar.setImageResource(R.drawable.h1);
				    }
				    else 
				    {
						healthBar.setImageResource(R.drawable.h0);
						thisActivity.finish();
						CharSequence text100 = "Your score was" + ((Integer) mainCharacter.kills).toString();

						Toast toast100 = Toast.makeText(thisActivity, text100 , Toast.LENGTH_LONG);

						toast100.show();

						
						
				    }

//					LinearLayout l10 = new LinearLayout(this);
//
//					LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width/10, height/15);
//					
				GridLayout.LayoutParams p10 = new GridLayout.LayoutParams(GridLayout.spec(14), GridLayout.spec(4));
//
//					healthBar.setLayoutParams(imageParams);
//					
//					l10.addView(healthBar);
//					
//					l10.setId(100);
				    
				    gL.addView(healthBarLayout, p10);
					
					continue;

				}

				Log.d("2110", "Ghost: " + xCoordinateOfThisGhost + " , " + yCoordinateOfThisGhost);
				Log.d("2110", "Player: x " + xCoordinateOfPlayer + " , y " + yCoordinateOfPlayer);

				gL.removeView(layoutForCurrentGhost);


				currentGhost.move();
				for(Wall w : arrayListOfWalls)
				{
					w.block(currentGhost);
					
				}

				GridLayout.LayoutParams glp = new GridLayout.LayoutParams (GridLayout.spec(currentGhost.getyCoordinate()), GridLayout.spec(currentGhost.getxCoordinate()));
				Log.d("2110", mainCharacter.getxCoordinate() + "\t" + mainCharacter.getyCoordinate());


				gl.addView(layoutForCurrentGhost, glp);





				//Log.d("2110", "" + currentGhost.getyCoordinate() );
				//currentGhost.invalidate(); // re-draw (delete current and replace)
				// current image is no longer valid
				j ++;

			}
		}

		@Override
		protected void onPostExecute(Double result) { //param based on return type

		}

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected void onCancelled() 
		{

		}



	}
	
	class MoveAllBullets extends AsyncTask<LinearLayout, LinearLayout, Double> {

		@Override
		// Enables the bullets to "move" (get re-created) with .25 sec intervals
		protected Double doInBackground(LinearLayout... params) 
		{
			while (!this.isCancelled()) 
			{		// while not cancelled

				Log.d("720", "MoveAllBullets.doInBackground() was called");
				this.publishProgress(params);	//calls onProgressUpdate
				
				try {
					Thread.sleep(250); 		// sleep (wait) one quarter of a second
				} catch (InterruptedException e) {

				}
			}
			return 0.0;
		}


		@Override
		protected void onProgressUpdate(LinearLayout... params){

			Log.d("720", "Moving " + params.length + " Bullets");
			//for (LinearLayout layoutForCurrentBullet : listOfBulletLayouts)
			for(LinearLayout layoutForCurrentBullet : params) 
			{

				
				//GridLayout gL = (GridLayout) findViewById(1);


				Bullet currentBullet = (Bullet) layoutForCurrentBullet.getChildAt(0);
				
				for(Wall w : arrayListOfWalls)
				{
					w.blockBullet(currentBullet);
					Log.d("BULLET", "" + currentBullet.isThisBulletStillVisible() + "x,y + \t" + currentBullet.getxCoordinate() + "," + currentBullet.getyCoordinate());
					
				}
				
				if(currentBullet.getxCoordinate() <= 0 || currentBullet.getxCoordinate() >= 9 || currentBullet.getyCoordinate() <= 0 || currentBullet.getyCoordinate() >= 9)
				{
					currentBullet.setVisible(false);
					gl.removeView(layoutForCurrentBullet);
					
					continue;
				
				}
				
				
				
					if(!currentBullet.isThisBulletStillVisible())
					{
						
						gl.removeView(layoutForCurrentBullet);
						
						continue;

					}
					

				//Log.d("720", "Found a bullet to move at " + currentBullet.getxCoordinate() + ", " + currentBullet.getyCoordinate());

				//int xCoordinateOfThisBullet = currentBullet.getxCoordinate();
				//int yCoordinateOfThisBullet = currentBullet.getyCoordinate();

				//Log.d("2110", "Bullet: " + xCoordinateOfThisBullet + " , " + yCoordinateOfThisBullet);

				gl.removeView(layoutForCurrentBullet);

				currentBullet.move();

				
				GridLayout.LayoutParams glp = new GridLayout.LayoutParams (GridLayout.spec(currentBullet.getyCoordinate()), GridLayout.spec(currentBullet.getxCoordinate()));

				gl.addView(layoutForCurrentBullet, glp);
			}
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
	
	
}



