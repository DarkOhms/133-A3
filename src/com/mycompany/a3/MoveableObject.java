package com.mycompany.a3;
import java.lang.Math;


public class MoveableObject extends GameObject implements IMoveable {
	private int speed;
	private int direction;
	
	
	public void move() {
		this.setLocationX(this.getLocationX() + Math.cos((direction/180.0)*Math.PI)*speed);
		this.setLocationY(this.getLocationY() + Math.sin((direction/180.0)*Math.PI)*speed);
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

	@Override
	public String toString() {
		String sString = super.toString();
		return sString + "MoveableObject [speed=" + speed + ", direction=" + direction + "]";
	}
}
