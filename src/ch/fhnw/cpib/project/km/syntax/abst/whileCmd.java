package ch.fhnw.cpib.project.km.syntax.abst;

public class whileCmd implements ICommand {

	private IExpression expression;
	private ICommand command;
		
	public whileCmd(IExpression expression, ICommand command) {
		super();
		this.expression = expression;
		this.command = command;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n"
				+ expression.toString(indent + "    \n")
				+ command.toString(indent + "    \n") ;
	}

}
