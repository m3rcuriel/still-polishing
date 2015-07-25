package org.usfirst.frc.team115.lib;

public abstract class Subsystem implements Runnable {

	protected String name;
	protected Controller controller;

	public void useController(Controller c) {
		if (controller != null) {
			controller.disable();
		}
		controller = c;
		if (controller != null) {
			controller.enable();
		}
	}

	public void turnOffControllers() {
		useController(null);
	}

	@Override
	public void run() {
		if (controller != null) {
			controller.run();
		}
	}

	public Subsystem(String name) {
		this.name = name;
	}
}
