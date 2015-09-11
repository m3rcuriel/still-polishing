package com.mvrt.frc2015.sim;

import com.team254.fakewpilib.SimRobotBase;

import java.util.TimerTask;

public class SimRobot extends SimRobotBase {
  @Override
  public void initSimRobot() {}

  @Override
  public void startRobotSim() {
    System.out.println("starting robot sim!");

    double updateRate = 500.0; // Hz


    class Run extends TimerTask {
      long loop_counter;

      @Override
      public void run() {
        loop_counter++;
      }
    }
    java.util.Timer timer = new java.util.Timer();
    timer.scheduleAtFixedRate(new Run(), 0, (long) ((1.0 / updateRate) * 1000.0));
  }
}
