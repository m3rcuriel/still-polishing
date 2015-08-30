package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.Controller;
import org.usfirst.frc.team115.lib.StateHolder;
import org.usfirst.frc.team115.lib.Subsystem;
import org.usfirst.frc.team115.lib.trajectory.TrajectoryFollower;
import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.subsystems.controllers.TrajectoryFollowingPositionController;

import edu.wpi.first.wpilibj.CANTalon;

public class Elevator extends Subsystem {

	//TODO move to Constants
	public static final double BOTTOM_HEIGHT = 56;

	private static final int TICKS_PER_ROTATION = 1024;
	private static final double INCHES_PER_ROTATION = 3.53559055;
	private static final double TICKS_PER_INCH = TICKS_PER_ROTATION / INCHES_PER_ROTATION;

	private Controller controller = null;

	private CANTalon elevatorDrive1 = new CANTalon(Constants.kElevatorDrive1);
	private CANTalon elevatorDrive2 = new CANTalon(Constants.kElevatorDrive2);

	public Elevator() {
		super("elevator");
	}

	public TrajectoryFollower.TrajectorySetpoint getSetpoint() {
		TrajectoryFollower.TrajectorySetpoint setpoint;

		if(controller instanceof TrajectoryFollowingPositionController) {
			setpoint = ((TrajectoryFollowingPositionController) controller).getSetpoint();
		} else {
			setpoint = new TrajectoryFollower.TrajectorySetpoint();
			setpoint.position = getHeight();
		}
		return setpoint;
	}

	public double getHeight() {
		return BOTTOM_HEIGHT - elevatorDrive1.getPosition() / TICKS_PER_INCH;
	}

	@Override
	public void getState(StateHolder states) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reloadConstants() {
		// TODO Auto-generated method stub

	}

}
