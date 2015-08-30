package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.AbstractSpeedController;
import org.usfirst.frc.team115.lib.Controller;
import org.usfirst.frc.team115.lib.StateHolder;
import org.usfirst.frc.team115.lib.Subsystem;
import org.usfirst.frc.team115.lib.trajectory.TrajectoryFollower;
import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.subsystems.controllers.TrajectoryFollowingPositionController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Elevator extends Subsystem implements Runnable {

	//TODO move to Constants
	public static final double BOTTOM_HEIGHT = 0;
	public static final double TOP_HEIGHT = 56;

	private static final int TICKS_PER_ROTATION = 1024;
	private static final double INCHES_PER_ROTATION = 3.53559055;
	private static final double TICKS_PER_INCH = TICKS_PER_ROTATION / INCHES_PER_ROTATION;

	private Controller controller = null;

	private CANTalon elevatorDrive1;
	private CANTalon elevatorDrive2;
	private AbstractSpeedController speedController;

	private DoubleSolenoid brakeSolenoid;

	private boolean brakeOnTarget;

	public Elevator() {
		super("elevator");

		elevatorDrive1 = new CANTalon(Constants.kElevatorDrive1);
		elevatorDrive2 = new CANTalon(Constants.kElevatorDrive2);
		elevatorDrive2.changeControlMode(ControlMode.Follower);
		elevatorDrive2.set(elevatorDrive1.getDeviceID());

		brakeSolenoid = new DoubleSolenoid(Constants.kElevatorBrakeA, Constants.kElevatorBrakeB);
		//		speedController =  = new AbstractSpeedController(new CANTalon[]{elevatorDrive1, elevatorDrive2},
		//				new int[]{Constants.kElevatorDrive1PDP, Constants.kElevatorDrive2PDP});
	}

	public TrajectoryFollower.TrajectorySetpoint getSetpoint() {
		TrajectoryFollower.TrajectorySetpoint setpoint;

		if (controller instanceof TrajectoryFollowingPositionController) {
			setpoint = ((TrajectoryFollowingPositionController) controller).getSetpoint();
		} else {
			setpoint = new TrajectoryFollower.TrajectorySetpoint();
			setpoint.position = getHeight();
		}
		return setpoint;
	}

	public double getHeight() {
		return elevatorDrive1.getPosition() / TICKS_PER_INCH;
	}

	public double getVelocity() {
		return (((elevatorDrive1.getSpeed() + elevatorDrive2.getSpeed()) / 2) / TICKS_PER_INCH) / 10;
	}

	public double getGoalHeight() {
		if (controller instanceof TrajectoryFollowingPositionController) {
			return ((TrajectoryFollowingPositionController) controller).getGoal();
		} else {
			return -1;
		}
	}

	public void setSpeedUnsafe(double speed) {
		elevatorDrive1.set(speed);
	}

	public void setSpeedLimitlessSafe(double speed) {
		if (speed > 1E-3 || speed < -1E-3) {
			setBrake(false);
		}
		setSpeedUnsafe(speed);
	}

	public void setSpeedSafe(double speed) {
		double height = getHeight();
		if (speed > 1E-3 || speed < -1E-3) {
			setBrake(false);
		}
		if (height >= TOP_HEIGHT) {
			speed = Math.min(0, speed);
		} else if (height <= BOTTOM_HEIGHT) {
			speed = Math.max(0, speed);
		}
		setSpeedUnsafe(speed);
	}

	public void setBrake(boolean on) {
		brakeSolenoid.set(on ? Value.kForward : Value.kReverse);
		if (on) {
			setSpeedUnsafe(0);
		}
	}

	public boolean getBrake() {
		return brakeSolenoid.get() == DoubleSolenoid.Value.kForward;
	}

	public synchronized void setOpenLoop(double speed, boolean brake) {
		controller = null;
		setBrake(brake);
		setSpeedLimitlessSafe(speed);
	}

	public synchronized void setPositionSetpoint(double setpoint, boolean brakeOnTarget) {
		this.brakeOnTarget = brakeOnTarget;
		TrajectoryFollower.TrajectorySetpoint priorSetpoint = getSetpoint();
		if (!(controller instanceof TrajectoryFollowingPositionController)) {
			TrajectoryFollower.TrajectoryConfig config = new TrajectoryFollower.TrajectoryConfig();
			config.dt = Constants.kControlLoopPeriod;
			config.maxAcceleration = Constants.kElevatorMaxAccelInchesPerSec2;
			config.maxVelocity = Constants.kElevatorMaxSpeedInchesPerSec2;
			controller = new TrajectoryFollowingPositionController(
					Constants.kElevatorPositionKp,
					Constants.kElevatorPositionKi,
					Constants.kElevatorPositionKd,
					Constants.kElevatorPositionKv,
					Constants.kElevatorPositionKa,
					Constants.kElevatorOnTargetError,
					config);
			((TrajectoryFollowingPositionController) controller).setGoal(priorSetpoint, setpoint);
		}
	}

	@Override
	public void getState(StateHolder states) {
		states.put("height", getHeight());
		states.put("velocity", getVelocity());
		states.put("setpoint", getSetpoint().position);
		// TODO add abstract SpeedController
		states.put("output", elevatorDrive1.get());
	}

	@Override
	public void run() {
		if (controller instanceof TrajectoryFollowingPositionController) {
			TrajectoryFollowingPositionController positionController = (TrajectoryFollowingPositionController) controller;
			if (positionController.isOnTarget()) {
				setBrake(brakeOnTarget && getHeight() > 1.0);
				positionController.update(getHeight(), getVelocity());
				if (!brakeOnTarget) {
					setSpeedSafe(positionController.get());
				} else {
					setOpenLoop(0, true);
					controller = null;
				}
			} else {
				positionController.update(getHeight(), getVelocity());
				setSpeedSafe(positionController.get());
			}
		}

		if (getBrake()) {
			setSpeedUnsafe(0.0);
		}
	}

	@Override
	public void reloadConstants() {
		controller = null;
	}

	public boolean isOnTarget() {
		return controller != null ? controller.isOnTarget() : true;
	}
}
