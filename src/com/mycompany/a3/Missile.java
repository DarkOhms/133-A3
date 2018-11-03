package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public class Missile extends MoveableObject {
	private int fuelLevel;
	
	
	public Missile(int direction, int speed, double locationX, double locationY ) {
		fuelLevel = 10;
		this.setDirection(direction);
		this.setSpeed(speed + 2);
		this.setLocationX(locationX);
		this.setLocationY(locationY);
		this.setColor(ColorUtil.rgb(255, 0, 0));
	
	}

	public int getFuelLevel() {
		return fuelLevel;
	}
	
	public void burnFuel() {
		fuelLevel--;
	}

	@Override
	public String toString() {
		return super.toString() + "Missile [fuelLevel=" + fuelLevel + "]";
	}
	
}
