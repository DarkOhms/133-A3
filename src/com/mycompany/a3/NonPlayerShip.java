package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class NonPlayerShip extends Ship implements IDrawable {
	private int size;
	
	public NonPlayerShip(){
		
		if(this.getRandom().nextInt(3) < 2){
			size = 35;//small
		}else {
			size = 75;//large
		}
		this.setDirection(this.getRandom().nextInt(359));
		this.setDirection(this.getRandom().nextInt(4) + 1);
		this.setColor(ColorUtil.GREEN);
		this.setLauncher(new MissileLauncher(this));
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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
