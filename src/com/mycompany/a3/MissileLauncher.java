package com.mycompany.a3;

public class MissileLauncher extends MoveableObject implements ISteerable {
	
	private Ship belongsTo;
	
	public MissileLauncher(Ship parent ) {
		//pass a starting location to the launcher from ship
		this.belongsTo = parent;
		this.setLocationX(belongsTo.getLocationX());
		this.setLocationX(belongsTo.getLocationX());
	}
	
	public void steer(int directionChange) {
		this.setDirection(this.getDirection() + directionChange);
	}
	
	//generate a new Missile and return it for storage in the GameWorld
	public Missile fireMissile() {
		Missile missile = new Missile(this.getDirection(), this.getSpeed(), this.getLocationX(), this.getLocationY());
		belongsTo.fireMissile();
		return missile;
	}
	
	@Override
	public String toString() {
		String sString = super.toString(); 
		return sString +  "MissileLauncher [belongsTo=" + belongsTo + "]";
	}

	//keeps the launcher attached to the ship it belongs to
	public void move() {
		this.setLocationX(belongsTo.getLocationX());
		this.setLocationX(belongsTo.getLocationX());
	}

}
