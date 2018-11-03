package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;

public class PlayerShip extends Ship implements ISteerable {
	
	public PlayerShip(){
		//start PlayerShip in the center
		super(512,384);
		this.setMissileCount(10);
		this.setColor(ColorUtil.GREEN);
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
	
}
