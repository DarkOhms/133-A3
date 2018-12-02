package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.a3.commands.AddAsteroidCommand;
import com.mycompany.a3.commands.AddNonPlayerShipCommand;
import com.mycompany.a3.commands.AddPlayerShipCommand;
import com.mycompany.a3.commands.AddSpaceStationCommand;
import com.mycompany.a3.commands.AsteroidCollisionCommand;
import com.mycompany.a3.commands.AsteroidKillShotCommand;
import com.mycompany.a3.commands.AsteroidNPSCollisionCommand;
import com.mycompany.a3.commands.DecreaseSpeedCommand;
import com.mycompany.a3.commands.FireNonPlayerMissileCommand;
import com.mycompany.a3.commands.FirePlayerMissileCommand;
import com.mycompany.a3.commands.HyperspaceCommand;
import com.mycompany.a3.commands.IncreaseSpeedCommand;
import com.mycompany.a3.commands.NPSKillShotCommand;
import com.mycompany.a3.commands.PlayerCrashCommand;
import com.mycompany.a3.commands.PlayerKillShotCommand;
import com.mycompany.a3.commands.PlayerNPSCrashCommand;
import com.mycompany.a3.commands.RefuelCommand;
import com.mycompany.a3.commands.RestockMissilesCommand;
import com.mycompany.a3.commands.RotateLauncherCommand;
import com.mycompany.a3.commands.TickCommand;
import com.mycompany.a3.commands.TurnLeftCommand;
import com.mycompany.a3.commands.TurnRightCommand;

public class CommandPanel extends Form {
	private GameWorld gw;
	public CommandPanel(GameWorld gw) {
		
		this.setTitle("Commands");
		this.gw = gw;
		
		setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		//Create buttons
		Button addAsteroid = new Button("Add Asteroid");
		addAsteroid.getUnselectedStyle().setBgTransparency(255);
		addAsteroid.setCommand(new AddAsteroidCommand(gw));
		add(addAsteroid);
		
		Button addPlayerShip = new Button("Add Player Ship");
		addPlayerShip.getUnselectedStyle().setBgTransparency(255);
		addPlayerShip.setCommand(new AddPlayerShipCommand(gw));
		add(addPlayerShip);
		
		Button addNonPlayerShip = new Button("Add Non Player Ship");
		addNonPlayerShip.getUnselectedStyle().setBgTransparency(255);
		addNonPlayerShip.setCommand(new AddNonPlayerShipCommand(gw));
		add(addNonPlayerShip);
		
		Button addSpaceStation = new Button("Add Space Station");
		addSpaceStation.getUnselectedStyle().setBgTransparency(255);
		addSpaceStation.setCommand(new AddSpaceStationCommand(gw));
		add(addSpaceStation);
		
		Button increaseSpeed = new Button("Increase Speed");
		increaseSpeed.getUnselectedStyle().setBgTransparency(255);
		increaseSpeed.setCommand(new IncreaseSpeedCommand(gw));
		add(increaseSpeed);
		
		Button decreaseSpeed = new Button("Decrease Speed");
		decreaseSpeed.getUnselectedStyle().setBgTransparency(255);
		decreaseSpeed.setCommand(new DecreaseSpeedCommand(gw));
		add(decreaseSpeed);
		
		Button turnLeft = new Button("Turn Left");
		turnLeft.getUnselectedStyle().setBgTransparency(255);
		turnLeft.setCommand(new TurnLeftCommand(gw));
		add(turnLeft);
		
		Button turnRight = new Button("Turn Right");
		turnRight.getUnselectedStyle().setBgTransparency(255);
		turnRight.setCommand(new TurnRightCommand(gw));
		add(turnRight);
		
		Button rotateLauncher = new Button("Rotate Launcher");
		rotateLauncher.getUnselectedStyle().setBgTransparency(255);
		rotateLauncher.setCommand(new RotateLauncherCommand(gw));
		add(rotateLauncher);
		
		Button firePlayerMissile = new Button("Fire Player Missile");
		firePlayerMissile.getUnselectedStyle().setBgTransparency(255);
		firePlayerMissile.setCommand(new FirePlayerMissileCommand(gw));
		add(firePlayerMissile);
		
		Button fireNonPlayerMissile = new Button("Fire non Player Missile");
		fireNonPlayerMissile.getUnselectedStyle().setBgTransparency(255);
		fireNonPlayerMissile.setCommand(new FireNonPlayerMissileCommand(gw));
		add(fireNonPlayerMissile);
		
		Button hyperspace = new Button("Hyperspace");
		hyperspace.getUnselectedStyle().setBgTransparency(255);
		hyperspace.setCommand(new HyperspaceCommand(gw));
		add(hyperspace);
		
		Button restockMissiles = new Button("Restock Missiles");
		restockMissiles.getUnselectedStyle().setBgTransparency(255);
		restockMissiles.setCommand(new RestockMissilesCommand(gw));
		add(restockMissiles);
		
		/* outdated commands
		Button asteroidKillShot = new Button("Missile vs. Asteroid");
		asteroidKillShot.getUnselectedStyle().setBgTransparency(255);
		asteroidKillShot.setCommand(new AsteroidKillShotCommand(gw));
		add(asteroidKillShot);
		
		Button  npsKillShot = new Button("Missile vs. NPS");
		npsKillShot.getUnselectedStyle().setBgTransparency(255);
		npsKillShot.setCommand(new NPSKillShotCommand(gw));
		add(npsKillShot);
		
		Button playerKillShot = new Button("Missile vs. Player");
		playerKillShot.getUnselectedStyle().setBgTransparency(255);
		playerKillShot.setCommand(new PlayerKillShotCommand(gw));
		add(playerKillShot);
		
		Button playerCrash = new Button("Player vs. Asteroid");
		playerCrash.getUnselectedStyle().setBgTransparency(255);
		playerCrash.setCommand(new PlayerCrashCommand(gw));
		add(playerCrash);
		
		Button  playerNPSCrash = new Button("Player vs. NPS");
		playerNPSCrash.getUnselectedStyle().setBgTransparency(255);
		playerNPSCrash.setCommand(new PlayerNPSCrashCommand(gw));
		add(playerNPSCrash);
		
		Button asteroidNPSCollision = new Button("Asteroid vs. NPS");
		asteroidNPSCollision.getUnselectedStyle().setBgTransparency(255);
		asteroidNPSCollision.setCommand(new AsteroidNPSCollisionCommand(gw));
		add(asteroidNPSCollision);
		
		Button asteroidCollision = new Button("Asteroid vs. Asteroid");
		asteroidCollision.getUnselectedStyle().setBgTransparency(255);
		asteroidCollision.setCommand(new AsteroidCollisionCommand(gw));
		add(asteroidCollision);
		
		*/
		Button refuel = new Button("Refuel");
		refuel.getUnselectedStyle().setBgTransparency(255);
		refuel.setCommand(new RefuelCommand(gw));
		add(refuel);
		
		Button tick = new Button("Tick");
		tick.getUnselectedStyle().setBgTransparency(255);
		tick.setCommand(new TickCommand(gw));
		add(tick);
		
		
	}
}
