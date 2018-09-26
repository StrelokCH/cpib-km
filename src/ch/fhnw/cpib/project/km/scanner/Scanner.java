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
	private static List<BaseToken> getKeywords(){
		List<BaseToken> ret = new ArrayList<>();
		ret.add(new Bool());
		ret.add(new BoolLiteral(true));
		// TODO add rest
		return ret;
	}
	
	private static List<BaseToken> getSymbols(){
		List<BaseToken> ret = new ArrayList<>();
		ret.add(new Becomes());
		// TODO add rest
		return ret;
	}
	
	private static List<BaseToken> getVarious(){
		List<BaseToken> ret = new ArrayList<>();
		ret.add(new Ignore());
		// TODO add rest
		return ret;
	}

	public static ITokenList scan(CharSequence cs) throws LexicalErrorException {
		// precondition: last character (if it exists) is a newline
		assert cs.length() == 0 || cs.charAt(cs.length() - 1) == '\n';

		ITokenList l = new TokenList();
		
		int start = 0;
		while (start < cs.length()) {
			// try identifiers
			BaseToken identifier = new Identifier("");
			int longestMatch = identifier.match(cs);
			BaseToken matchedToken = null;
			
			if (longestMatch > 0) {
				matchedToken = identifier;
				
				// try keywords (subset of identifiers)
				List<BaseToken> keywords = getKeywords();
				for (BaseToken keyword : keywords) {
					int match = keyword.match(cs);
					
					// each keyword must be a subset of identifier
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
				int match = symbol.match(cs);
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
				int match = var.match(cs);
				if (match > longestMatch) {
					// found keyword with bigger length
					longestMatch = match;
					matchedToken = var;
					break;
				}
			}
			
			if (longestMatch == 0) {
				throw new LexicalErrorException("lexical error at position " + start);
			}
			
			l.add(matchedToken.createToken(cs));
		}

		l.add(new Sentinel());
		return l;
	}
}
