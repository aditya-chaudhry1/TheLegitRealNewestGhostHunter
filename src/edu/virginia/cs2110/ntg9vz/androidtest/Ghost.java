package edu.virginia.cs2110.ntg9vz.androidtest;

import android.content.Context;
import android.util.Log;

public class Ghost extends Character 
{
	protected int damage;
	protected boolean isVisible = true;
	public Ghost(int health, int startX, int startY, int startVelocity, int damage, Context context)
	{
		super(context);
		maxHealth = health;
		remainingHealth = maxHealth; 
		xCoordinate = startX;
		yCoordinate = startY;
		velocity = startVelocity;

		isEnemy = true;
		
		if(damage == 1)
			imageResource = R.drawable.level1ghostup;
		
		else if(damage == 2)
			imageResource = R.drawable.level2ghostup;
		
		else if(damage == 3)
			imageResource = R.drawable.level3ghostup;
		
		else
			imageResource = R.drawable.level4ghostup;



		
		this.damage = damage;
		Log.d("THis Ghost has a damage of", "" + this.damage);
	}
	
	public void changeDirection(int newDirection)
	{
		currentDirection = newDirection;
		
		if (currentDirection == 0)
		{
			//imageResource = R.drawable.level1ghostup;
			if(damage == 5)
				imageResource = R.drawable.level1ghostup;
			
			else if(damage == 15)
				imageResource = R.drawable.level2ghostup;
			
			else if(damage == 25)
				imageResource = R.drawable.level3ghostup;
			
			else
				imageResource = R.drawable.level4ghostup;
		}
		
		else if (currentDirection == 1)
		{
			//imageResource = R.drawable.level1ghostleft;
			if(damage == 5)
				imageResource = R.drawable.level1ghostleft;
			
			else if(damage == 15)
				imageResource = R.drawable.level2ghostleft;
			
			else if(damage == 25)
				imageResource = R.drawable.level3ghostleft;
			
			else
				imageResource = R.drawable.level4ghostleft;
		}
		
		else if (currentDirection == 2)
		{
			//imageResource = R.drawable.level1ghostdown;
			if(damage == 5)
				imageResource = R.drawable.level1ghostdown;
			
			else if(damage == 15)
				imageResource = R.drawable.level2ghostdown;
			
			else if(damage == 25)
				imageResource = R.drawable.level3ghostdown;
			
			else
				imageResource = R.drawable.level4ghostdown;
		}
		
		else
		{
			//imageResource = R.drawable.level1ghostright;
			if(damage == 5)
				imageResource = R.drawable.level1ghostright;
			
			else if(damage == 15)
				imageResource = R.drawable.level2ghostright;
			
			else if(damage == 25)
				imageResource = R.drawable.level3ghostright;
			
			else
				imageResource = R.drawable.level4ghostright;
		}
		
	}
	
	public int getDamage()
	{
		return this.damage;
	}
	
	public void setDamage(int newDamage)
	{
		 this.damage = newDamage;
	}
	
	public int getHealth()
	{
		return this.remainingHealth;
	}
	
	public boolean isVisible()
	{
		return isVisible;
	}
	
	public void setIsVisible(boolean isItVisibleNow)
	{
		this.isVisible = isItVisibleNow;
	}
}
