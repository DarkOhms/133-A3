package com.mycompany.a3;

public class SpaceStation extends FixedObject{
	
	private int blinkRate;
	
	public SpaceStation() {
		blinkRate = (this.getRandom()).nextInt(4);
	}

	public int getBlinkRate() {
		return blinkRate;
	}

}
