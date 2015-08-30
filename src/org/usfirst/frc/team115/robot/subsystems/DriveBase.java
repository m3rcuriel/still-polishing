package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.DriveController;
import org.usfirst.frc.team115.lib.DriveOutput;
import org.usfirst.frc.team115.lib.Motion;
import org.usfirst.frc.team115.lib.StateHolder;
import org.usfirst.frc.team115.lib.Subsystem;
import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.HardwareInterface;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class DriveBase extends Subsystem {

	private Motion cachedMotion = new Motion(0, 0, 0, 0, 0, 0);
	private DriveController controller = null;

	//TODO move to HardwareInterface?
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

	public void setDriveOutputs(DriveOutput output) {
		leftDriveBaseFront.set(output.leftMotors);
		rightDriveBaseFront.set(-output.rightMotors);
	}

	@Override
	public void run() {
		if (controller == null)
			return;
		setDriveOutputs(controller.update(getPhysicalMotion()));
	}

	public Motion getPhysicalMotion() {
		cachedMotion.reset(
				leftDriveBaseFront.getPosition(),
				rightDriveBaseFront.getPosition(),
				leftDriveBaseFront.getSpeed(),
				rightDriveBaseFront.getSpeed(),
				HardwareInterface.kGyro.getAngle(),
				HardwareInterface.kGyro.getRate()
		);
		return cachedMotion;
	}

	@Override
	public void getState(StateHolder states) {
		states.put("gyro_angle", HardwareInterface.kGyro.getAngle());
        states.put("left_encoder", leftDriveBaseFront.getPosition());
        states.put("left_encoder_rate", leftDriveBaseFront.getSpeed());
        states.put("right_encoder_rate", rightDriveBaseFront.getSpeed());
        states.put("right_encoder", rightDriveBaseFront.getPosition());

        Motion setPointPose = controller == null
                ? getPhysicalMotion()
                : controller.getCurrentSetpoint();
        //TODO encoder distance to setpoint
        states.put("turn_set_point_pos", setPointPose.getHeading());
        states.put("left_signal", leftDriveBaseFront.get());
        states.put("right_signal", rightDriveBaseFront.get());
        states.put("on_target", (controller != null && controller.onTarget()) ? 1.0 : 0.0);
	}

	@Override
	public void reloadConstants() {
		// no constants to load
	}
}
