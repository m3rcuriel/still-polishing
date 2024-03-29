package com.mvrt.frc2015;

import com.kauailabs.navx.frc.AHRS;
import com.mvrt.frc2015.subsystems.DriveBase;
import com.mvrt.frc2015.subsystems.Elevator;
import com.mvrt.frc2015.subsystems.Intake;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;

public class HardwareInterface {
  public static DriveBase kDrive = new DriveBase();
  public static Elevator kElevator = new Elevator();
  public static Intake kIntake = new Intake();

  public static Joystick kDriverJoystick = new Joystick(Constants.kDriverJoystick);
  public static Joystick kOperatorJoystick = new Joystick(Constants.kOperatorJoystick);

  public static AHRS kGyro = new AHRS(SPI.Port.kMXP);

  public static PowerDistributionPanel kPDP = new PowerDistributionPanel();
}
