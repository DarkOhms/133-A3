package com.mycompany.a3.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;

public class AboutCommand extends Command {
	private Game g;
	public AboutCommand(Game g) {
		
		super("About");
		this.g = g;
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent e) {
		System.out.println("This is a big school project for CSC 133 by Luke Martin.");
		
	}
}
