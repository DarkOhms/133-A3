package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddNonPlayerShipCommand extends Command{
	private GameWorld gw;
	public AddNonPlayerShipCommand(GameWorld gw) {
		
		super("Add Non Player Ship");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		gw.addNonPlayerShip();
		
	}

	
}
