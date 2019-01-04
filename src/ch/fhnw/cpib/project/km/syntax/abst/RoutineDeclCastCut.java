package ch.fhnw.cpib.project.km.syntax.abst;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.analysis.Context;
import ch.fhnw.cpib.project.km.analysis.Environment;
import ch.fhnw.cpib.project.km.exceptions.ScopeCheckingException;
import ch.fhnw.cpib.project.km.token.keywords.Const;
import ch.fhnw.cpib.project.km.token.keywords.FlowmodeIn;
import ch.fhnw.cpib.project.km.token.keywords.Int64;
import ch.fhnw.cpib.project.km.token.keywords.MechmodeReference;
import ch.fhnw.cpib.project.km.token.various.Identifier;

/**
 * Predefined class to handle calls to standard cast functions
 * @author Janik
 *
 */
public class RoutineDeclCastCut extends RoutineDecl {
	
	static Identifier createIdentifier() {
		return new Identifier("toInt32Cut");
	}
	
	static List<FullIdentifier> createParamList() {
		List<FullIdentifier> ret = new ArrayList<FullIdentifier>();
		// in ref const x64:int64
		ret.add(new FullIdentifier(new FlowmodeIn(),new MechmodeReference(), new Const(), new Identifier("x64"), new Int64()));
		return ret;
	}
	
	static StoDecl createReturn() {
		// const x32:int32
		return new StoDecl(new FullIdentifier(null,null, new Const(), new Identifier("x32"), new Int64()));
	}
	
	static List<FullIdentifier> createGlobImps() {
		// none
		return new ArrayList<FullIdentifier>();
	}
	
	static List<StoDecl> createCpsStoDecl() {
		// none
		return new ArrayList<StoDecl>();
	}
	
	static List<ICommand> createCpsCmd() {
		// none
		return new ArrayList<ICommand>();
	}

	public RoutineDeclCastCut() {
		super(createIdentifier(), createParamList(), createReturn(), createGlobImps(), createCpsStoDecl(), createCpsCmd());
	}
	
	@Override
	public void addToEnvironment(Environment env, Context context) throws ScopeCheckingException {
		// not needed
	}

}
