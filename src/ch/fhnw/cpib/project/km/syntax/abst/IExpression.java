package ch.fhnw.cpib.project.km.syntax.abst;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;

public interface IExpression {
	public String toString(String indent);

	public void addToEnvironment(Environment env, Context context);
}
