package edu.virginia.cs2110.ntg9vz.androidtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class Bullet extends ImageView
{

	//Android Stuff
	private Paint paint;
	protected int imageResource;
	
	//App specific Stuff
	protected int xCoordinate;
	protected int yCoordinate;
	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	protected int direction;
	
	protected int range;
	protected int distanceTravlled;
	protected int velocity;
	
	protected int damage;
	
	protected boolean isVisible = true;
	
	public Bullet(int xCoord, int yCoord, int dir, int dam, Context context) {
		super(context);
		
		xCoordinate = xCoord;
		yCoordinate = yCoord;
		direction = dir;
		damage = dam;
		velocity = 1;
			
		//0 = facing up, 1 = facing left, 2 = facing down, 3 = facing right
		
		if (direction == 0)
			imageResource = R.drawable.bulletup;
		
		else if (direction == 1)
			imageResource = R.drawable.bulletleft;
		
		else if (direction == 2)
			imageResource = R.drawable.bulletdown;
		
		else
			imageResource = R.drawable.bulletright;
	}
	
	public Bullet(Context context) {
		super(context);
		init();
	}

	public Bullet(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	public Bullet(Context context, AttributeSet attrs) {
		super(context, attrs);
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

	@Override
	public void onDraw(Canvas c) {
		super.onDraw(c); // this will erase everything and make it white

		// find out height and width of this Canvas:
		int h = this.getMeasuredHeight();
		int w = this.getMeasuredWidth();
		setImageResource(imageResource);
	}
	
	

	
	public int getDamage()
	{
		return damage;
	}
	
	public void move()
	{
		Log.d("720", "Bullet.move() has been called");
		//if (distanceTravlled > range)
		//	isVisible = false;
		
		//if (distanceTravlled < range && isVisible)
		//{
			if (direction == 0)
				yCoordinate -= velocity;
			
			else if (direction == 1)
				xCoordinate -= velocity;
			
			else if (direction == 2)
				yCoordinate += velocity;
			
			else
				xCoordinate += velocity;
			
		//	distanceTravlled += velocity;
	//	} 

	}
	
	public boolean isThisBulletStillVisible()
	{
		return isVisible;
	}
	
	
	public void setVisible(boolean b)
	{
		isVisible = b;
	}
	//public abstract void animateBullet();
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
