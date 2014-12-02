package edu.virginia.cs2110.ntg9vz.androidtest;

public abstract class Weapon 
{
	
	protected int damage;
	protected int xCoordinate;
	protected int yCoordinate;
	
	//Do image somehow
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

	public abstract void attack();
	
	public abstract void animateAttack();
	
	public int getxCoordinate() {
		return xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	
	public int getDamage()
	{
		return damage;
	}
	
	
}
