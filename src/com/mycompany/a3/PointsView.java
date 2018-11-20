package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class PointsView extends Container implements Observer {
	
	private Label scoreLabel;
	private Label score;
	private Label lives;
	private Label livesLabel;
	private Label missiles;
	private Label missilesLabel;
	private Label sound;
	private Label soundLabel;
	
	private IGameWorld gw;
	
	
	public PointsView(){
		
		//set layout
		setLayout(new BoxLayout(BoxLayout.X_AXIS));
		//create labels
		scoreLabel = new Label("SCORE: ");
		score = new Label("   ");
		livesLabel = new Label("LIVES: ");
		lives = new Label("  ");
		missilesLabel = new Label("MISSILES: ");
		missiles = new Label("  ");
		soundLabel = new Label("SOUND");
		sound = new Label(" ON ");
		//add styles
		getAllStyles().setBgColor(ColorUtil.BLACK);
		getAllStyles().setBgTransparency(255);
		
		scoreLabel.getAllStyles().setFgColor(ColorUtil.GREEN);
		scoreLabel.getAllStyles().setBgTransparency(255);
		scoreLabel.getAllStyles().setBgColor(ColorUtil.BLACK);
		
		score.getAllStyles().setFgColor(ColorUtil.GREEN);
		score.getAllStyles().setBgTransparency(255);
		score.getAllStyles().setBgColor(ColorUtil.BLACK);
		
		livesLabel.getAllStyles().setFgColor(ColorUtil.GREEN);
		livesLabel.getAllStyles().setBgTransparency(255);
		livesLabel.getAllStyles().setBgColor(ColorUtil.BLACK);
		
		lives.getAllStyles().setFgColor(ColorUtil.GREEN);
		lives.getAllStyles().setBgTransparency(255);
		lives.getAllStyles().setBgColor(ColorUtil.BLACK);
		
		missilesLabel.getAllStyles().setFgColor(ColorUtil.GREEN);
		missilesLabel.getAllStyles().setBgTransparency(255);
		missilesLabel.getAllStyles().setBgColor(ColorUtil.BLACK);
		
		missiles.getAllStyles().setFgColor(ColorUtil.GREEN);
		missiles.getAllStyles().setBgTransparency(255);
		missiles.getAllStyles().setBgColor(ColorUtil.BLACK);
		
		soundLabel.getAllStyles().setFgColor(ColorUtil.GREEN);
		sound.getAllStyles().setFgColor(ColorUtil.GREEN);
		//add labels
		add(scoreLabel);
		add(score);
		add(livesLabel);
		add(lives);
		add(missilesLabel);
		add(missiles);
		add(soundLabel);
		add(sound);
		
	}
	
	public void update(Observable o, Object arg) {
		IGameWorld gw = (IGameWorld) arg;
		lives.setText(Integer.toString(gw.getPlayerLives()));
		score.setText(Integer.toString(gw.getPlayerScore()));
		missiles.setText(Integer.toString(gw.getMissiles()));
		if(gw.getSound()) {
			sound.setText(" ON ");
		}else {
			sound.setText(" OFF ");
		}
		this.repaint();
	}
}
