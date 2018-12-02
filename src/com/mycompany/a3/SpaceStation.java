package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class SpaceStation extends FixedObject implements IDrawable{
	
	private int blinkRate;
	private boolean blink;
	
	public SpaceStation() {
		blinkRate = ((this.getRandom()).nextInt(4) + 1)*10;
		setSize(230);
		setColor(ColorUtil.YELLOW);
	}

	public int getBlinkRate() {
		return blinkRate;
	}
	
	public void blink() {
		blink = true;
	}
	
	public void draw(Graphics g, Point pntRelToParent) {
		int xOrigin = pntRelToParent.getX();
		int yOrigin = pntRelToParent.getY();
		
		if(blink) {
			g.setColor(ColorUtil.WHITE);
			blink =false;
		}else {
			g.setColor(getColor());			
		}
		g.fillArc(xOrigin + getIntLocationX(), yOrigin + getIntLocationY(), getSize(), (int)(getSize()/1.6), 0, 180);
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		//rearm the player ship
		if(otherObject instanceof PlayerShip) {
			((PlayerShip)otherObject).reArm();
		}else {
			((MoveableObject)otherObject).rebound();
			//missiles rebound too XD
		}
		
	}
}
