package com.mvrt.frc2015.subsystems;

import com.m3rcuriel.controve.api.Subsystem;
import com.m3rcuriel.controve.controllers.DriveController;
import com.m3rcuriel.controve.controllers.util.DriveOutput;
import com.m3rcuriel.controve.controllers.util.Motion;
import com.m3rcuriel.controve.retrievable.StateHolder;
import com.mvrt.frc2015.Constants;
import com.mvrt.frc2015.subsystems.controllers.DriveStraightController;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class DriveBase extends Subsystem implements Runnable {

  private Motion cachedMotion = new Motion(0, 0, 0, 0, 0, 0);
  private DriveController controller = null;

  // TODO move to HardwareInterface?
  private VictorSP leftDriveBaseFront;
  private VictorSP leftDriveBaseRear;

  private VictorSP rightDriveBaseFront;
  private VictorSP rightDriveBaseRear;

  private Encoder leftEncoder;
  private Encoder rightEncoder;

  public DriveBase() {
    super("Drivebase");

    leftDriveBaseFront = new VictorSP(Constants.kLeftDriveFront);
    leftDriveBaseRear = new VictorSP(Constants.kLeftDriveRear);

    rightDriveBaseFront = new VictorSP(Constants.kRightDriveFront);
    rightDriveBaseRear = new VictorSP(Constants.kRightDriveRear);

    leftEncoder = new Encoder(Constants.kLeftDriveEncoderA, Constants.kLeftDriveEncoderB);
    rightEncoder = new Encoder(Constants.kRightDriveEncoderA, Constants.kRightDriveEncoderB);

  }

  public void setDriveOutputs(DriveOutput output) {
    leftDriveBaseFront.set(output.leftMotors);
    leftDriveBaseRear.set(output.leftMotors);
    rightDriveBaseFront.set(-output.rightMotors);
    rightDriveBaseRear.set(-output.rightMotors);
  }

  public void setDistanceSetpoint(double distance) {
    setDistanceSetpoint(distance, Constants.kDriveMaxSpeedInchesPerSec);
  }

  public void setDistanceSetpoint(double distance, double velocity) {
    // 0 < vel < max_vel
    double velocityToUse = Math.min(Constants.kDriveMaxSpeedInchesPerSec, Math.max(velocity, 0));
    controller = new DriveStraightController(
        getPhysicalMotion(),
        distance,
        velocityToUse);
  }

  @Override
  public void run() {
    if (controller == null) {
      return;
    }
    setDriveOutputs(controller.update(getPhysicalMotion()));
  }

  public Motion getPhysicalMotion() {
    cachedMotion.reset(leftDriveBaseFront.getPosition(), rightDriveBaseFront.getPosition(),
        leftDriveBaseFront.getSpeed(), rightDriveBaseFront.getSpeed(), 0, 0);
    //HardwareInterface.kGyro.getAngle(), HardwareInterface.kGyro.getRate());
    return cachedMotion;
  }

  @Override
  public void getState(StateHolder states) {
    // states.put("gyro_angle", HardwareInterface.kGyro.getAngle());
    states.put("left_encoder", leftEncoder.getDistance());
    states.put("left_encoder_rate", leftEncoder.getRate());
    states.put("right_encoder_rate", rightEncoder.getRate());
    states.put("right_encoder", leftEncoder.getDistance());
    states.put("left_signal", leftDriveBaseFront.get());
    states.put("right_signal", rightDriveBaseFront.get());
    states.put("on_target", (controller != null && controller.onTarget()) ? 1.0 : 0.0);

    Motion setPointPose =
        controller == null ? getPhysicalMotion() : controller.getCurrentSetpoint();

    // TODO encoder distance to setpoint
    states.put("turn_set_point_pos", setPointPose.getHeading());

  }

  @Override
  public void reloadConstants() {
    // no constants to load
  }
}
