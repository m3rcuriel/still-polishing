package com.mvrt.frc2015.subsystems.controllers;

import com.mvrt.frc2015.Constants;
import com.mvrt.lib.DriveController;
import com.mvrt.lib.DriveOutput;
import com.mvrt.lib.Motion;
import com.mvrt.lib.SynchronousPID;
import com.mvrt.lib.trajectory.TrajectoryFollower;
import com.mvrt.lib.trajectory.TrajectoryFollower.TrajectorySetpoint;

public class DriveStraightController extends DriveController {

	private TrajectoryFollowingPositionController distanceController;
	private SynchronousPID turnPid;
	private Motion setpointRelativeMotion;

	public DriveStraightController(Motion priorSetpoint, double goalSetpoint, double maxVelocity) {
		TrajectoryFollower.TrajectoryConfig config = new TrajectoryFollower.TrajectoryConfig();
		config.dt = Constants.kControlLoopPeriod;
		config.maxAcceleration = Constants.kDriveMaxAccelInchesPerSec2;

		distanceController = new TrajectoryFollowingPositionController(
				Constants.kDrivePositionKp,
				Constants.kDrivePositionKi,
				Constants.kDrivePositionKd,
				Constants.kDrivePositionKv,
				Constants.kDrivePositionKa,
				Constants.kDriveOnTargetError,
				config);

		TrajectorySetpoint initialSetpoint = new TrajectorySetpoint();
		initialSetpoint.position = encoderDistance(priorSetpoint);
		initialSetpoint.velocity = encoderVelocity(priorSetpoint);
		distanceController.setGoal(initialSetpoint, goalSetpoint);

		turnPid = new SynchronousPID();
		turnPid.setPID(Constants.kDriveStraightKp,
				Constants.kDriveStraightKi,
				Constants.kDriveStraightKd);
		turnPid.setSetpoint(priorSetpoint.getHeading());
		setpointRelativeMotion = new Motion(
				priorSetpoint.getLeftDistance(),
				priorSetpoint.getRightDistance(),
				0,
				0,
				priorSetpoint.getHeading(),
				priorSetpoint.getHeadingVelocity());
	}

	@Override
	public DriveOutput update(Motion motion) {
		distanceController.update((motion.getLeftDistance() + motion.getRightDistance()) / 2.0,
				(motion.getLeftVelocity() + motion.getRightVelocity()) / 2.0);
		double throttle = distanceController.get();
		double turn = turnPid.calculate(motion.getHeading());

		return new DriveOutput(throttle + turn, throttle - turn);
	}

	@Override
	public Motion getCurrentSetpoint() {
		TrajectorySetpoint trajectorySetpoint = distanceController.getSetpoint();
		double dist = trajectorySetpoint.position;
		double velocity = trajectorySetpoint.velocity;
		return new Motion(
				setpointRelativeMotion.getLeftDistance() + dist,
				setpointRelativeMotion.getRightDistance() + dist,
				setpointRelativeMotion.getLeftVelocity() + velocity,
				setpointRelativeMotion.getRightVelocity() + velocity,
				setpointRelativeMotion.getHeading(),
				setpointRelativeMotion.getHeadingVelocity());
	}

	@Override
	public boolean onTarget() {
		return distanceController.isOnTarget();
	}

	public static double encoderVelocity(Motion motion) {
		return (motion.getLeftVelocity() + motion.getRightVelocity()) / 2.0;
	}

	public static double encoderDistance(Motion motion) {
		return (motion.getLeftDistance() + motion.getRightDistance()) / 2.0;
	}
}
