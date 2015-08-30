package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.robot.statemachine.Commands;
import org.usfirst.frc.team115.robot.subsystems.Intake;

public class OperatorInterface {
	private Commands commands = new Commands();
	private boolean toggle = false;

	public void reset() {
		commands = new Commands();
	}

	public Commands getCommands() {
		if (toggle
				&& HardwareInterface.kOperatorJoystick
				.getRawButton(Constants.kIntakeToggleButton)) {
			toggle = false;
			commands.intakeRequest = toggle(
					HardwareInterface.kIntake.getState() == Intake.State.CLOSED,
					Commands.IntakeRequest.OPEN, Commands.IntakeRequest.CLOSE);
		} else {
			toggle = true;
		}

		return commands;
	}

	public <T> T toggle(boolean state, T A, T B) {
		return state ? A : B;
	}
}
