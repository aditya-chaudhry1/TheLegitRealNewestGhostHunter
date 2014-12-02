package edu.virginia.cs2110.ntg9vz.androidtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Wall extends ImageView
{

	protected int xStart;
    protected int xFin;
    protected int yStart;
    protected int yFin;
    protected int width;
    protected int height;


    public Wall(int x1, int y1, int x2, int y2, int w, int h, Context c) 
	{
		super(c);
		Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 4");

		width = w;
		height = h;
		
		if(x1 == x2 && x1 < 10 && x1 >= 0 && x2 < 10 && x2 >= 0 && y1 < 10 && y1 >= 0 && y2 < 10 && y2 >= 0) 
        {
            this.xStart = x1;
            this.xFin = x1;
            this.yStart = y1;
            this.yFin = y2;
        }

        if(y1 == y2 && x1 < 10 && x1 >= 0 && x2 < 10 && x2 >= 0 && y1 < 10 && y1 >= 0 && y2 < 10 && y2 >= 0) {
            this.xStart = x1;
            this.xFin = x2;
            this.yStart = y1;
            this.yFin = y1;
        }
        
        setImageResource(R.drawable.wall);
		Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 5");

        wallDrop();
	}

    public void wallDrop () 
    {
		Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 6");
		Activity a = (Activity) getContext();
		
		GridLayout gL = (GridLayout) a.findViewById(1);
		Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 6.5" + gL.toString());

		Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 7");

        if (this.xStart == this.xFin) 
        {
            for(int i = this.yStart; i <= this.yFin; i++) 
            {
            	
            	ImageView peiceOfWall = new ImageView(getContext());
            	peiceOfWall.setImageResource(R.drawable.wall);
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 8");

				LinearLayout l = new LinearLayout(getContext());
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 8a");

				LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width, height);
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 8b");

				GridLayout.LayoutParams p = new GridLayout.LayoutParams(GridLayout.spec(i), GridLayout.spec(xStart));
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 9");

				peiceOfWall.setLayoutParams(imageParams);
				
				l.addView(peiceOfWall);
				
				gL.addView(l, p);
				

			}
        }
        if (this.yStart == this.yFin) 
        {
            for(int i = this.xStart; i <= this.xFin; i++) 
            {
            	ImageView peiceOfWall = new ImageView(getContext());
            	peiceOfWall.setImageResource(R.drawable.wall);
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 8");

				LinearLayout l = new LinearLayout(getContext());
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 8a");

				LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(width, height);
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 8b");

				GridLayout.LayoutParams p = new GridLayout.LayoutParams(GridLayout.spec(yStart), GridLayout.spec(i));
				Log.d("sofjnodskfdosipjfopidsfj", "Yo Yo Yo We Here 9");

				peiceOfWall.setLayoutParams(imageParams);
				
				l.addView(peiceOfWall);
				gL.addView(l, p);

				

            }
        }
     }
    public boolean vertWall() {
        if (this.xFin == this.xStart) {
            return true;
        }
        return false;
    }


    public void block(Character p) {
    	
        if (this.vertWall() == true && p.getxCoordinate() == this.xFin && p.getyCoordinate() <= this.yFin && p.getyCoordinate() >= this.yStart)
        {
        	Log.d("WALLWALLWALLWALL", "side walls");

            if (p.currentDirection == 0) {
                int a = p.getyCoordinate();
                p.setyCoordinate(a + 1);
            }
            if (p.currentDirection == 1) {
                int a = p.getxCoordinate();
                p.setxCoordinate(a + 1);
            }
            if (p.currentDirection == 2) {
                int a = p.getyCoordinate();
                p.setyCoordinate(a - 1);
            }
            if (p.currentDirection == 3) {
                int a = p.getxCoordinate();
                p.setxCoordinate(a - 1);
            }
        }

        if (this.vertWall() == false && p.getyCoordinate() == this.yFin && p.getxCoordinate() <= this.xFin && p.getxCoordinate() >= this.xStart) 
        {
        	Log.d("WALLWALLWALLWALL", "top & bottom wall");
            if (p.currentDirection == 0) {
                int a = p.getyCoordinate();
                p.setyCoordinate(a + 1);
            }
            if (p.currentDirection == 1) {
                int a = p.getxCoordinate();
                p.setxCoordinate(a + 1);
            }
            if (p.currentDirection == 2) {
                int a = p.getyCoordinate();
                p.setyCoordinate(a - 1);
            	Log.d("Bottom Wall Has Blocked", "new y coordinate: "+ p.getyCoordinate());

            }
            if (p.currentDirection == 3) {
                int a = p.getxCoordinate();
                p.setxCoordinate(a - 1);
            }
        }

    }
    
    

    public void blockBullet (Bullet b) 
    {
//		Log.d("BLOCK BULLET", "we getting called");

    	if (this.vertWall() == true && b.getxCoordinate() == this.xFin && b.getyCoordinate() >= this.yStart && b.getyCoordinate() <= this.yFin)
        {
    		Log.d("BLOCK BULLET", "we getting called");
        	b.setVisible(false);           

        }

        if (this.vertWall() == false && b.getyCoordinate() == this.yFin && b.getxCoordinate() >= this.xStart && b.getxCoordinate() <= this.xFin) 
        {
        	Log.d("BLOCK BULLET", "we getting called");
        	b.setVisible(false);           
        }
    	
    	
//        if (b.getxCoordinate() <= this.xFin && b.getyCoordinate() >= this.xStart && b.getyCoordinate() >= this.yStart && b.getyCoordinate() <= this.yFin) 
//        {
//        	Log.d("BLOCK BULLET", "we getting called");
//            b.setVisible(false);
//        	//b.isVisible = false;
//        }
    }

} 
