package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends MoveableObject implements IDrawable{



	public Asteroid(){
		
		this.setColor(ColorUtil.GRAY);
		setSize(getRandom().nextInt(150) + 100);
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
