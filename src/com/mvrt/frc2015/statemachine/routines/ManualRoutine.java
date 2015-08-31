package com.mvrt.frc2015.statemachine.routines;

import com.mvrt.frc2015.statemachine.Commands;
import com.mvrt.frc2015.statemachine.RobotSetpoints;
import com.mvrt.lib.Routine;

public class ManualRoutine extends Routine {

	@Override
	public void reset() {
		// nothing to do here
	}

	@Override
	public RobotSetpoints update(Commands commands, RobotSetpoints existing) {
		if (existing.intakeAction == RobotSetpoints.IntakeAction.PREFER_CLOSE) {
			existing.intakeAction = RobotSetpoints.IntakeAction.CLOSE;
			if (commands.intakeRequest == Commands.IntakeRequest.OPEN) {
				existing.intakeAction = RobotSetpoints.IntakeAction.OPEN;
			}
		} else if (existing.intakeAction == RobotSetpoints.IntakeAction.PREFER_OPEN) {
			existing.intakeAction = RobotSetpoints.IntakeAction.OPEN;
			if (commands.intakeRequest == Commands.IntakeRequest.CLOSE) {
				existing.intakeAction = RobotSetpoints.IntakeAction.CLOSE;
			}
		} else if (existing.intakeAction == RobotSetpoints.IntakeAction.NONE) {
			if (commands.intakeRequest == Commands.IntakeRequest.OPEN) {
				existing.intakeAction = RobotSetpoints.IntakeAction.OPEN;
			} else if (commands.intakeRequest == Commands.IntakeRequest.CLOSE) {
				existing.intakeAction = RobotSetpoints.IntakeAction.CLOSE;
			}
		}

		return existing;
	}

	@Override
	public void cancel() {
		// nothing to do here
	}

	@Override
	public boolean isFinished() {
		// routine never finishes
		return false;
	}

	@Override
	public String getName() {
		return "Manual";
	}

}
