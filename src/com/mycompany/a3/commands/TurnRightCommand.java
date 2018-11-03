package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TurnRightCommand extends Command{
	private GameWorld gw;
	public TurnRightCommand(GameWorld gw) {
		
		super("Turn Right");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		gw.turnRight();
		
	}

	
}
