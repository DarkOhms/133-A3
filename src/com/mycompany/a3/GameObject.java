package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	private int color;
	private double locationX;
	private double locationY;
	private Random random;

	{
		random = new Random();
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

}
