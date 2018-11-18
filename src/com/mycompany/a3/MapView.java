package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class MapView extends Container implements Observer {
	
	private Iterator<GameObject> gwIterator;
	
	public MapView(){
		getAllStyles().setBgColor(ColorUtil.BLACK);
		getAllStyles().setBgTransparency(255);

	}
	public void update(Observable o, Object arg) {
		//get an iterator to draw the game map
		ProxyGameWorld proxy = new ProxyGameWorld((GameWorld)o);
		gwIterator = (proxy).getIterator(); 
		repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);

		Point pntRelToParent = new Point(getX(),getY());
		
		while(gwIterator.hasNext()) {
			GameObject obj = gwIterator.next();
			if(obj instanceof IDrawable)
			((IDrawable)obj).draw(g, pntRelToParent);
		}
	}
}

