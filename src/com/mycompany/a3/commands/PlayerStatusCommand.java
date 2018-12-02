package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PlayerStatusCommand extends Command{
	private GameWorld gw;
	public PlayerStatusCommand(GameWorld gw) {
		
		super("Player Status");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		if(!gw.isPaused())
			gw.playerStatus();
		
	}

	
}
