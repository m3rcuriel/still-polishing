package org.usfirst.frc.team115.lib;

import org.usfirst.frc.team115.robot.MVRTRobot;

public abstract class Controller extends MVRTRobot implements Runnable {
	protected boolean enabled = false;

	@Override
	public abstract void run();

	public abstract void reset();

	public abstract double getGoal();

	public void enable() {
		enabled = true;
	}

	public void disable() {
		enabled = false;
	}

	public boolean enabled() {
		return enabled;
	}
}