package org.usfirst.frc.team115.lib;

public final class DriveOutput {
	public double leftMotors;
	public double rightMotors;

	public DriveOutput(double left, double right) {
		this.leftMotors = left;
		this.rightMotors = right;
	}

	public static DriveOutput NEUTRAL = new DriveOutput(0, 0);
}
