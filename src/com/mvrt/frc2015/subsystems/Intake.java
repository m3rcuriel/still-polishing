package com.mvrt.frc2015.subsystems;

import com.mvrt.frc2015.Constants;
import com.mvrt.lib.StateHolder;
import com.mvrt.lib.Subsystem;
import com.mvrt.lib.AbstractSpeedController;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake extends Subsystem {
	
	private State state;
	
	private DoubleSolenoid intake;
	private AbstractSpeedController leftMotor;
	private AbstractSpeedController rightMotor;
	
	public enum State {
		OPEN, CLOSED;
	}

	public Intake(AbstractSpeedController leftMotor, AbstractSpeedController rightMotor ) {
		super("Intake");
		
		intake = new DoubleSolenoid(Constants.kIntakeSolenoidA, Constants.kIntakeSolenoidB);
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		leftMotor.setInverted(true);
		rightMotor.setInverted(true);
		state = intake.get() == Value.kForward ? State.OPEN : State.CLOSED;
	}
	
	public void open() {
		state = State.OPEN;
		intake.set(Value.kForward);
	}
	
	public void close() {
		state = State.CLOSED;
		intake.set(Value.kReverse);
	}
	
	public void setRollerSpeed(double speed) {
		setLeftRightRollerMotors(speed, speed);
	}
	
	public void setLeftRightRollerMotors(double leftSpeed, double rightSpeed) {
		leftMotor.set(leftSpeed);
		rightMotor.set(rightSpeed);
	}
	
	public State getState() {
		return state;
	}

	@Override
	public void getState(StateHolder states) {
		states.put("intake", state == State.OPEN ? "open" : "closed");
	}

	@Override
	public void reloadConstants() {
	}

}