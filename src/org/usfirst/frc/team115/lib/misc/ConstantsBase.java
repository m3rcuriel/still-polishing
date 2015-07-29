package org.usfirst.frc.team115.lib.misc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Scanner;
import java.util.Vector;

public class ConstantsBase {
	private static final String DEFAULT_CONSTANTS_FILE = "constants.txt";
	private static final Vector<Constant> constants = new Vector<Constant>();

	public static void readConstantsFromFile() {
		String file = getFile(DEFAULT_CONSTANTS_FILE);
		if (file.length() < 1) {
			System.err.println("Not overriding constants");
		}

		String[] lines = file.split("\n");

		for (String line : lines) {
			String[] keyvalue = line.split("=");
			if (keyvalue.length != 2) {
				System.out.println(
						"Error: Invalid constants file line: " + (keyvalue.length == 0 ? "(empty line)" : line));
				continue;
			}
			boolean found = false;
			for (int j = 0; j < constants.size(); j++) {
				Constant constant = constants.get(j);
				if (constant.getName().equals(keyvalue[0])) {
					System.out.println("Setting " + constant.getName() + " to " + Double.parseDouble(keyvalue[1]));
					constant.setVal(Double.parseDouble(keyvalue[1]));
					found = true;
					break;
				}
			}

			if (!found) {
				System.err.println("Error: the constant doesn't exist: " + line);
			}
		}
	}

	public static void dumpConstantsToFile() {
		try {
			dumpConstantsToFile(DEFAULT_CONSTANTS_FILE);
		} catch (FileNotFoundException e) {
			System.err.println("The default constants file has been locked, or is a directory");
			e.printStackTrace();
		}
	}

	public static void dumpConstantsToFile(String filename) throws FileNotFoundException {
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"))) {
			for(Constant constant : constants) {
				String dumpText = constant.toString();
				dumpText.replaceAll(": ", "=");

			}
		} catch (UnsupportedEncodingException e) {
			System.err.println("This should never happen!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("File dump failed with IOException!");
			e.printStackTrace();
		}

	}

	public static String getFile(String filename) {
		String content = "";
		try (Scanner fileSc = new Scanner(new File(filename)).useDelimiter("\\Z")) {
			content = fileSc.next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return content;
	}

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
