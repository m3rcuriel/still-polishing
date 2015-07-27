
package org.usfirst.frc.team115.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class MVRT extends IterativeRobot {

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		MVRTRobot.init();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopInit() {
		MVRTRobot.drivebase.turnOffControllers();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		boolean quickTurn = MVRTRobot.driverJoystick.getTrigger();
		double turn = MVRTRobot.driverJoystick.getX();

		if (quickTurn) {
			double sign = Math.signum(turn);
			turn = turn * turn * sign;
		}

		MVRTRobot.driveSystem.drive(MVRTRobot.driverJoystick.getY(), turn, quickTurn);
	}

	@Override
	public void disabledInit() {
		Constants.readConstantsFromFile();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}

}
