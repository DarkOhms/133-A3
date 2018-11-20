package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command {
	private GameWorld gw;
	boolean paused;
	public SoundCommand(GameWorld gw) {
		
		super("Sound");
		this.gw = gw;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("SoundCommand invoked.");
		//toggle sound
		gw.setSound(!gw.getSound());
		
	}
}

