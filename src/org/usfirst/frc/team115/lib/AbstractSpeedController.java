package org.usfirst.frc.team115.lib;

import org.usfirst.frc.team115.robot.HardwareInterface;

import edu.wpi.first.wpilibj.SpeedController;

public class AbstractSpeedController implements SpeedController {
	private SpeedController[] controllers;
	private int[] pdpSlots;
	
	private boolean inverted;
	
	public AbstractSpeedController(SpeedController controller, int pdpSlot) {
		controllers = new SpeedController[]{controller};
		pdpSlots = new int[]{pdpSlot};
	}
	
	public AbstractSpeedController(SpeedController controller, int[] pdpSlots) {
		controllers = new SpeedController[]{controller};
		this.pdpSlots = pdpSlots;
	}
	
	public AbstractSpeedController(SpeedController[] controllers, int[] pdpSlots) {
		if (controllers.length != pdpSlots.length) {
			throw new IllegalArgumentException("Length of controllers array and PDP array must match!");
		}
		this.controllers = controllers;
		this.pdpSlots = pdpSlots;
	}
	
	public void setInverted(boolean inverted) {
		this.inverted = inverted;
	}
	
	private double sign() {
		return (isInverted() ? -1.0 : 1.0);
	}
	
	public boolean isInverted() {
		return inverted;
	}
	
	public double getCurrent() {
		double current = 0.0;
		for (int slot : pdpSlots) {
			current += HardwareInterface.kPDP.getCurrent(slot);
		}
		return current;
	}
	
	public double getSignedCurrent() {
		return getCurrent() * Math.signum(get() + sign());
	}

	@Override
	public void pidWrite(double output) {
		for (SpeedController controller : controllers) {
			controller.pidWrite(output * sign());
		}
	}

	@Override
	public double get() {
		return controllers[0].get() * sign();
	}

	@Override
	public void set(double speed, byte syncGroup) {
		for (SpeedController controller : controllers) {
			controller.set(speed * sign(), syncGroup);
		}
	}

	@Override
	public void set(double speed) {
		for (SpeedController controller : controllers) {
			controller.set(speed * sign());
		}
	}

	@Override
	public void disable() {
		for (SpeedController controller : controllers) {
			controller.disable();
		}
	}
	
}
