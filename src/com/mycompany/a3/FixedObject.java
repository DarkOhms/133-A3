package com.mycompany.a3;

public abstract class FixedObject extends GameObject {
	static int ID =0;
	private int id;
	
	public FixedObject() {
		this.id = ID;
		ID++;
	}

	public int getId() {
		return id;
	}
	
	
}
