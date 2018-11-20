package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerShip extends Ship implements ISteerable, IDrawable {
	
	//singleton
	private static PlayerShip theShip;
	
	
	private PlayerShip(){
		//start PlayerShip in the center
		this.setLocationX(getWorldXbound()/2);
		this.setLocationY(getWorldYbound()/2);
		this.setMissileCount(10);
		this.setColor(ColorUtil.BLUE);
		this.setSize(120);
	}
	
	//singleton
	public static PlayerShip getShip() {
		if(theShip == null) 
			theShip = new PlayerShip();
			
		return theShip;
		
	}
	
	public void hyperspace() {
		this.setLocationX(getWorldXbound()/2);
		this.setLocationY(getWorldYbound()/2);
	}
	
	public void reArm() {
		this.setMissileCount(10);
	}
	
	public void steer(int directionChange) {
		this.setDirection(this.getDirection() + directionChange);
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
