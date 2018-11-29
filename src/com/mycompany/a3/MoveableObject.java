package com.mycompany.a3;
import java.lang.Math;


public class MoveableObject extends GameObject implements IMoveable {
	private int speed;
	private int direction;
	
	public MoveableObject() {
		direction = 0;
	}
	
	
	public void move() {
		if((this.getIntLocationX() + this.getSize() > this.getWorldXbound()) || (this.getIntLocationX() + this.getSize() < 0))
			rebound();
		if((this.getIntLocationY() + this.getSize() > this.getWorldYbound()) || (this.getIntLocationY() + this.getSize() < 0))
			rebound();
		
		this.setLocationX(this.getLocationX() + Math.sin((direction/180.0)*Math.PI)*speed);
		this.setLocationY(this.getLocationY() - Math.cos((direction/180.0)*Math.PI)*speed);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
	
		this.direction = direction%360;
	}
	
	public void rebound() {
		this.setDirection(this.getDirection() + 180);
		//bad physics, update this later
	}

	@Override
	public String toString() {
		String sString = super.toString();
		return sString + "MoveableObject [speed=" + speed + ", direction=" + direction + "]";
	}
}
