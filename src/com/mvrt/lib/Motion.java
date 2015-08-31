package com.mvrt.lib;

public class Motion {

	public double leftDistance;
	public double rightDistance;
	public double leftVelocity;
	public double rightVelocity;
	public double heading;
	public double headingVelocity;

	public Motion(double leftDistance, double rightDistance, double leftVelocity, double rightVelocity, double heading,
			double headingVelocity) {
		this.leftDistance = leftDistance;
		this.rightDistance = rightDistance;
		this.leftVelocity = leftVelocity;
		this.rightVelocity = rightVelocity;
		this.heading = heading;
		this.headingVelocity = headingVelocity;
	}

	public void reset(double leftDistance, double rightDistance, double leftVelocity, double rightVelocity, double heading,
			double headingVelocity) {
		this.leftDistance = leftDistance;
		this.rightDistance = rightDistance;
		this.leftVelocity = leftVelocity;
		this.rightVelocity = rightVelocity;
		this.heading = heading;
		this.headingVelocity = headingVelocity;
	}

	/**
	 * @return the leftDistance
	 */
	public double getLeftDistance() {
		return leftDistance;
	}

	/**
	 * @return the rightDistance
	 */
	public double getRightDistance() {
		return rightDistance;
	}

	/**
	 * @return the leftVelocity
	 */
	public double getLeftVelocity() {
		return leftVelocity;
	}

	/**
	 * @return the rightVelocity
	 */
	public double getRightVelocity() {
		return rightVelocity;
	}

	/**
	 * @return the heading
	 */
	public double getHeading() {
		return heading;
	}

	/**
	 * @return the headingVelocity
	 */
	public double getHeadingVelocity() {
		return headingVelocity;
	}
}
