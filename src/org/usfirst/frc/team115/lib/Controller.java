package org.usfirst.frc.team115.lib;

public abstract class Controller {
	protected boolean enabled = false;

	public abstract void reset();
	
	public abstract boolean isOnTarget();
}