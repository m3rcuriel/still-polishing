package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Subsystem;
import org.usfirst.frc.team115.robot.Constants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class DriveBase extends Subsystem {

	private CANTalon leftDriveBaseFront = new CANTalon(Constants.LEFT_DRIVE_FRONT.getInt());
	private CANTalon leftDriveBaseCenter = new CANTalon(Constants.LEFT_DRIVE_CENTER.getInt());
	private CANTalon leftDriveBaseRear = new CANTalon(Constants.LEFT_DRIVE_REAR.getInt());

	private CANTalon rightDriveBaseFront = new CANTalon(Constants.RIGHT_DRIVE_FRONT.getInt());
	private CANTalon rightDriveBaseCenter = new CANTalon(Constants.RIGHT_DRIVE_CENTER.getInt());
	private CANTalon rightDriveBaseRear = new CANTalon(Constants.RIGHT_DRIVE_REAR.getInt());

	public DriveBase() {
		super("Drivebase");
		leftDriveBaseCenter.changeControlMode(ControlMode.Follower);
		leftDriveBaseCenter.set(leftDriveBaseFront.getDeviceID());
		leftDriveBaseRear.changeControlMode(ControlMode.Follower);
		leftDriveBaseRear.set(leftDriveBaseFront.getDeviceID());

		rightDriveBaseCenter.changeControlMode(ControlMode.Follower);
		rightDriveBaseCenter.set(rightDriveBaseFront.getDeviceID());
		rightDriveBaseRear.changeControlMode(ControlMode.Follower);
		rightDriveBaseRear.set(rightDriveBaseFront.getDeviceID());
	}

	public void setLeftRightPower(double leftPower, double rightPower) {
		leftDriveBaseFront.set(leftPower);
		rightDriveBaseFront.set(-rightPower);
	}

	@Override
	public void run() {
		super.run();
	}
}
