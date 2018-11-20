package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	private int color;
	private double locationX;
	private double locationY;
	private Random random;
	private int size;
	private static ProxyGameWorld proxy;
	private static int worldXbound;
	private static int worldYbound;
	
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
}
