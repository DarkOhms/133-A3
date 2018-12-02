package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends MoveableObject implements IDrawable{
	static double[] x = new double[] { 200, 400, 800};
	static double[] y = new double[] { 200, 400, 800};


	public Asteroid(){
		
		//create semi-random location
		int xi = getRandom().nextInt(3);
		int yi = getRandom().nextInt(3);
		this.setLocationX(x[xi]);
		this.setLocationY(y[yi]);
		this.setColor(ColorUtil.GRAY);
		setSize(getRandom().nextInt(100) + 50);
		this.setDirection(this.getRandom().nextInt(359));
		this.setSpeed(this.getRandom().nextInt(9));
		
	}
	
	public void draw(Graphics g, Point pntRelToParent) {
		int xOrigin = pntRelToParent.getX();
		int yOrigin = pntRelToParent.getY();
		
		g.setColor(getColor());
		g.fillArc(xOrigin + getIntLocationX(), yOrigin + getIntLocationY(), getSize(), getSize(), 0, 360);
	}
}
