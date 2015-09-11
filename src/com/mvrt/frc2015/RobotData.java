package com.mvrt.frc2015;


import com.m3rcuriel.controve.retrievable.Retrievable;
import com.m3rcuriel.controve.retrievable.StateHolder;

import edu.wpi.first.wpilibj.Timer;

public class RobotData implements Retrievable {

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
