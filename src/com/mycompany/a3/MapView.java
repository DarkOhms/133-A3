package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MapView extends Container implements Observer {
	
	private Iterator<GameObject> gwIterator;
	private ProxyGameWorld proxy;
	
	public MapView(){
		getAllStyles().setBgColor(ColorUtil.BLACK);
		getAllStyles().setBgTransparency(255);

	}
	public void update(Observable o, Object arg) {
		//get an iterator to draw the game map
		proxy = new ProxyGameWorld((GameWorld)o);
		gwIterator = proxy.getIterator(); 
		repaint();
	}
	
	public void pointerPressed(int x, int y) {
		
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPntRelToParent = new Point(x,y);
		Point pCmpRetToParent = new Point(getX(), getY());
		
		//draw boxes around selected objects
		if(gwIterator != null) {
			while(gwIterator.hasNext()) {
				GameObject obj = gwIterator.next();
				if(obj instanceof ISelectable)
					if(((ISelectable)obj).contains(pPntRelToParent, pCmpRetToParent ))
						obj.setSelected(true);
					else
						obj.setSelected(false);
			}
		}
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);

		Point pntRelToParent = new Point(getX(),getY());
		//selectability
		
		
		
		if(null != proxy && proxy.isPaused()) {
			if(gwIterator != null) {
				while(gwIterator.hasNext()) {
					//call selectable draw
					GameObject obj = gwIterator.next();
					((GameObject)obj).draw(g, pntRelToParent);
					}
				}
			//display pause screen
			//char[] message = new char[] {'P','A','U','S','E','D'};
			//g.setColor(ColorUtil.CYAN);
			//Font stringFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
			//g.setFont(stringFont);
			//g.drawChars(message, 0, 6, proxy.getXBOUND()/2, proxy.getYBOUND()/2);
		}else if(null != proxy && proxy.isGameOver()) {
			if(Dialog.show("Game Over | Final Score: " + proxy.getPlayerScore(), "Try again?", "Yes", "No"))
				proxy.init();
					
		}else {
		//null check
			if(gwIterator != null) {
				while(gwIterator.hasNext()) {
					GameObject obj = gwIterator.next();
					if(obj instanceof IDrawable)
						((IDrawable)obj).draw(g, pntRelToParent);
					}
				}
		}
	}
}

