package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

import com.codename1.ui.geom.Point;

public class GameWorld extends Observable {
	private int XBOUND;
	private int YBOUND;
	private Point pntRelToParent;

	private int playerScore;
	private int playerLives;
	private int elapsedGameTime;
	
	private Random random;
	//private Vector<GameObject> store = new Vector<GameObject>();
	private BGSound introMusic;
	private Sound collision;
	private Sound explosion;
	private boolean sound;
	private boolean paused;
	private boolean gameOver;
	
	private GameObjectsCollection store;
	
	{
		store = new GameObjectsCollection();
		random = new Random();
	}
		
	//methods for generating and checking random locations for new objects
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private double randomXLocation() {
		int inBounds = XBOUND - 50;
		return (random.nextInt(inBounds)) + 25;
		
	}
	
	private double newXLocation() {
		double possibleXLocation = randomXLocation();
		
		for(int i =0; i < store.size(); i++) {
			if(possibleXLocation - store.elementAt(i).getLocationX() <= 20.0)
				possibleXLocation = newXLocation();
		}
		
		return possibleXLocation;
	}
	
	private double newYLocation() {
		double possibleYLocation = randomYLocation();
		
		for(int i =0; i < store.size(); i++) {
			if(possibleYLocation - store.elementAt(i).getLocationY() <= 20.0)
				possibleYLocation = newYLocation();
		}
		
		return possibleYLocation;
	}
	
	private double randomYLocation() {
		int inBounds = YBOUND - 50;
		return random.nextInt(inBounds) + 25;
		
	}
	
	private void setRandomLocation(GameObject obj) {
		obj.setLocationX(newXLocation());
		obj.setLocationY(newYLocation());
	}
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public void addNewAsteroid() {
		//create a new asteroid object
		Asteroid asteroid = new Asteroid();
		//set random location
		//setRandomLocation(asteroid);
		//add asteroid to game objects
		store.add(asteroid);
		//print to console
		System.out.println("A new ASTEROID has been created");
		update();
	}
	
	public void addPlayerShip() {
		
		//add playerShip to game objects with special position
		store.add(0,PlayerShip.getShip());
		//print to console
		System.out.println("A new PlayerShip has been created");
		update();
	}
	
	public void addNonPlayerShip() {
		//create a new non player ship object, must have new location parameters
		NonPlayerShip nonPlayerShip = new NonPlayerShip();
		//add playerShip to game objects
		store.add(nonPlayerShip);
		//print to console
		System.out.println("A new NonPlayerShip has been created");
		update();
	}
	
	public void addSpaceStation() {
		//create a new SpaceStation object
		SpaceStation spaceStation = new SpaceStation();
		//set random location
		spaceStation.setLocationX(750);
		spaceStation.setLocationY(260);
		//add spaceStation to game objects
		store.add(spaceStation);
		//print to console
		System.out.println("A new Space Station has been created");
		
	}
	
	//increases player speed if player speed is less than 10
	public void increaseSpeed() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof PlayerShip && ((PlayerShip)store.elementAt(0)).getSpeed() < 10 ) {
			PlayerShip mObj = (PlayerShip)store.elementAt(0);
			mObj.setSpeed(mObj.getSpeed()+1);
		}else {
			System.out.println("Speed is at Maximum");
		}
	}
	
	public void decreaseSpeed() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof MoveableObject) {
			MoveableObject mObj = (MoveableObject)store.elementAt(0);
			if(mObj.getSpeed() > 0) {
				mObj.setSpeed(mObj.getSpeed()-1);
			}else {
				System.out.println("Speed is already zero.");
			}
		}
	}
	
	public void turnLeft() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof PlayerShip) {
			PlayerShip mObj = (PlayerShip)store.elementAt(0);
			mObj.steer(-5);
		}
		
	}
	
	public void turnRight() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof PlayerShip) {
			PlayerShip mObj = (PlayerShip)store.elementAt(0);
			mObj.steer(5);
		}
	}
	
	public void rotateLauncher() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof PlayerShip) {
			MissileLauncher mObj = ((PlayerShip) store.elementAt(0)).getMissileLauncher();
			mObj.steer(-5);
		}
		
	}
	
	public void firePlayerMissile() {
		//we know element 0 is the player ship because on init()
		//firing a missile adds a Missile object to the GameObjects vector
		if(store.elementAt(0) instanceof PlayerShip) {
			if(((PlayerShip) store.elementAt(0)).getMissileCount() > 0) {
				MissileLauncher mObj = ((PlayerShip) store.elementAt(0)).getMissileLauncher();
				store.add(mObj.fireMissile());
				System.out.println("Missile Fired");	
			}else {
				System.out.println("Out of Missiles!");
			}
		}
		
	}
	
	public void fireNonPlayerMissile() {
		//find instanceof NPS then fire
		//firing a missile adds a Missile object to the GameObjects vector
		for(int i = 1; i < store.size(); i++) {
			if(store.elementAt(i) instanceof NonPlayerShip) {
				MissileLauncher mObj = ((NonPlayerShip) store.elementAt(i)).getMissileLauncher();
				store.add(mObj.fireMissile());
			}else {
				System.out.println("No NPS to fire.");
			}
		}
		
	}
	
	public void hyperspace() {
		//we know element 0 is the player ship because on init()
		//can we crash as a result of hyperspace?
		if(store.elementAt(0) instanceof PlayerShip) {
			((PlayerShip) store.elementAt(0)).hyperspace();
			
		}	
		
	}
	
	public void restockMissiles() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof PlayerShip) {
			((PlayerShip) store.elementAt(0)).reArm();
			
		}	
		
	}
///////////collisions////////////////////////////////////////////////////////////	
	public void asteroidKillShot() {
		//this just removes a missile and an asteroid if both exist
		for(int i = 1; i < store.size(); i++) {
			if(store.elementAt(i) instanceof Missile) {
				for(int j = 1; j < store.size(); j++) {
					if(store.elementAt(j) instanceof Asteroid) {
						store.remove(i);
						store.remove(j);
						playerScore++;
						break;
					}else {
						System.out.println("There are no Asteroids");
					}
				}//end for loop searching for Asteroids
			break;
			}else{
				System.out.println("There are no in flight missiles");
			}
		}//end for to search for Missiles	
		
	}
	
	public void npsKillShot() {
		//this just removes a missile and a non player ship if both exist
		for(int i = 1; i < store.size(); i++) {
			if(store.elementAt(i) instanceof Missile) {
				for(int j = 1; j < store.size(); j++) {
					if(store.elementAt(j) instanceof NonPlayerShip) {
						store.remove(i);
						store.remove(j);
						playerScore += 3;
						break;
					}else {
						System.out.println("There are no Non Player Ships");
					}
				}//end for loop searching for Asteroids
			break;	
			}else{
				System.out.println("There are no missiles");
			}
		}//end for to search for Missiles
		
	}
	
	public void playerKillShot() {
		//this just removes a missile and the player ship if there is a missile
		for(int i = 1; i < store.size(); i++) {
			if(store.elementAt(i) instanceof Missile) {
				store.remove(0);
				playerLives--;
				if(playerLives < 1) {
					System.out.println("GAME OVER");
				}else {
					addPlayerShip();
				}
			break;
			}else{
				System.out.println("There are no missiles");
			}
		}//end for to search for Missiles
		
	}
	
	public void playerKillShot(int j) {
	
		store.remove(0);
		playerLives--;
		if(playerLives < 1) {
			System.out.println("GAME OVER");
		}else {
			store.remove(j);
			addPlayerShip();
		}
		
	}
	
	public void playerCrash() {
		//this just removes an asteroid and the player ship if there is an asteroid
		for(int i = 1; i < store.size(); i++) {
			if(store.elementAt(i) instanceof Asteroid) {
				store.remove(0);
				playerLives--;
				if(playerLives < 1) {
					System.out.println("GAME OVER");
				}else {
					addPlayerShip();
				}
			break;
			}else{
				System.out.println("There are no asteroids");
			}
		}//end for to search for Asteroids	
		
	}
	
	public void playerNPSCrash() {
		//this just removes a non player ship and the player ship if there is a non player ship
		for(int i = 1; i < store.size(); i++) {
			if(store.elementAt(i) instanceof NonPlayerShip) {
				store.remove(0);
				playerLives--;
				if(playerLives < 1) {
					System.out.println("GAME OVER");
				}else {
					addPlayerShip();
				}
			break;
			}else{
				System.out.println("There are no non player ships");
			}
		}//end for to search for NonPlayerShips	
		
	}
	
	public void playerNPSCrash(int j) {
			
		store.remove(0);
		playerLives--;
		if(playerLives < 1) {
				System.out.println("GAME OVER");
		}else {
			store.remove(j);
			addPlayerShip();
		}
				
		
	}
	
	public void asteroidNPSCollision() {
		//this just removes a non player ship and an asteroid if both exist
		for(int i = 1; i < store.size(); i++) {
			if(store.elementAt(i) instanceof NonPlayerShip) {
				for(int j = 1; j < store.size(); j++) {
					if(store.elementAt(j) instanceof Asteroid) {
						store.remove(i);
						store.remove(j);
						break;
					}else {
						System.out.println("There are no Asteroids");
					}//end if else
				}//end loop for Asteroids
			break;
			}else{
				System.out.println("There are no non player ships");
			}
		}//end for to search for NonPlayerShips	
		
	}
	
	public void asteroidNPSCollision(int i, int j) {
		
		store.remove(i);
		store.remove(j);
		//play sound
					
	}
	
	public void asteroidCollision() {
		//this just removes 2 asteroids if 2 asteroids exist
		for(int i = 1; i < store.size(); i++) {
			if(store.elementAt(i) instanceof Asteroid) {
				for(int j = 1; j < store.size(); j++) {
					if(store.elementAt(j) instanceof Asteroid) {
						store.remove(i);
						store.remove(j);
						break;
					}else {
						System.out.println("There is only one Asteroid");
					}//end if else
				}//end loop for Asteroids
			break;
			}else{
				System.out.println("There aren't any asteroids.");
			}
		}//end for to search for NonPlayerShips	
		
	}
	
	//overload
	public void asteroidCollision(int i, int j) {
		store.remove(i);
		store.remove(j);
		//play asteroid collision sound
	}
	
	public void playerStatus() {
		System.out.println("===================================================================");
		System.out.println("                         Game Status                             ");
		System.out.println("SCORE:                                       " + playerScore + " ");
		System.out.println("MISSILE COUNT:                               " + ((PlayerShip)store.elementAt(0)).getMissileCount());
		System.out.println("ELAPSED TIME:                                " + elapsedGameTime + " ticks" );
		System.out.println("===================================================================");
		
	}
	
	public void tick() {
		
		moveObjects();
		checkForCollisions();
		//update space station blink
		blink();
		updateMissileFuel();
		//cleanup
		cleanup();
		elapsedGameTime++;	
		update();
	}
	

	//the order here gives us information on the initial elements 
	// i.e. player ship is element 0
	public void init() {
		GameObject.setGameWorld(new ProxyGameWorld(this));
		addPlayerShip();
		introMusic = new BGSound("intro1.wav");
		collision = new Sound("asteroidhit.wav");
		explosion = new Sound("missilehit.wav");
		paused = false;
		setSound(true);
		playerScore = 0;
		playerLives = 3;
		
	}

	
	public Iterator<GameObject> getIterator() {
		
		return store.getIterator();
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public int getPlayerLives() {
		return playerLives;
	}

	public int getElapsedGameTime() {
		return elapsedGameTime;
	}
	
	public int getMissiles() {
		return ((PlayerShip)store.elementAt(0)).getMissileCount();
	}
	
	public void refuelMissile() {
		for(int i = 0; i < store.size(); i++) {
			//update missile fuel and remove spent missiles
			if(store.elementAt(i) instanceof Missile && store.elementAt(i).isSelected() ) {
				Missile missile = (Missile)store.elementAt(i);
				missile.maxFuelLevel();
				System.out.println("Missile Refueled");
			}//end missile maintenance if
		}//end check for missiles
		update();
	}
	
	private void update() {
		this.setChanged();
		this.notifyObservers(new ProxyGameWorld(this));
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getXBOUND() {
		return XBOUND;
	}

	public void setXBOUND(int xBOUND) {
		XBOUND = xBOUND;
	}

	public int getYBOUND() {
		return YBOUND;
	}

	public void setYBOUND(int yBOUND) {
		YBOUND = yBOUND;
	}
	
	public Point getPntRelToParent() {
		return pntRelToParent;
	}
	
	public void setPntRelToParent(Point pntRelToParent) {
		this.pntRelToParent = pntRelToParent;
	}
	
	public BGSound getIntroMusic() {
		return introMusic;
	}
	public void setSound(boolean sound) {
		this.sound = sound;
		if(sound) {
			introMusic.run();
			sound = false;
		}else {
			introMusic.pause();
			sound = true;
		}
		
	}
	
	public boolean getSound() {
		return sound;
	}
	
	public void playCollision() {
		if(sound)
			collision.play();
	}
	
	public void playExplosion() {
		if(sound)
			explosion.play();
	}
	public boolean isGameOver() {
		return gameOver;
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public void moveObjects() {
		
		for(int i = 0; i < store.size(); i++) {
			//move objects
			if(store.elementAt(i) instanceof IMoveable) {
				((IMoveable)store.elementAt(i)).move();
			}
		}//end iteration
	
		update();
	}
	
	public void checkForCollisions() {

		//check for collision
		for(int i = 0; i < store.size(); i++) {
			//inner loop with minimal iterations
			for(int j = 1 + i; j < store.size(); j++) {
				//only check collision with other objects not self or others marked for destruction
				if(i !=j &&( !(store.elementAt(i).isDestroy()) || !(store.elementAt(i).isDestroy()) )) {
					if(((ICollider)store.elementAt(i)).collidesWith(store.elementAt(j))) {
						//handle collision at GameObject
						store.elementAt(i).handleCollision(store.elementAt(j));
						//different collisions GameWorld changes i.e. lives and sounds
						if(store.elementAt(i) instanceof Asteroid && store.elementAt(j) instanceof Asteroid) {
							playCollision();
							break;
						}
						if(store.elementAt(i) instanceof Asteroid && store.elementAt(j) instanceof NonPlayerShip || store.elementAt(i) instanceof Asteroid && store.elementAt(j) instanceof NonPlayerShip ) {
							playCollision();
							break;
						}
						if(store.elementAt(i) instanceof PlayerShip && store.elementAt(j) instanceof NonPlayerShip) {
							playCollision();
							playerLives--;
							break;
						}
						if(store.elementAt(i) instanceof PlayerShip && store.elementAt(j) instanceof Asteroid ) {
							playCollision();
							playerLives--;
							break;
						}
						if(store.elementAt(i) instanceof PlayerShip && store.elementAt(j) instanceof Missile && !((Missile)store.elementAt(j)).isPlayerMissile()) {
							playExplosion();
							playerLives--;
							break;
						}else if(store.elementAt(i) instanceof Missile && store.elementAt(j) instanceof PlayerShip && !((Missile)store.elementAt(i)).isPlayerMissile()) {
							playExplosion();
							playerLives--;
							break;
						}
						if(store.elementAt(i) instanceof PlayerShip && store.elementAt(j) instanceof Asteroid) {
							playCollision();
							playerLives--;
							break;
						}else if(store.elementAt(i) instanceof Asteroid && store.elementAt(j) instanceof PlayerShip) {
							playCollision();
							playerLives--;
							break;
						}
						if(store.elementAt(i) instanceof Missile && store.elementAt(j) instanceof NonPlayerShip) {
							playExplosion();
							playerScore += 3;
							break;
						}
						if(store.elementAt(i) instanceof Missile && store.elementAt(j) instanceof Asteroid) {
							playExplosion();
							playerScore++;
							break;
						}
					}//end collision check
				}//end pre collision check
			}//end inner loop
		}//end outer loop
		update();
	}
	
	public void blink() {
		for(int i = 0; i < store.size(); i++) {
			
			if(store.elementAt(i) instanceof SpaceStation) {
				if(elapsedGameTime%((SpaceStation)store.elementAt(i)).getBlinkRate() == 0) {
					//blink
					((SpaceStation)store.elementAt(i)).blink();
				}
	
			}//end find spacestation
		}//end iteration
		update();
	}
	public void updateMissileFuel() {		
		for(int i = 0; i < store.size(); i++) {
			//update missile fuel and remove spent missiles
			if(store.elementAt(i) instanceof Missile) {
				Missile missile = (Missile)store.elementAt(i);
				missile.burnFuel();
				if(missile.getFuelLevel() < 1) {
					store.remove(i);
				continue;
				}
			}//end missile maintenance if
		}//end check for missiles
		update();
	}
	public void cleanup() {
		if(((GameObject)store.elementAt(0)).isDestroy()){
			if(playerLives < 1) {
				System.out.println("GAME OVER");
				gameOver = true;
			}else {
				addPlayerShip();
			}
		}
		GameObjectsCollection toDestroy = new GameObjectsCollection();
		for(int i = 0; i < store.size(); i++) {
			if(((GameObject)store.elementAt(i)).isDestroy()){
				toDestroy.add(store.elementAt(i));
			}
		}
		//destroy 	
		store.removeAll(toDestroy);
		update();
	}
}