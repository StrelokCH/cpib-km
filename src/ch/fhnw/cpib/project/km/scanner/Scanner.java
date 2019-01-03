package ch.fhnw.cpib.project.km.scanner;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.project.km.exceptions.LexicalErrorException;
import ch.fhnw.cpib.project.km.token.BaseToken;
import ch.fhnw.cpib.project.km.token.ITokenList;
import ch.fhnw.cpib.project.km.token.TokenList;
import ch.fhnw.cpib.project.km.token.keywords.*;
import ch.fhnw.cpib.project.km.token.symbols.*;
import ch.fhnw.cpib.project.km.token.various.*;

public class Scanner {
	private static List<BaseToken> getKeywords() {
		List<BaseToken> ret = new ArrayList<>();
		ret.add(new Bool());
		ret.add(new BoolLiteral(true));
		ret.add(new Call());
		ret.add(new Const());
		ret.add(new DebugIn());
		ret.add(new DebugOut());
		ret.add(new DivisionOperator());
		ret.add(new Do());
		ret.add(new Else());
		ret.add(new EndFunction());
		ret.add(new EndIf());
		ret.add(new EndProcedure());
		ret.add(new EndProgram());
		ret.add(new EndWhile());
		ret.add(new FlowmodeIn());
		ret.add(new FlowmodeInOut());
		ret.add(new FlowmodeOut());
		ret.add(new Function());
		ret.add(new Global());
		ret.add(new If());
		ret.add(new Init());
		ret.add(new Int32());
		ret.add(new Int64());
		ret.add(new Local());
		ret.add(new MechmodeCopy());
		ret.add(new MechmodeReference());
		ret.add(new ModuloOperator());
		ret.add(new NotOperator());
		ret.add(new Procedure());
		ret.add(new Program());
		ret.add(new Returns());
		ret.add(new Skip());
		ret.add(new Then());
		ret.add(new Var());
		ret.add(new While());
		return ret;
	}

	private static List<BaseToken> getSymbols() {
		List<BaseToken> ret = new ArrayList<>();
		ret.add(new Becomes());
		ret.add(new CAndOperator());
		ret.add(new Colon());
		ret.add(new Comma());
		ret.add(new COrOperator());
		ret.add(new EqualsOperator());
		ret.add(new GreaterEqualsOperator());
		ret.add(new GreaterThanOperator());
		ret.add(new LeftParenthesis());
		ret.add(new LowerEqualsOperator());
		ret.add(new LowerThanOperator());
		ret.add(new MinusOperator());
		ret.add(new NotEqualsOperator());
		ret.add(new PlusOperator());
		ret.add(new RightParenthesis());
		ret.add(new Semicolon());
		ret.add(new TimesOperator());
		return ret;
	}

	private static List<BaseToken> getVarious() {
		List<BaseToken> ret = new ArrayList<>();
		// don't add Identifier/Sentinel here, as it is a special case
		ret.add(new Ignore());
		ret.add(new Comment());
		ret.add(new Int32Literal(0));
		ret.add(new Int64Literal(0));
		return ret;
	}

	public static ITokenList scan(CharSequence cs) throws LexicalErrorException {
		// precondition: last character (if it exists) is a newline
		assert cs.length() == 0 || cs.charAt(cs.length() - 1) == '\n';

		ITokenList l = new TokenList();

		int start = 0;
		while (start < cs.length()) {
			
			CharSequence currentCharSequence = cs.subSequence(start, cs.length());
			
			// try identifiers
			BaseToken identifier = new Identifier("");
			int longestMatch = identifier.match(currentCharSequence);
			BaseToken matchedToken = null;

			if (longestMatch > 0) {
				matchedToken = identifier;

				// try keywords (subset of identifiers)
				List<BaseToken> keywords = getKeywords();
				for (BaseToken keyword : keywords) {
					int match = keyword.match(currentCharSequence);

					// each keyword must be a subset of identifier
					if (match > longestMatch) {
						assert false;
					}
					assert match <= longestMatch;

					if (match == longestMatch) {
						// found keyword with same length
						longestMatch = match;
						matchedToken = keyword;
						break;
					}
				}
			}

			// try symbols
			List<BaseToken> symbols = getSymbols();
			for (BaseToken symbol : symbols) {
				int match = symbol.match(currentCharSequence);
				if (match > longestMatch) {
					// found keyword with bigger length
					longestMatch = match;
					matchedToken = symbol;
					break;
				}
			}

			// try various
			List<BaseToken> various = getVarious();
			for (BaseToken var : various) {
				int match = var.match(currentCharSequence);
				if (match > longestMatch) {
					// found keyword with bigger length
					longestMatch = match;
					matchedToken = var;
					break;
				}
			}

			if (longestMatch == 0) {
				// no match
				throw new LexicalErrorException("lexical error at position " + start);
			}

			if (!(matchedToken instanceof Ignore) && !(matchedToken instanceof Comment)) {
				l.add(matchedToken.createToken(currentCharSequence));
			}
			start += longestMatch;
		}

		l.add(new Sentinel());
		return l;
	}
}
