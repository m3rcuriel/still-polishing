package org.usfirst.frc.team115.lib.misc;

import java.util.Vector;

public class ConstantsBase {
	private static final Vector<Constant> constants = new Vector<Constant>();

	public static class Constant {
		private String name;
		private double value;

		public Constant(String name, double value) {
			this.name = name;
			this.value = value;
			constants.addElement(this);
		}

		public String getName() {
			return name;
		}

		public double getDouble() {
			return value;
		}

		public int getInt() {
			return (int) value;
		}

		public void setVal(double value) {
			this.value = value;
		}

		public String toHtml() {
			String str = "<html>" + this.name + ": " + "<input type='text' value=\"" + this.value + "\" name=\""
					+ this.name + "\"> <br/>";

			return str;
		}

		@Override
		public String toString() {
			return name + ": " + value;
		}
	}
}
