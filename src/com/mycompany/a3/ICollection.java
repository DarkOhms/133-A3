package com.mycompany.a3;

import java.util.Iterator;

public interface ICollection {
	public Iterator<GameObject> getIterator();
	
	public void add(GameObject newObject);
	
	public void add(int index,GameObject newObject);
	
	public void remove(int index);
	
	public int size();
	
	public GameObject elementAt(int index);
}
