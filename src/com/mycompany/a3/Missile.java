package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Missile extends MoveableObject implements IDrawable {
	private int fuelLevel;
	private boolean playerMissile;
	private int MAX = 100;
	
	
	public Missile(int direction, int speed, double locationX, double locationY ) {
		
		this.setSize(10);
		fuelLevel = MAX;
		this.setDirection(direction);
		this.setSpeed(speed + 3);
		this.setLocationX(locationX);
		this.setLocationY(locationY);
		this.setColor(ColorUtil.rgb(255,0,0));
		this.playerMissile = false;  //default
	
	}

	public boolean isPlayerMissile() {
		return playerMissile;
	}

	public void setPlayerMissile(boolean playerMissile) {
		this.playerMissile = playerMissile;
	}

	public int getFuelLevel() {
		return fuelLevel;
	}
	
	public void maxFuelLevel() {
		fuelLevel = MAX;
	}
	
	public void burnFuel() {
		fuelLevel--;
	}

	
	public void draw(Graphics g, Point pntRelToParent) {
		//get map origin
		int mapOriginX = (int) pntRelToParent.getX();
		int mapOriginY = (int) pntRelToParent.getY();
		//get location on map
		int locationX = mapOriginX + (int)this.getIntLocationX() + getSize()/2;
		int locationY = mapOriginY + (int)this.getIntLocationY() + getSize()/2;
		
		
		//initial triangle points
		
		int[] xPoints = {-getSize(), 0, getSize()};
		int[] yPoints = {getSize(), -getSize(), getSize()};
		
		//add ship location and angle
		for(int i =0; i < xPoints.length; i++) {
			//ship attitude
			double theta = Math.toRadians(this.getDirection());
			int x = xPoints[i];
			int y = yPoints[i];
			xPoints[i] = (int)(x*Math.cos(theta) - y*Math.sin(theta));
			yPoints[i] = (int)(x*Math.sin(theta) + y*Math.cos(theta));
			//ship location
			xPoints[i] += locationX;
			yPoints[i] += locationY;
		}
		
		g.setColor(this.getColor());
		g.fillPolygon(xPoints, yPoints, 3); 
	}	
	
}
