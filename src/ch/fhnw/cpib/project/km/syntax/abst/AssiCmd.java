package ch.fhnw.cpib.project.km.syntax.abst;

public class AssiCmd implements ICommand{

	private final IExpression expression1;
	private final IExpression expression2;

	public AssiCmd(IExpression expression1, IExpression expression2) {
		super();
		this.expression1 = expression1;
		this.expression2 = expression2;
	}

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n"
				+ expression1.toString(indent + "    \n")
				+ expression2.toString(indent + "    \n");
	}

}
