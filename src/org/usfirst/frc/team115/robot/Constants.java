package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.misc.ConstantsBase;

public class Constants extends ConstantsBase {
	public static double kDriveSensitivity = .75;

	public static long kSlowLooperPeriod = 10;
	
	// End of editable constants
	public static int kEndEditableArea = 0;

	// Don't change electrical constants at runtime...!!!
	public static int kLeftDriveFront = 0;
	public static int kLeftDriveCenter = 0;
	public static int kLeftDriveRear = 0;

	public static int kRightDriveFront = 0;
	public static int kRightDriveCenter = 0;
	public static int kRightDriveRear = 0;

	public static int kDriverJoystick = 0;

	@Override
	public String getFileLocation() {
		return "~/constants.txt";
	}
}
