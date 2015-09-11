package com.mvrt.frc2015.subsystems.controllers;

import com.m3rcuriel.controve.controllers.DriveController;
import com.m3rcuriel.controve.controllers.TrajectoryFollowingPositionController;
import com.m3rcuriel.controve.controllers.util.DriveOutput;
import com.m3rcuriel.controve.controllers.util.Motion;
import com.m3rcuriel.controve.trajectory.TrajectoryFollower;
import com.mvrt.frc2015.Constants;

public class TurnInPlaceController extends DriveController {
  private final TrajectoryFollowingPositionController controller;
  private final Motion setpointRelativeMotion;

  public TurnInPlaceController(Motion motionToContinue, double destHeading, double velocity) {
    TrajectoryFollower.TrajectoryConfig config = new TrajectoryFollower.TrajectoryConfig();
    config.dt = Constants.kControlLoopPeriod;
    config.maxAcceleration = Constants.kTurnMaxAccelRadsPerSec2;
    config.maxVelocity = velocity;
    controller =
        new TrajectoryFollowingPositionController(Constants.kTurnKp, Constants.kTurnKi,
            Constants.kTurnKd, Constants.kTurnKv, Constants.kTurnKa, Constants.kTurnOnTargetError,
            config);
    TrajectoryFollower.TrajectorySetpoint initialSetpoint =
        new TrajectoryFollower.TrajectorySetpoint();
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
    return new Motion(setpointRelativeMotion.leftDistance, setpointRelativeMotion.rightDistance,
        setpointRelativeMotion.leftVelocity, setpointRelativeMotion.rightVelocity,
        setpoint.position, setpoint.velocity);
  }

  @Override
  public boolean onTarget() {
    return controller.isOnTarget();
  }

}
