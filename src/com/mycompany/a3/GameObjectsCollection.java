package com.mycompany.a3;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class GameObjectsCollection implements ICollection {

	private Vector<GameObject> store;
	
	public GameObjectsCollection(){
		store = new Vector<GameObject>();
	}

	@Override
	public Iterator<GameObject> getIterator() {
		// TODO Auto-generated method stub
		return store.iterator();
	}

	@Override
	public void add(GameObject newObject) {
		store.add(newObject);
		
	}

	@Override
	public void remove(int index) {
		store.remove(index);
		
	}
	
	public boolean removeAll(GameObjectsCollection c) {
		return store.removeAll(c.getStore());
		
	}

	public Vector<GameObject> getStore() {
		return store;
	}

	@Override
	public int size() {

		return store.size();
	}

	@Override
	public GameObject elementAt(int index) {
		return store.elementAt(index);
	}

	@Override
	public void add(int index, GameObject newObject) {
		store.add(index, newObject);
		
	}
	
	

}
