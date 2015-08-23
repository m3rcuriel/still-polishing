package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.misc.ConstantsBase;

public class Constants extends ConstantsBase {
	public static double kTurnKp = 0;
	public static double kTurnKi = 0;
	public static double kTurnKd = 0;
	public static double kTurnKv = 0;
	public static double kTurnKa = 0;
	public static double kTurnOnTargetError = 0;

	public static double kTurnMaxAccelRadsPerSec2 = 0;

	public static double kDriveSensitivity = .75;

	public static long kSlowLooperPeriod = 10;

	public static long kControlLoopPeriod = 50;

	// End of editable constants
	public static int kEndEditableArea = 0;

	// Don't change electrical constants at runtime...!!!
	public static int kLeftDriveFront = 0;
	public static int kLeftDriveCenter = 0;
	public static int kLeftDriveRear = 0;

	public static int kRightDriveFront = 0;
	public static int kRightDriveCenter = 0;
	public static int kRightDriveRear = 0;

	public static int kElevatorMain= 0;
	public static int kElevatorFollow = 0;
	public static int kElevatorBrakeA = 0;
	public static int kElevatorBrakeB = 0;
	
	public static int kElevatorUp = 0;
	
	public static int pcm = 0;
	
	public static int kDriverJoystick = 0;
	public static int kOperatorJoystick = 1;

	@Override
	public String getFileLocation() {
		return "~/constants.txt";
	}
}
