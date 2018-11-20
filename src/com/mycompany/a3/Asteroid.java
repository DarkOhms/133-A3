package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Asteroid extends MoveableObject implements IDrawable{



	public Asteroid(){
		
		this.setColor(ColorUtil.GRAY);
		setSize(getRandom().nextInt(24) + 6);
		this.setDirection(this.getRandom().nextInt(359));
		
	}
	
	public void draw(Graphics g, Point pntRelToParent) {
		
	}
}
