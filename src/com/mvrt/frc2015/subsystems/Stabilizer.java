package com.mvrt.frc2015.subsystems;

import com.mvrt.frc2015.Constants;
import com.mvrt.frc2015.subsystems.Intake.State;
import com.mvrt.lib.StateHolder;
import com.mvrt.lib.Subsystem;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Stabilizer extends Subsystem {
	
	private State state;
	private DoubleSolenoid stabilizerSolenoid;
	
	private enum State {
		OPEN, CLOSED;
	}
	
	public Stabilizer() {
		super("stabilizer");
		stabilizerSolenoid = new DoubleSolenoid(Constants.kStabilizerSolenoidA, Constants.kStabilizerSolenoidB);
	}
	
	public void open() {
		stabilizerSolenoid.set(DoubleSolenoid.Value.kForward);
		state = State.OPEN;
	}

	public void close() {
		stabilizerSolenoid.set(DoubleSolenoid.Value.kReverse);
		state = State.CLOSED;
	}


	@Override
	public void getState(StateHolder states) {
	      states.put("stabilizer state", state == State.OPEN ? "open" : "closed");
	}

	@Override
	public void reloadConstants() {
	}

}