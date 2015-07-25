package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.misc.ConstantsBase;

public class Constants extends ConstantsBase {
	public static final Constant sensitivity = new Constant("sensitivity", .75);

	public static final Constant LEFT_DRIVE_FRONT = new Constant("Left Drive Front", 0);
	public static final Constant LEFT_DRIVE_CENTER = new Constant("Left Drive Center", 0);
	public static final Constant LEFT_DRIVE_REAR = new Constant("Left Drive Rear", 0);

	public static final Constant RIGHT_DRIVE_FRONT = new Constant("Left Drive Front", 0);
	public static final Constant RIGHT_DRIVE_CENTER = new Constant("Left Drive Front", 0);
	public static final Constant RIGHT_DRIVE_REAR = new Constant("Left Drive Front", 0);

	public static final Constant driverJoystick = new Constant("Driver Joystick", 0);

	// private default constructor to prevent initialization
	private Constants() {

	}
}
