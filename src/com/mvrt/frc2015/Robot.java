package com.mvrt.frc2015;

import com.mvrt.frc2015.statemachine.StateController;
import com.mvrt.frc2015.subsystems.DriveBase;
import com.mvrt.lib.DriveOutput;
import com.mvrt.lib.SystemManager;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the IterativeRobot documentation. If you change the name of this class
 * or the package after creating this project, you must also update the manifest file in the
 * resource directory.
 */
public class Robot extends IterativeRobot {

  ScheduledExecutorService slowLooper = Executors.newScheduledThreadPool(3);
  ScheduledExecutorService looper = Executors.newScheduledThreadPool(3);

  StateController stateController = new StateController();
  OperatorInterface operatorInterface = new OperatorInterface();

  DriveSystem driveSystem = new DriveSystem(HardwareInterface.kDrive);

  Joystick driveJoystick = HardwareInterface.kDriverJoystick;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    slowLooper.scheduleAtFixedRate(new DriveBase(), 0, Constants.kSlowLooperPeriod,
        TimeUnit.MILLISECONDS);

    SystemManager.getInstance().add(stateController);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    boolean quickTurn = driveJoystick.getTrigger();
    double turn = driveJoystick.getX();

    if (quickTurn) {
      double sign = Math.signum(turn);
      turn = turn * turn * sign;
    }

    driveSystem.drive(driveJoystick.getY(), turn, quickTurn);
    stateController.update(operatorInterface.getCommands());
  }

  @Override
  public void disabledInit() {
    looper.shutdown();
    slowLooper.shutdown();

    HardwareInterface.kDrive.setDriveOutputs(DriveOutput.NEUTRAL);;

    HardwareInterface.kDrive.reloadConstants();
    HardwareInterface.kElevator.reloadConstants();

    System.gc();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

  }

}
