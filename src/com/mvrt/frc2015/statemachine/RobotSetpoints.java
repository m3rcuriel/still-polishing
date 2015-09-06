package com.mvrt.frc2015.statemachine;

public class RobotSetpoints {

  public enum IntakeAction {
    NONE, OPEN, CLOSE, PREFER_OPEN, PREFER_CLOSE;
  }

  public IntakeAction intakeAction;

  public void reset() {
    intakeAction = IntakeAction.NONE;
  }
}
