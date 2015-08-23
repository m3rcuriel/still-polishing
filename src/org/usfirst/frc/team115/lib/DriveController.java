package org.usfirst.frc.team115.lib;

public abstract class DriveController {
	public abstract DriveOutput update(Motion motion);

	public abstract Motion getCurrentSetpoint();

	public abstract boolean onTarget();
}
