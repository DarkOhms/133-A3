package com.mycompany.a3;

import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import java.util.Vector;

public class GameWorld extends Observable {
	private int XBOUND = 1024;
	private int YBOUND = 768;
	private int playerScore;
	private int playerLives;
	private int elapsedGameTime;
	
	private Random random = new Random();
	//private Vector<GameObject> store = new Vector<GameObject>();
	
	private GameObjectsCollection store = new GameObjectsCollection();
	
	
	//methods for generating and checking random locations for new objects
	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	private double randomXLocation() {

		return random.nextInt(XBOUND - 10) + 5.0;
		
	}
	
	private double newXLocation() {
		double possibleXLocation = randomXLocation();
		
		for(int i =0; i < store.size(); i++) {
			if(possibleXLocation == store.elementAt(i).getLocationX())
				possibleXLocation = newXLocation();
		}
		
		return possibleXLocation;
	}
	
	private double newYLocation() {
		double possibleYLocation = randomYLocation();
		
		for(int i =0; i < store.size(); i++) {
			if(possibleYLocation == store.elementAt(i).getLocationY())
				possibleYLocation = newYLocation();
		}
		
		return possibleYLocation;
	}
	
	private double randomYLocation() {

		return random.nextInt(YBOUND - 10) + 5.0;
		
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
		setRandomLocation(asteroid);
		//add asteroid to game objects
		store.add(asteroid);
		//print to console
		System.out.println("A new ASTEROID has been created");
		update();
	}
	
	public void addPlayerShip() {
		//create a new player ship object
		PlayerShip playerShip = new PlayerShip();
		//add playerShip to game objects with special position
		store.add(0,playerShip);
		//print to console
		System.out.println("A new PlayerShip has been created");
		update();
	}
	
	public void addNonPlayerShip() {
		//create a new non player ship object, must have new location parameters
		NonPlayerShip nonPlayerShip = new NonPlayerShip(newXLocation(), newYLocation());
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
		setRandomLocation(spaceStation);
		//add spaceStation to game objects
		store.add(spaceStation);
		//print to console
		System.out.println("A new Space Station has been created");
		update();
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
		update();
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
		update();
	}
	
	public void turnLeft() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof PlayerShip) {
			PlayerShip mObj = (PlayerShip)store.elementAt(0);
			mObj.steer(-1);
		}
		update();
	}
	
	public void turnRight() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof PlayerShip) {
			PlayerShip mObj = (PlayerShip)store.elementAt(0);
			mObj.steer(1);
		}
		update();
	}
	
	public void rotateLauncher() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof PlayerShip) {
			MissileLauncher mObj = ((PlayerShip) store.elementAt(0)).getMissileLauncher();
			mObj.steer(-5);
		}
		update();
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
		update();
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
		update();
	}
	
	public void hyperspace() {
		//we know element 0 is the player ship because on init()
		//can we crash as a result of hyperspace?
		if(store.elementAt(0) instanceof PlayerShip) {
			((PlayerShip) store.elementAt(0)).hyperspace();
			
		}	
		update();
	}
	
	public void restockMissiles() {
		//we know element 0 is the player ship because on init()
		if(store.elementAt(0) instanceof PlayerShip) {
			((PlayerShip) store.elementAt(0)).reArm();
			
		}	
		update();
	}
	
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
		update();
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
		update();
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
		update();
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
		update();
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
		update();
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
		update();
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
		update();
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
		//iterate through objects
		for(int i = 0; i < store.size(); i++) {
			//move objects
			if(store.elementAt(i) instanceof IMoveable)
				 ((IMoveable)store.elementAt(i)).move();
			//update space station blink
			if(store.elementAt(i) instanceof SpaceStation)
				//avoid divide by zero error
				if(((SpaceStation)store.elementAt(i)).getBlinkRate() != 0)
					if(elapsedGameTime%((SpaceStation)store.elementAt(i)).getBlinkRate() == 0) {
						//blink
					}
			//update missile fuel and remove spent missiles
			if(store.elementAt(i) instanceof Missile) {
				 Missile missile = (Missile)store.elementAt(i);
				 missile.burnFuel();
				 if(missile.getFuelLevel() < 1) {
					 store.remove(i);
					 continue;
				 }
			}//end missile maintenance if
		}//end iteration
		elapsedGameTime++;	
		update();
	}
	
	public void map() {
		for(int i = 0; i < store.size(); i++) {
			System.out.println(store.elementAt(i).toString());
		}
	}
	
	//the order here gives us information on the initial elements 
	// i.e. player ship is element 0
	public void init() {
		addPlayerShip();
		playerScore = 0;
		playerLives = 3;
		update();
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
	
	private void update() {
		this.setChanged();
		this.notifyObservers(new ProxyGameWorld(this));
	}

	
}


