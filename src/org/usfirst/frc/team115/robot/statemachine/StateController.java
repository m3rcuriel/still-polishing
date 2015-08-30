package org.usfirst.frc.team115.robot.statemachine;

import org.usfirst.frc.team115.lib.Routine;
import org.usfirst.frc.team115.lib.StateHolder;
import org.usfirst.frc.team115.lib.StateMachine;
import org.usfirst.frc.team115.robot.HardwareInterface;
import org.usfirst.frc.team115.robot.statemachine.routines.ManualRoutine;

// behaviour manager
public class StateController implements StateMachine {

	private Routine currentRoutine = null;
	private RobotSetpoints setpoints;
	private ManualRoutine manualRoutine = new ManualRoutine();

	private void setNewRoutine(Routine newRoutine) {
		boolean needsCancel = newRoutine != currentRoutine && currentRoutine != null;

		boolean needsReset = newRoutine != currentRoutine && newRoutine != null;
		if (needsCancel) {
			currentRoutine.cancel();
		}
		currentRoutine = newRoutine;
		if (needsReset) {
			currentRoutine.reset();
		}
	}

	public void reset() {
		setNewRoutine(null);
	}

	public StateController() {
		setpoints = new RobotSetpoints();
		setpoints.reset();
	}

	public void update(Commands commands) {
		setpoints.reset();

		if (currentRoutine != null && currentRoutine.isFinished()) {
			setNewRoutine(null);
		}

		if (currentRoutine != null) {
			setpoints = currentRoutine.update(commands, setpoints);
		}

		setpoints = manualRoutine.update(commands, setpoints);

		if (setpoints.intakeAction == RobotSetpoints.IntakeAction.OPEN || setpoints.intakeAction == RobotSetpoints.IntakeAction.PREFER_OPEN) {
			HardwareInterface.kIntake.open();
		} else if (setpoints.intakeAction == RobotSetpoints.IntakeAction.CLOSE || setpoints.intakeAction == RobotSetpoints.IntakeAction.PREFER_CLOSE) {
			HardwareInterface.kIntake.close();
		}
	}

	@Override
	public void getState(StateHolder states) {
		states.put("mode", currentRoutine != null ? currentRoutine.getName() : "---");
	}

	@Override
	public String getName() {
		return "state controller";
	}
}
