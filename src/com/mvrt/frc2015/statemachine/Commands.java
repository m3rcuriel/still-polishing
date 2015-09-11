package com.mvrt.frc2015.statemachine;

import com.m3rcuriel.controve.api.CommandsBase;

public class Commands extends CommandsBase {
  public enum IntakeRequest {
    NONE, OPEN, CLOSE;
  }

  public IntakeRequest intakeRequest;
}
