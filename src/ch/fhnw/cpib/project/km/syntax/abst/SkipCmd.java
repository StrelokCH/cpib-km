package ch.fhnw.cpib.project.km.syntax.abst;

public class SkipCmd implements IExpression {

	@Override
	public String toString(String indent) {
		return indent + "(" + this.getClass().getSimpleName() + ")\n";
	}
}