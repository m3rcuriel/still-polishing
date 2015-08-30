package org.usfirst.frc.team115.robot.subsystems.controllers;

import org.usfirst.frc.team115.lib.DriveController;
import org.usfirst.frc.team115.lib.DriveOutput;
import org.usfirst.frc.team115.lib.Motion;
import org.usfirst.frc.team115.lib.trajectory.TrajectoryFollower;
import org.usfirst.frc.team115.robot.Constants;

public class TurnInPlaceController extends DriveController {
	private final TrajectoryFollowingPositionController controller;
	private final Motion setpointRelativeMotion;

	public TurnInPlaceController(Motion motionToContinue, double destHeading, double velocity) {
		TrajectoryFollower.TrajectoryConfig config = new TrajectoryFollower.TrajectoryConfig();
		config.dt = Constants.kControlLoopPeriod;
		config.maxAcceleration = Constants.kTurnMaxAccelRadsPerSec2;
		config.maxVelocity = velocity;
		controller = new TrajectoryFollowingPositionController(
				Constants.kTurnKp,
				Constants.kTurnKi,
				Constants.kTurnKd,
				Constants.kTurnKv,
				Constants.kTurnKa,
				Constants.kTurnOnTargetError,
				config);
		TrajectoryFollower.TrajectorySetpoint initialSetpoint = new TrajectoryFollower.TrajectorySetpoint();
		initialSetpoint.position = motionToContinue.heading;
		initialSetpoint.velocity = motionToContinue.headingVelocity;
		controller.setGoal(initialSetpoint, destHeading);

		setpointRelativeMotion = motionToContinue;
	}

	@Override
	public DriveOutput update(Motion motions) {
		controller.update(motions.getHeading(), motions.getHeadingVelocity());
		double turn = controller.get();
		return new DriveOutput(turn, -turn);
	}

	@Override
	public Motion getCurrentSetpoint() {
		TrajectoryFollower.TrajectorySetpoint setpoint = controller.getSetpoint();
		return new Motion(setpointRelativeMotion.leftDistance,
				setpointRelativeMotion.rightDistance,
				setpointRelativeMotion.leftVelocity,
				setpointRelativeMotion.rightVelocity,
				setpoint.position,
				setpoint.velocity);
	}

	@Override
	public boolean onTarget() {
		return controller.isOnTarget();
	}

}
