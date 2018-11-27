package ch.fhnw.cpib.project.km.syntax.abst;

public class CondCmd implements ICommand {

	private IExpression expression;
	private ICommand ifCase;
	private ICommand elseCase;
		
	public CondCmd(IExpression expression, ICommand ifCase, ICommand elseCase) {
		super();
		this.expression = expression;
		this.ifCase = ifCase;
		this.elseCase = elseCase;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n"
				+ expression.toString(indent + "    \n")
				+ ifCase.toString(indent + "    \n")
				+ elseCase.toString(indent + "    \n") ;
	}

}
