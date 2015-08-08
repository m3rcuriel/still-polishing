package org.usfirst.frc.team115.lib;

public abstract class Subsystem implements StateMachine {

	protected String name;

	public Subsystem(String name) {
		this.name = name;
		SystemManager.getInstance().add(this);
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void reloadConstants();
}
