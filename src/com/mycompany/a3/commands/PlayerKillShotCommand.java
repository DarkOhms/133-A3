package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PlayerKillShotCommand extends Command{
	private GameWorld gw;
	public PlayerKillShotCommand(GameWorld gw) {
		
		super("Player Kill Shot");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		gw.playerKillShot();
		
	}

	
}
