package org.usfirst.frc.team115.lib;

import org.usfirst.frc.team115.robot.statemachine.Commands;
import org.usfirst.frc.team115.robot.statemachine.RobotSetpoints;

public abstract class Routine {
	public abstract void reset();

	public abstract RobotSetpoints update(Commands command, RobotSetpoints existing);

	public abstract void cancel();

	public abstract boolean isFinished();

	public abstract String getName();
}
