
package org.usfirst.frc.team115.robot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team115.lib.DriveOutput;
import org.usfirst.frc.team115.lib.SystemManager;
import org.usfirst.frc.team115.robot.subsystems.DriveBase;
import org.usfirst.frc.team115.robot.subsystems.Elevator;
import org.usfirst.frc.team115.robot.subsystems.controllers.ElevatorController;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class MVRT extends IterativeRobot {

	ScheduledExecutorService slowLooper = Executors.newScheduledThreadPool(3);
	ScheduledExecutorService looper = Executors.newScheduledThreadPool(3);

	StateController stateController = new StateController();

	DriveBase drive = HardwareInterface.kDrive;

	DriveSystem driveSystem = new DriveSystem(drive);

	Joystick driveJoystick = HardwareInterface.kDriverJoystick;
	Joystick operatorJoystick = HardwareInterface.kOperatorJoystick;
	
	
	Elevator elevator = HardwareInterface.kElevator;
	
	ElevatorController elevatorController = new ElevatorController(elevator);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		slowLooper.scheduleAtFixedRate(new DriveBase(), 0, Constants.kSlowLooperPeriod, TimeUnit.MILLISECONDS);
		
		elevator.setController(elevatorController);
		
		slowLooper.scheduleAtFixedRate(new Elevator(), 0, Constants.kSlowLooperPeriod, TimeUnit.MILLISECONDS);
		SystemManager.getInstance().add(stateController);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {

	}

	/**
	 * This function is called periodically during operator control
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
	}

	@Override
	public void disabledInit() {
		looper.shutdown();
		slowLooper.shutdown();

		drive.setDriveOutputs(DriveOutput.NEUTRAL);;

		drive.reloadConstants();

		System.gc();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}

}
