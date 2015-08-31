package com.mvrt.lib;

public abstract class DriveController {
	public abstract DriveOutput update(Motion motion);

	public abstract Motion getCurrentSetpoint();

	public abstract boolean onTarget();
}
