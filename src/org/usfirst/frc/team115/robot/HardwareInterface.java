package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.subsystems.DriveBase;
import org.usfirst.frc.team115.robot.subsystems.Elevator;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;

public class HardwareInterface {
	public static DriveBase kDrive = new DriveBase();
	public static Joystick kDriverJoystick = new Joystick(Constants.kDriverJoystick); 
	
	public static Elevator kElevator = new Elevator();
	public static Joystick kOperatorJoystick = new Joystick(Constants.kOperatorJoystick);

	public static AHRS kGyro = new AHRS(SPI.Port.kMXP);
}
