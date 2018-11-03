package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class SaveCommand extends Command {
	private Game g;
	public SaveCommand(Game g) {
		
		super("Save");
		this.g = g;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("SaveCommand invoked.");
		
	}
}
