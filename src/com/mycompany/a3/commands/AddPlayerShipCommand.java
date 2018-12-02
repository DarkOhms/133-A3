package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddPlayerShipCommand extends Command{
	private GameWorld gw;
	public AddPlayerShipCommand(GameWorld gw) {
		
		super("Add Player Ship");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		if(!gw.isPaused())
			gw.addPlayerShip();
		
	}

	
}
