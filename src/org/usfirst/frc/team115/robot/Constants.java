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

	public static double kDrivePositionKp = 0;
	public static double kDrivePositionKi = 0;
	public static double kDrivePositionKd = 0;
	public static double kDrivePositionKv = 0;
	public static double kDrivePositionKa = 0;
	public static double kDriveOnTargetError = 0;

	public static double kDriveMaxAccelInchesPerSec2 = 0;

	public static double kDriveStraightKp = 0;
	public static double kDriveStraightKi = 0;
	public static double kDriveStraightKd = 0;

	public static double kDriveSensitivity = .75;
	
	public static double kElevatorPositionKp = 0;
	public static double kElevatorPositionKi = 0;
	public static double kElevatorPositionKd = 0;
	public static double kElevatorPositionKv = 0;
	public static double kElevatorPositionKa = 0;
	public static double kElevatorOnTargetError = 0;
	
	public static double kElevatorMaxAccelInchesPerSec2 = 0;
	public static double kElevatorMaxSpeedInchesPerSec2 = 0;

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

	public static int kElevatorDrive1 = 0;
	public static int kElevatorDrive2 = 0;
	
	public static int kElevatorBrakeA = 0;
	public static int kElevatorBrakeB = 0;

	public static int kDriverJoystick = 0;

	@Override
	public String getFileLocation() {
		return "~/constants.txt";
	}
}
