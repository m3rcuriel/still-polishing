package com.mvrt.frc2015;

import com.mvrt.lib.StateHolder;
import com.mvrt.lib.StateMachine;

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
