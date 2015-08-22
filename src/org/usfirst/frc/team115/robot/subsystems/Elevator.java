package org.usfirst.frc.team115.robot.subsystems;

import org.usfirst.frc.team115.lib.StateHolder;
import org.usfirst.frc.team115.lib.Subsystem;
import org.usfirst.frc.team115.robot.Constants;
import org.usfirst.frc.team115.robot.ElevatorController;
import org.usfirst.frc.team115.robot.HardwareInterface;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Elevator extends Subsystem implements Runnable{
	
	private ElevatorController controller;

	private static final double BOTTOM = 56;

	private static final double TICKS_PER_ROTATION = 1024;
	private static final double INCHES_PER_ROTATION = 3.53559055;
	private static final double TICKS_PER_INCH = TICKS_PER_ROTATION / INCHES_PER_ROTATION;

	private DoubleSolenoid brakeSolenoid = new DoubleSolenoid(Constants.pcm, Constants.kElevatorBrakeA, Constants.kElevatorBrakeB);

	private CANTalon elevatorDriveMain, elevatorDriveFollow;

	public Elevator() {
		super("Elevator");
		
		elevatorDriveMain = new CANTalon(Constants.kElevatorMain);
		elevatorDriveMain.setReverseSoftLimit((int) (0.25 * TICKS_PER_INCH));
		elevatorDriveMain.enableReverseSoftLimit(true);
		elevatorDriveMain.enableForwardSoftLimit(false);
		elevatorDriveMain.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
		elevatorDriveMain.enableLimitSwitch(true, false);

		elevatorDriveFollow = new CANTalon(Constants.kElevatorFollow);
		elevatorDriveFollow.changeControlMode(ControlMode.Follower);
		elevatorDriveFollow.set(elevatorDriveMain.getDeviceID());
	}


	public void setElevatorPower(double power){
		if(power < 0 && getHeight() < 5){
			power *= 0.4;
		}
		else if(power < 0){
			power *= 0.8;
		}
		elevatorDriveMain.set(power * -1);
	}

	public boolean isLimitPressed(){
		return elevatorDriveMain.isFwdLimitSwitchClosed();
	}

	public double getHeight(){
		return BOTTOM - elevatorDriveMain.getPosition() / TICKS_PER_INCH;
	}

	public void setBrake(boolean brake){
		if(brake){
			brakeSolenoid.set(Value.kForward);
		}
		else{
			brakeSolenoid.set(Value.kReverse);
		}
	}

	public void resetEncoder(){
		elevatorDriveMain.setPosition(BOTTOM * TICKS_PER_INCH);
	}

	public void brake(){
		setElevatorPower(0);
		setBrake(true);
	}
	
	public void setController(ElevatorController controller){
		this.controller = controller;
	}


	@Override
	public void run() {
		if(controller == null) return;
		
		controller.presetUp(HardwareInterface.kOperatorJoystick.getRawButton(Constants.kElevatorUp));
		controller.drive();
	}


	@Override
	public void getState(StateHolder states) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reloadConstants() {
		// TODO Auto-generated method stub
		
	}

}
