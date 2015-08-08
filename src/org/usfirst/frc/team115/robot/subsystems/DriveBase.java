package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.StateHolder;
import org.usfirst.frc.team115.lib.Subsystem;
import org.usfirst.frc.team115.robot.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class DriveBase extends Subsystem implements Runnable {

	private CANTalon leftDriveBaseFront = new CANTalon(Constants.kLeftDriveFront);
	private CANTalon leftDriveBaseRear = new CANTalon(Constants.kLeftDriveRear);

	private CANTalon rightDriveBaseFront = new CANTalon(Constants.kRightDriveFront);
	private CANTalon rightDriveBaseRear = new CANTalon(Constants.kRightDriveRear);

	public DriveBase() {
		super("Drivebase");
		leftDriveBaseRear.changeControlMode(ControlMode.Follower);
		leftDriveBaseRear.set(leftDriveBaseFront.getDeviceID());

		rightDriveBaseRear.changeControlMode(ControlMode.Follower);
		rightDriveBaseRear.set(rightDriveBaseFront.getDeviceID());
	}

	public void setLeftRightPower(double leftPower, double rightPower) {
		leftDriveBaseFront.set(leftPower);
		rightDriveBaseFront.set(-rightPower);
	}

	@Override
	public void run() {
	}

	@Override
	public void getState(StateHolder states) {
		// TODO add states
		
	}

	@Override
	public void reloadConstants() {
		// no constants to load
	}
}
