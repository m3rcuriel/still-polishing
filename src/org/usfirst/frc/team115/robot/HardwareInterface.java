package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.subsystems.DriveBase;
import org.usfirst.frc.team115.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick;

public class HardwareInterface {
	public static DriveBase kDrive = new DriveBase();
	public static Joystick kDriverJoystick = new Joystick(Constants.kDriverJoystick); 
	
	public static Elevator kElevator = new Elevator();
	public static Joystick kOperatorJoystick = new Joystick(Constants.kOperatorJoystick);
}
