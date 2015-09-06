package com.mvrt.frc2015;

import com.mvrt.frc2015.statemachine.Commands;
import com.mvrt.frc2015.subsystems.Intake;

public class OperatorInterface {
  private Commands commands = new Commands();
  private boolean toggle = false;

  public void reset() {
    commands = new Commands();
  }

  public Commands getCommands() {
    if (toggle && HardwareInterface.kOperatorJoystick.getRawButton(Constants.kIntakeToggleButton)) {
      toggle = false;
      commands.intakeRequest =
          toggle(HardwareInterface.kIntake.getState() == Intake.State.CLOSED,
          Commands.IntakeRequest.OPEN, Commands.IntakeRequest.CLOSE);
    } else {
      toggle = true;
    }

    return commands;
  }

  public <T> T toggle(boolean state, T state1, T state2) {
    return state ? state1 : state2;
  }
}
