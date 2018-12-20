package ch.fhnw.cpib.project.km.analysis;

public class Context {
	public final SymbolTable symbolTable;
	
	public Context(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}
	
	public Context clone() {
		return new Context(symbolTable.clone());
	}
	
}
