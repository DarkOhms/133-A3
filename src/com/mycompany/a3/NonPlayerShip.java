package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public class NonPlayerShip extends Ship {
	private int size;
	
	public NonPlayerShip(double locationX, double locationY){
		super(locationX, locationY);
		
		if(this.getRandom().nextInt(3) < 2){
			size = 10;//small
		}else {
			size = 20;//large
		}
		this.setDirection(this.getRandom().nextInt(359));
		this.setDirection(this.getRandom().nextInt(4) + 1);
		this.setColor(ColorUtil.GREEN);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
