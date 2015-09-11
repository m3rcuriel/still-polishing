package com.mvrt.frc2015.statemachine;

import com.m3rcuriel.controve.api.RobotSetpointsBase;

public class RobotSetpoints extends RobotSetpointsBase {

  public enum IntakeAction {
    NONE, OPEN, CLOSE, PREFER_OPEN, PREFER_CLOSE;
  }

  public IntakeAction intakeAction;

  public void reset() {
    intakeAction = IntakeAction.NONE;
  }
}
