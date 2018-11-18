package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class PlayerShip extends Ship implements ISteerable, IDrawable {
	
	//singleton
	private static PlayerShip theShip;
	
	private PlayerShip(){
		//start PlayerShip in the center
		super(512,384);
		this.setMissileCount(10);
		this.setColor(ColorUtil.GREEN);
	}
	
	//singleton
	public static PlayerShip getShip() {
		if(theShip == null) 
			theShip = new PlayerShip();
			
		return theShip;
		
	}
	
	public void hyperspace() {
		this.setLocationX(512);
		this.setLocationY(384);
	}
	
	public void reArm() {
		this.setMissileCount(10);
	}
	
	public void steer(int directionChange) {
		this.setDirection(this.getDirection() + directionChange);
	}
	
	public void draw(Graphics g, Point pntRelToParent) {
		int mapOriginX = (int) pntRelToParent.getX();
		int mapOriginY = (int) pntRelToParent.getY();
		//make triangle
		int x1 = mapOriginX + (int)this.getIntLocationX() -20;
		int x2 = mapOriginX + (int)this.getIntLocationX();
		int x3 = mapOriginX + (int)this.getIntLocationX()+20;
		
		int y1 = mapOriginY + (int)this.getIntLocationY() -20;
		int y2 = mapOriginY + (int)this.getIntLocationY();
		int y3 = mapOriginY + (int)this.getIntLocationY()+20;
				
		int[] xPoints = new int[] {mapOriginX + (int)this.getIntLocationX() -20, mapOriginX +(int)this.getIntLocationX(), mapOriginX +(int)this.getIntLocationX()+20};
		int[] yPoints = new int[] {mapOriginY + (int)this.getIntLocationY() -20, mapOriginY + (int)this.getIntLocationY(), mapOriginY + (int)this.getIntLocationY()+20};
		
		
		
		g.setColor(this.getColor());
		g.fillTriangle(x1, y1, x2, y2, x3, y3);
		g.setAlpha(255);
		//g.fillPolygon(xPoints, yPoints, 3); 
		
		int currX = (int) this.getIntLocationX();
		int currY = (int) this.getIntLocationY();
		int centerX = currX + 40/2; // Simulate getSize / 2
		int centerY = currY + 40/2;
		g.setColor(this.getColor());
		// CIRCLE
		g.drawArc((int)this.getIntLocationX() + mapOriginX, (int)this.getIntLocationY()+ mapOriginY, centerX, centerY, 0, 360);
		
		
	}
	
}
