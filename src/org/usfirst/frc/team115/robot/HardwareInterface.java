package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.subsystems.DriveBase;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;

public class HardwareInterface {
	public static DriveBase kDrive = new DriveBase();
	public static Joystick kDriverJoystick = new Joystick(0);

	public static AHRS kGyro = new AHRS(SPI.Port.kMXP);
}
