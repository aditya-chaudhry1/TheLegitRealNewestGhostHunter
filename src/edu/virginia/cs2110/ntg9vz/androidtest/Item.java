package edu.virginia.cs2110.ntg9vz.androidtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class Item extends ImageView
{

	
	private Paint paint;
	protected int imageResource;
	protected int xCoordinate;
	protected int yCoordinate;
	
	protected Object whatThisItemActuallyIs;
	protected boolean isVisible = true;
	
	public Item(Context context, AttributeSet attrs, int defStyleAttr) 
	{
		super(context, attrs, defStyleAttr);
		init();
	}

	public Item(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Item(Context context, int x, int y, int imageResourceID, Object actualItem) 
	{
		super(context);
		//this.imageResource =  imageResource;
		init();
		
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.imageResource = imageResourceID;
		this.whatThisItemActuallyIs = actualItem;
	}
	
	public void init() {
		paint = new Paint();
		paint.setColor(0xff00ff00); // single int in hex (Green)
		paint.setStyle(Paint.Style.FILL);
		 
		paint.setStrokeWidth(3);
	}
	
	// Need to override the onDraw method to tell it what to draw
		@Override
		public void onDraw(Canvas c) {
			super.onDraw(c); // this will erase everything and make it white
			
			// find out height and width of this Canvas:
			int h = this.getMeasuredHeight();
			int w = this.getMeasuredWidth();
			
			
			setImageResource(imageResource);			
		}
		
		public int getxCoordinate() {
			return xCoordinate;
		}

		public int getyCoordinate() {
			return yCoordinate;
		}

		public boolean getIsVisible()
		{
			return isVisible;
		}
		
		public void setIsVisible(boolean isItVisibleNow)
		{
			this.isVisible = isItVisibleNow;
		}
		
		public Object getWhatThisItemActuallyIs()
		{
			return whatThisItemActuallyIs;
		}
}
