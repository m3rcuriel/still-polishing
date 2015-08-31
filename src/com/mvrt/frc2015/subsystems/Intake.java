package com.mvrt.frc2015.subsystems;

import com.mvrt.frc2015.Constants;
import com.mvrt.lib.StateHolder;
import com.mvrt.lib.Subsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Intake extends Subsystem {
	
	private State state;
	
	private DoubleSolenoid intake;
	
	public enum State {
		OPEN, CLOSED;
	}

	public Intake() {
		super("Intake");
		
		intake = new DoubleSolenoid(Constants.kIntakeSolenoidA, Constants.kIntakeSolenoidB);
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