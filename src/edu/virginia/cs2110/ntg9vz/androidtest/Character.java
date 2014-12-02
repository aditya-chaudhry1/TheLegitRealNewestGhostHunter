package edu.virginia.cs2110.ntg9vz.androidtest;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public abstract class Character extends ImageView
{
	//Android Stuff
	private Paint paint;
	protected int imageResource;
	
	
	public Character(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	public Character(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Character(Context context) {
		super(context);
		//this.imageResource =  imageResource;
		init();
	}
	
	public void init() {
		paint = new Paint();
		paint.setColor(0xff00ff00); // single int in hex (Green)
		paint.setStyle(Paint.Style.FILL);
		  // first ff = opaque;  first 00 = no red
		  // second ff = green all the way on;  second 00 = no blue
		  // could also do: paint.setColor(Color.GREEN);
		//paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(3);
	}
	
	// Need to override the onDraw method to tell it what to draw
		@Override
		public void onDraw(Canvas c) {
			super.onDraw(c); // this will erase everything and make it white
			  // (the default background color), or else we will see the 
			  // previous version of view (overwriting)
			
			//First tiny circle we drew:
			// Format:  c.drawCircle(cx, cy, radius, paint);
			// c.drawCircle(40, 20, 15, paint); // small circle
			
			// find out height and width of this Canvas:
			int h = this.getMeasuredHeight();
			int w = this.getMeasuredWidth();
			
			//setFrame(xCoordinate, yCoordinate, xCoordinate + 50, yCoordinate + 50);
			
//			 Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guy);
//	          c.drawBitmap(myBitmap, xCoordinate, xCoordinate, null);
//	          
			
			setImageResource(imageResource);
//			
//			setScaleX((float) h/(10 * getDrawable().getIntrinsicHeight()));
//			setScaleY((float) w/(10 * getDrawable().getIntrinsicWidth()));
	          //setFrame(xCoordinate, yCoordinate,xCoordinate + 50, yCoordinate + 50);
			
			// ** STATIC ** //
			// c.drawCircle(cx, cy, radius, paint);
			// centering on x (width), centering on y (height)
			// taking min of {width, height} and / by 2
			// (second circle, dividing by 4, for a different sized circle)
//			c.drawCircle(w/2, h/2, Math.min(w, h)/2, paint);
//			c.drawCircle(w/2, h/2, Math.min(w, h)/4, paint); //second circle
			
			// ** DYNAMIC DISPLAY ** //
			//int x = (int)(Math.random()*w); // so that x is different each time
			//c.drawCircle(xCoordinate, yCoordinate, 20, paint);
			//c.drawCircle(x, h/2, Math.min(w, h)/4, paint); //second circle			
		}
		
//----------------------------------------------------------------------------------------------------------------------------------------
	
	//Actual stuff
		protected int xCoordinate;
		protected int yCoordinate;
		protected int displayXCoordinate;
		protected int displayYCoordinate;
		protected int kills;
		
		/*(0,5)
		 * -
		 * -
		 * -
		 * -
		 * (0,0) - - - - (5,0)
		 */
		protected int velocity;	//How many grid points to move on one interation of move()
		protected int currentDirection;	//0 = facing up, 1 = facing left, 2 = facing down, 3 = facing right
		
		protected int maxHealth;
		protected int remainingHealth;
		
		//protected ArrayList<Weapon> listOfWeapons;
	//	protected int currentWeaponIndex; //index pointing to which weapon in listOfWeapons is being used
		
		protected Weapon currentWeapon;
		
		protected boolean isEnemy;
		
		//Do image somehow
		
	public void move()//0 = up, 1 = left, 2 = down, 3 = right
	{
		if (currentDirection == 0)
			yCoordinate -= velocity;
		
		else if (currentDirection == 1)
			xCoordinate -= velocity;
		
		else if (currentDirection == 2)
			yCoordinate += velocity;
		
		else
			xCoordinate += velocity;
		
		
		   if (this.getxCoordinate() > 9) {
	            this.setxCoordinate(0);
	        }

	        if (this.getxCoordinate() < 0) {
	            this.setxCoordinate(9);
	        }

	        if (this.getyCoordinate() > 9) {
	            this.setyCoordinate(0);
	        }

	        if (this.getyCoordinate() < 0) {
	            this.setyCoordinate(9);
	        }
		
		
	
	}
	
//	public void switchWeapon()
//	{
//		currentWeaponIndex += 1;
//		
//		if (currentWeaponIndex >= listOfWeapons.size())
//			currentWeaponIndex = 0;
//	}
	
	public void fireCurrentWeapon()
	{
		currentWeapon.attack();
		//listOfWeapons.get(currentWeaponIndex).attack();
	}
	
	public void changeHealth(int changeInHealth)
	{
		if( remainingHealth + changeInHealth < maxHealth)
			remainingHealth += changeInHealth;
		
		else
		{
			Log.d("Stepping on health pack should bring us here before", "" + remainingHealth);
			remainingHealth = maxHealth;
			Log.d("Stepping on health pack should bring us here after", "" + remainingHealth);

			
		}
	}
	
	public int getHealth()
	{
		return remainingHealth;
	}
	
	
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(int currentDirection) {
		this.currentDirection = currentDirection;
	}

	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	public void setCurrentWeapon(Weapon newWeapon) 
	{
		currentWeapon = newWeapon;
	}
}
