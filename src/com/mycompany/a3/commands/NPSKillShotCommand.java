package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class NPSKillShotCommand extends Command{
	private GameWorld gw;
	public NPSKillShotCommand(GameWorld gw) {
		
		super("NPS Kill Shot");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		if(!gw.isPaused())
			gw.npsKillShot();
		
	}

	
}
