package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MissileLauncher extends MoveableObject implements ISteerable, IDrawable {
	
	private Ship belongsTo;
	
	public MissileLauncher(Ship parent ) {
		//pass a starting location to the launcher from ship
		this.belongsTo = parent;
		this.setLocationX(belongsTo.getLocationX());
		this.setLocationX(belongsTo.getLocationX());
		this.setSize((int)(belongsTo.getSize()*0.6));
		this.setColor(ColorUtil.rgb(255,0,0));
		
	}
	
	public void steer(int directionChange) {
		this.setDirection(this.getDirection() + directionChange);
	}
	
	//generate a new Missile and return it for storage in the GameWorld
	public Missile fireMissile() {
		Missile missile = new Missile(this.getDirection(), this.getSpeed(), this.getLocationX(), this.getLocationY());
		belongsTo.fireMissile();
		if(belongsTo instanceof PlayerShip)
			missile.setPlayerMissile(true);
			
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
	
	//overrides
	public double getLocationX() {
		return belongsTo.getLocationX();
	}
	
	public double getLocationY() {
		return belongsTo.getLocationY();
	}
	
	public int getSpeed() {
		return belongsTo.getSpeed();
	}
	
	public void draw(Graphics g, Point pntRelToParent) {
		int xOrigin = pntRelToParent.getX();
		int yOrigin = pntRelToParent.getY();
		
		g.setColor(getColor());
		g.fillArc(xOrigin + getIntLocationX(), yOrigin + getIntLocationY(), getSize(), getSize(), 0, 360);
	}

}
