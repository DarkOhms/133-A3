package com.mycompany.a3;

public abstract class Ship extends MoveableObject {
	private MissileLauncher launcher;
	private int missileCount;
	
	public Ship() {
		//synch ship start location to launcher start location
		launcher = new MissileLauncher(this);
	}
	public MissileLauncher getLauncher() {
		return launcher;
	}
	public void setLauncher(MissileLauncher launcher) {
		this.launcher = launcher;
	}
	//missile count restraint will need to be handled in GameWorld
	public void fireMissile() {
		
		missileCount--;
	}
	
	public int getMissileCount() {
		return missileCount;
	}
	
	public void setMissileCount(int count) {
		missileCount = count ;
	}
	
	public MissileLauncher getMissileLauncher() {
		return launcher;
	}
	
	public void move() {
		super.move();
		//moves missile launcher with the ship
		launcher.setLocationX(this.getLocationX());
		launcher.setLocationY(this.getLocationY());
	}
	@Override
	public String toString() {
		String sString= super.toString(); 
		return sString + "Ship [launcher=" + launcher + ", missileCount=" + missileCount + "]";
	}
}
