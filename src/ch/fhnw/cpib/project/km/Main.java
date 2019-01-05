package ch.fhnw.cpib.project.km;

import java.util.HashMap;
import java.util.Map;

import ch.fhnw.cpib.project.km.analysis.StaticAnalyser;
import ch.fhnw.cpib.project.km.code.IMLTestPrograms;
import ch.fhnw.cpib.project.km.parser.Parser;
import ch.fhnw.cpib.project.km.scanner.Scanner;
import ch.fhnw.cpib.project.km.syntax.abst.Program;
import ch.fhnw.cpib.project.km.syntax.concrete.IProgram;
import ch.fhnw.cpib.project.km.synthesis.CodeGenerator;
import ch.fhnw.cpib.project.km.token.ITokenList;
import ch.fhnw.cpib.project.km.vm.ICodeArray;
import ch.fhnw.cpib.project.km.vm.VirtualMachine;
import ch.fhnw.cpib.project.km.vm.IVirtualMachine.ExecutionError;
import ch.fhnw.cpib.project.km.vm.InputUtility;

public class Main {

	// settings
	private static int storeSize = 2048;
	private static boolean outputCodeArray = true;

	/**
	 * Runs a program which can be selected.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// select program
		String program = ChooseProgram();
		if (program.isEmpty()) {
			return;
		}

		// compile & run it
		try {
			ITokenList tokens = Scanner.scan(program);
			IProgram concreteProgram = new Parser(tokens).parse();
			Program abstProgram = concreteProgram.toAbsSyn();
			StaticAnalyser analyser = new StaticAnalyser(abstProgram);
			analyser.check();
			ICodeArray codeArray = new CodeGenerator(analyser).generate();

			System.out.println("Build successful.");
			if (outputCodeArray) {
				System.out.println(codeArray.toString());
			}

			codeArray.resize();
			new VirtualMachine(codeArray, storeSize);

			Thread.sleep(10);
			System.out.println();
		} catch (Exception e) {
			// Should not happen
			e.printStackTrace();
		}

		System.out.println("Program ended.");
	}

	/**
	 * Asks the user to choose a program.
	 * 
	 * @return The program code of the chosen program or an empty string (exit).
	 */
	private static String ChooseProgram() {
		PrintMenu();
		return QuerySelection();
	}

	private static String QuerySelection() {
		int selection;
		do {
			System.out.println("Please enter a number and press enter:");
			try {
				selection = InputUtility.readInt();
			} catch (ExecutionError e) {
				// invalid input
				selection = -1;
			}
		} while (!programs.containsKey(selection));

		String code = programs.get(selection);
		System.out.println("You selected " + selection + ".");
		System.out.println("The code is as follows:");
		System.out.println(code);
		return code;
	}

	private static void PrintMenu() {
		for (int i = 1; true; i++) {
			if (!descriptions.containsKey(i)) {
				break;
			}
			System.out.println(i + ": " + descriptions.get(i));
		}

		System.out.println(0 + ": " + descriptions.get(0));
	}

	/**
	 * Available Programs
	 */
	private static Map<Integer, String> programs = GetPrograms();
	private static Map<Integer, String> descriptions = GetDescriptions();

	/**
	 * Mapping from selection to program code.
	 * 
	 * @return
	 */
	private static Map<Integer, String> GetPrograms() {
		HashMap<Integer, String> ret = new HashMap<>();
		int i = 1;
		ret.put(i++, IMLTestPrograms.IntDiv64);
		ret.put(i++, IMLTestPrograms.IntDiv);
		ret.put(i++, IMLTestPrograms.Assoc);
		ret.put(i++, IMLTestPrograms.GlobalInits1);
		ret.put(i++, IMLTestPrograms.GlobalInits2);
		ret.put(i++, IMLTestPrograms.GlobalInits3);
		ret.put(i++, IMLTestPrograms.Globals);
		ret.put(i++, IMLTestPrograms.ParameterPassingInCopy);
		ret.put(i++, IMLTestPrograms.ParameterPassingInRef);
		ret.put(i++, IMLTestPrograms.EuclidExtendedNat);
		ret.put(i++, IMLTestPrograms.Scopes);
		ret.put(i++, IMLTestPrograms.Overloading);
		ret.put(i++, IMLTestPrograms.ToInt32Clamp);
		ret.put(i++, IMLTestPrograms.clamp);
		ret.put(i++, IMLTestPrograms.cut);
		ret.put(i++, IMLTestPrograms.cutUnsigned);
		ret.put(i++, IMLTestPrograms.operationOnInt64);
		ret.put(i++, IMLTestPrograms.faculty);
		ret.put(0, ""); // exit
		return ret;
	}

	/**
	 * Mapping from selection to program description.
	 * 
	 * @return
	 */
	private static Map<Integer, String> GetDescriptions() {

		HashMap<Integer, String> ret = new HashMap<>();
		int i = 1;
		ret.put(i++, "IntDiv64");
		ret.put(i++, "IntDiv");
		ret.put(i++, "Assoc");
		ret.put(i++, "GlobalInits1");
		ret.put(i++, "GlobalInits2");
		ret.put(i++, "GlobalInits3");
		ret.put(i++, "Globals");
		ret.put(i++, "ParameterPassingInCopy");
		ret.put(i++, "ParameterPassingInRef");
		ret.put(i++, "EuclidExtendedNat");
		ret.put(i++, "Scopes");
		ret.put(i++, "Overloading");
		ret.put(i++, "ToInt32Clamp");
		ret.put(i++, "Clamp");
		ret.put(i++, "Cut");
		ret.put(i++, "CutUnsigned");
		ret.put(i++, "OperationOnInt64");
		ret.put(i++, "Faculty");
		ret.put(0, "Exit"); // exit
		return ret;
	}

}
