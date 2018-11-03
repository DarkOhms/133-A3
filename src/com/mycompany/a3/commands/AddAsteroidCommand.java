package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class AddAsteroidCommand extends Command{
	private GameWorld gw;
	public AddAsteroidCommand(GameWorld gw) {
		
		super("Add Asteroid");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		gw.addNewAsteroid();
		
	}

	
}
