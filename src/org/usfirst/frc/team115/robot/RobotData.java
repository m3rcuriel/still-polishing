package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.StateHolder;
import org.usfirst.frc.team115.lib.StateMachine;

import edu.wpi.first.wpilibj.Timer;

public class RobotData implements StateMachine {

	@Override
	public void getState(StateHolder states) {
		states.put("voltage", HardwareInterface.kPDP.getVoltage());
		states.put("robotTime", Timer.getFPGATimestamp());
	}

	@Override
	public String getName() {
		return "robot";
	}

}
