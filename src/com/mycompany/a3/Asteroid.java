package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public class Asteroid extends MoveableObject {

	private int size;
	

	public int getSize() {
		return size;
	}


	public Asteroid(){
		
		this.setColor(ColorUtil.GRAY);
		size = this.getRandom().nextInt(24) + 6;
		this.setDirection(this.getRandom().nextInt(359));
	}
}
