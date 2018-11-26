package ch.fhnw.cpib.project.km.syntax.abst;

public class InputCmd implements ICommand {

	private IExpression expression;
	
	
	public InputCmd(IExpression expression) {
		super();
		this.expression = expression;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n"
				+ expression.toString(indent + "    \n");
	}

}
