package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

public class NewCommand extends Command {
	private Game g;
	public NewCommand(Game g) {
		
		super("New");
		this.g = g;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("NewCommand invoked.");
		
	}
}
