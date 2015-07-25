package org.usfirst.frc.team115.robot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.usfirst.frc.team115.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.Joystick;

public class MVRTRobot {
	public static final ScheduledExecutorService statePool = Executors.newScheduledThreadPool(2);

	public static final DriveBase drive = new DriveBase();

	public static final Joystick driverJoystick = new Joystick(Constants.driverJoystick.getInt());
	public static final DriveSystem driveSystem = new DriveSystem(drive);

	public static final DriveBase drivebase = new DriveBase();

	public static void init() {
		statePool.scheduleAtFixedRate(drive, 0, 20, TimeUnit.MILLISECONDS);
	}
}
