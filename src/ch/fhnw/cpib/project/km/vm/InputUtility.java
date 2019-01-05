// Virtual Machine Java 2015, V01
// Edgar F.A. Lederer, FHNW and Uni Basel, 2015

package ch.fhnw.cpib.project.km.vm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.fhnw.cpib.project.km.vm.IVirtualMachine.ExecutionError;

public class InputUtility {

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static boolean readBool() throws ExecutionError {
		String s;
		try {
			s = reader.readLine();
		} catch (IOException e) {
			throw new ExecutionError("Input failed.");
		}
		if (s.equals("false")) {
			return false;
		} else if (s.equals("true")) {
			return true;
		} else {
			throw new ExecutionError("Not a boolean.");
		}
	}

	public static int readInt() throws ExecutionError {
		String s;
		try {
			s = reader.readLine();
		} catch (IOException e) {
			throw new ExecutionError("Input failed.");
		}
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			throw new ExecutionError("Not an integer.");
		}
	}

	public static long readLong() throws ExecutionError {
		String s;
		try {
			s = reader.readLine();
		} catch (IOException e) {
			throw new ExecutionError("Input failed.");
		}
		try {
			return Long.parseLong(s);
		} catch (NumberFormatException e) {
			throw new ExecutionError("Not an long.");
		}
	}
}
