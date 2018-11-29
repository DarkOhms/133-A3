package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements ICollider {

	private int color;
	private double locationX;
	private double locationY;
	private Random random;
	private int size;
	private static ProxyGameWorld proxy;
	private static int worldXbound;
	private static int worldYbound;
	private boolean destroy; //marks objects for removal from the game world
	
	//constructor takes a ProxyGameWorld so it can update worldX/Ybound
	
	public static void setGameWorld(ProxyGameWorld myProxy) {
		proxy = myProxy;
	}
	
	
	{
		random = new Random();
	}
	
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "GameObject [color=" + color + ", locationX=" + Math.round(locationX*10)/10.0 + ", locationY=" + Math.round(locationY*10)/10.0 + "]";
	}

	public Random getRandom() {
		return random;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public double getLocationX() {
		return locationX;
	}
	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}
	public double getLocationY() {
		return locationY;
	}
	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}
	public int getIntLocationY() {
		return (int)locationY;
	}
	public int getIntLocationX() {
		return (int)locationX;
	}

	public int getWorldXbound() {
		//update before returning
		worldXbound = proxy.getXBOUND();
		return worldXbound;
	}
	
	public int getWorldYbound() {
		worldYbound = proxy.getYBOUND();
		return worldYbound;
	}
	
	public boolean isDestroy() {
		return destroy;
	}

	public void setDestroy(boolean destroy) {
		this.destroy = destroy;
	}

	@Override
	public boolean collidesWith(ICollider otherObject) {
		//get bounds for this object
		int leftBound = this.getIntLocationX() - this.getSize()/2;
		int rightBound = this.getIntLocationX() + this.getSize()/2;
		int topBound = this.getIntLocationY() - this.getSize()/2;
		int bottomBound = this.getIntLocationY() + this.getSize()/2;
		//get bounds for other object
		int leftBound2 = ((GameObject)otherObject).getIntLocationX() - ((GameObject)otherObject).getSize()/2;
		int rightBound2 = ((GameObject)otherObject).getIntLocationX() + ((GameObject)otherObject).getSize()/2;
		int topBound2 = ((GameObject)otherObject).getIntLocationY() - ((GameObject)otherObject).getSize()/2;
		int bottomBound2 = ((GameObject)otherObject).getIntLocationY() + ((GameObject)otherObject).getSize()/2;
		
		if(rightBound < leftBound2 || leftBound > rightBound2)
			return false;
		if(topBound2 < bottomBound || topBound > bottomBound2) {
			return false;
		}else {
			return true;
		}
	}
	
	public void handleCollision(ICollider otherObject) {
		//different collisions
		 if(this instanceof Asteroid && otherObject instanceof Asteroid) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }
		 if(this instanceof Asteroid && otherObject instanceof NonPlayerShip || this instanceof Asteroid && otherObject instanceof NonPlayerShip ) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }
		 if(this instanceof PlayerShip && otherObject instanceof NonPlayerShip) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }
		 if(this instanceof PlayerShip && otherObject instanceof Asteroid) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }
		 if(this instanceof PlayerShip && otherObject instanceof Missile && !((Missile)otherObject).isPlayerMissile()) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }else if(this instanceof Missile && otherObject instanceof PlayerShip) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }
		 if(this instanceof PlayerShip && otherObject instanceof Asteroid) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }else if(this instanceof Asteroid && otherObject instanceof PlayerShip) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }
		 if(this instanceof Missile && otherObject instanceof NonPlayerShip) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }
		 if(this instanceof Missile && otherObject instanceof Asteroid) {
			 this.setDestroy(true);
			 ((GameObject)otherObject).setDestroy(true);
		 }
	}
}
