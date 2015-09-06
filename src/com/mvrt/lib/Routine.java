package com.mvrt.lib;

import com.mvrt.frc2015.statemachine.Commands;
import com.mvrt.frc2015.statemachine.RobotSetpoints;

public abstract class Routine {
  public abstract void reset();

  public abstract RobotSetpoints update(Commands command, RobotSetpoints existing);

  public abstract void cancel();

  public abstract boolean isFinished();

  public abstract String getName();
}
