package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.Joystick;

public class HardwareInterface {
	public static DriveBase kDrive = new DriveBase();
	public static Joystick kDriverJoystick = new Joystick(0); 
}
