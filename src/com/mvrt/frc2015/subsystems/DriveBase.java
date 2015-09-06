package com.mvrt.frc2015.subsystems;

import com.mvrt.frc2015.Constants;
import com.mvrt.frc2015.HardwareInterface;
import com.mvrt.lib.DriveController;
import com.mvrt.lib.DriveOutput;
import com.mvrt.lib.Motion;
import com.mvrt.lib.StateHolder;
import com.mvrt.lib.Subsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class DriveBase extends Subsystem implements Runnable {

  private Motion cachedMotion = new Motion(0, 0, 0, 0, 0, 0);
  private DriveController controller = null;

  // TODO move to HardwareInterface?
  private CANTalon leftDriveBaseFront;
  private CANTalon leftDriveBaseRear;

  private CANTalon rightDriveBaseFront;
  private CANTalon rightDriveBaseRear;

  public DriveBase() {
    super("Drivebase");

    leftDriveBaseFront = new CANTalon(Constants.kLeftDriveFront);
    leftDriveBaseRear = new CANTalon(Constants.kLeftDriveRear);

    rightDriveBaseFront = new CANTalon(Constants.kRightDriveFront);
    rightDriveBaseRear = new CANTalon(Constants.kRightDriveRear);

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
    if (controller == null) {
      return;
    }
    setDriveOutputs(controller.update(getPhysicalMotion()));
  }

  public Motion getPhysicalMotion() {
    cachedMotion.reset(leftDriveBaseFront.getPosition(), rightDriveBaseFront.getPosition(),
        leftDriveBaseFront.getSpeed(), rightDriveBaseFront.getSpeed(),
        HardwareInterface.kGyro.getAngle(), HardwareInterface.kGyro.getRate());
    return cachedMotion;
  }

  @Override
  public void getState(StateHolder states) {
    states.put("gyro_angle", HardwareInterface.kGyro.getAngle());
    states.put("left_encoder", leftDriveBaseFront.getPosition());
    states.put("left_encoder_rate", leftDriveBaseFront.getSpeed());
    states.put("right_encoder_rate", rightDriveBaseFront.getSpeed());
    states.put("right_encoder", rightDriveBaseFront.getPosition());
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
