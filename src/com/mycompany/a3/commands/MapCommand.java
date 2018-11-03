package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class MapCommand extends Command{
	private GameWorld gw;
	public MapCommand(GameWorld gw) {
		
		super("Map");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		gw.map();
		
	}

	
}
