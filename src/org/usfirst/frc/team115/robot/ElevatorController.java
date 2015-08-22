package org.usfirst.frc.team115.robot;

import org.usfirst.frc.team115.lib.Controller;
import org.usfirst.frc.team115.robot.subsystems.Elevator;

public class ElevatorController extends Controller{
	
	private Elevator elevator;

	private static double PRESET_SPEED = 1;

	private double destHeight;
	private double distance;
	private int direction = 0;
	private boolean shouldRamp = true;

	private int curr;

	private static final double PRESET_BOTTOM = 0;
	private static final double PRESET_TOTE_INTAKE = 14;
	private static final double PRESET_BIN_INTAKE = 24;
	private static final double PRESET_TOTE_INTAKETHREE = 41;
	private static final double PRESET_TOP = 56;

	private double[] presets = {PRESET_BOTTOM, PRESET_TOTE_INTAKE, PRESET_BIN_INTAKE,
			PRESET_TOTE_INTAKETHREE, PRESET_TOP
	};

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isOnTarget() {
		// TODO Auto-generated method stub
		return false;
	}

	public ElevatorController(Elevator elevator){
		this.elevator = elevator;
		elevator.setBrake(false);
	}

	public void drive() {
		distance = Math.abs(elevator.getHeight()- destHeight);
		double x = (100 * Math.abs(distance - Math.abs(elevator.getHeight() - destHeight)) / distance);
		double ramp = shouldRamp ? calculateRamp(x) : 1;

		elevator.setElevatorPower(direction * PRESET_SPEED * ramp);

		if(elevator.getHeight() == destHeight){
			disable();
		}
	}

	public void enable(){
		enabled = true;
	}

	public void disable(){
		enabled = false;
	}

	public double calculateRamp(double toTarget){
		return ((1 - 0.70 / (1 + 12000 * Math.exp(-0.115 * toTarget))));
	}

	public void presetUp(boolean up){
		if(up){
			if(curr + 1 > presets.length)
				destHeight = presets[presets.length - 1];
			else
				destHeight = presets[curr + 1];
			direction = 1;
		}
		else{
			if(curr - 1 < 0)
				destHeight = presets[0];
			else
				destHeight = presets[curr - 1];
			direction = -1;
		}
	}

}
