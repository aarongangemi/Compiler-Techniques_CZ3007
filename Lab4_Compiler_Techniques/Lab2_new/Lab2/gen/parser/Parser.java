package parser;

import beaver.*;
import java.util.ArrayList;

/**
 * This class is a LALR parser generated by
 * <a href="http://beaver.sourceforge.net">Beaver</a> v0.9.6.1
 * from the grammar specification "parser.beaver".
 */
public class Parser extends beaver.Parser {
	static public class Terminals {
		static public final short EOF = 0;
		static public final short ID = 1;
		static public final short LBRACKET = 2;
		static public final short LPAREN = 3;
		static public final short MINUS = 4;
		static public final short STRING_LITERAL = 5;
		static public final short INT_LITERAL = 6;
		static public final short TRUE = 7;
		static public final short FALSE = 8;
		static public final short RPAREN = 9;
		static public final short RCURLY = 10;
		static public final short VOID = 11;
		static public final short BOOLEAN = 12;
		static public final short INT = 13;
		static public final short LCURLY = 14;
		static public final short SEMICOLON = 15;
		static public final short RBRACKET = 16;
		static public final short IF = 17;
		static public final short WHILE = 18;
		static public final short BREAK = 19;
		static public final short RETURN = 20;
		static public final short EQL = 21;
		static public final short IMPORT = 22;
		static public final short PUBLIC = 23;
		static public final short COMMA = 24;
		static public final short PLUS = 25;
		static public final short TIMES = 26;
		static public final short DIV = 27;
		static public final short MOD = 28;
		static public final short TYPE = 29;
		static public final short MODULE = 30;
		static public final short ELSE = 31;
		static public final short EQEQ = 32;
		static public final short NEQ = 33;
		static public final short LT = 34;
		static public final short LEQ = 35;
		static public final short GT = 36;
		static public final short GEQ = 37;
	}

	static final ParsingTables PARSING_TABLES = new ParsingTables(
		"U9pDLbbq54KKlTqp2KmWp60SGWWG326P99E4n2oG10dH8AW4GG4t14HnLt311MLn2NBKuwS" +
		"V$kYV$gaH9IAAkr7ZXWieAe8RKGH511TytgwkT9gUdWcBdkFCkVMMUwkwkhjULGDc2wv5Ay" +
		"v18yx6BCp679Yo7TVXUjo0N7b2$i18Z449uWcwEtjrc7#OkW3c9TLDmldETN$65SbqiWTNE" +
		"xfl44mwNXjkSdIxSPkZcuOgd88pS1Ace0QNOYmgCHINuoeCmV4fsUoKxF0KR7uAhY05L#pX" +
		"PYYk0HDrxa9VVf3gsmyNOBelFy7fVvQ77wNuGtD5FhaodrorJwxP9pVP9tTkqhvDdhSwpPd" +
		"xU5HuMNagXNuAwh0aWUyhdj#NGWHJSHfEnppvKtjX78S5k0WBSJ4kmQ8ZlkQnnfbSxujPJU" +
		"b8Gt$SXKnaO0068eHbZBFOsl4jQftODMAtToDA$J8inOsQ7Oo8V8VbcX#77Db1zcOCm#qOW" +
		"JoCnWaeH2rYACM9vCjHZvFHmlPKd8dpC5VMuZBv7bVY6laMjs8cLc0LtyDgt07W7c8bx5yR" +
		"39ZS8OASTJfNRtzMh3tFJAxe5lg3#LopMADUNRPB5pnCdO6Sf3ehwjMPINLMtACB4SbqGrZ" +
		"DVkCLihPYhF4IFhiuQwgSAzfQyrLyajN$6VjlShsvcXHzCrY1$dnV$VzFEUzRTMl22MmefJ" +
		"wEYEzgIXK7#rI0LPdBSoY7Jzps3CvZ52iqdtLQmFFlIAzvh76jpjHpjsxakLNBtRY99tqzy" +
		"usg6kpONYTsZTZjUDv30$a9tBbjjfdZXBZds7mTkGp5XldF89T7Di2z9yGuIZxCtIZ2grjj" +
		"XDcGOa8wCbYJ1kTflOSmzt0BKL4$rYkmnjgM67JJr10pYDb46h4TC1wcpQ5zQANUmknjpDX" +
		"7F0gOcGJVYF48qQr6XN5GRtLPv0OHl8uvKEUAw6VPlfbBxLBQR0ODj5PokkP6abjEEup1Gf" +
		"sBwcktqX$gde$c8#JQjEPUuW4wrZWF4gk9npMtxj1#VE8mDh9$ZXudnlWDsk5QVv$tMZh$7" +
		"ZKZT9yynfkJwBQH7wFzkVGBTPyYFxtddlBr7AVI7wtzEVGBz7WxYKK#$OhPp29tW7wSgD5z" +
		"bz0lqPewefIe9ifSVSkrhISgUl9yjPskUrfBjBlYzObpG8UBxtBbPxhy1Jxz5dlYBTgkqFP" +
		"#5xT3My$wn2xlk2vklvxJbmIVgx6Rg2JsCDckDPiI#nc69nxWylSckLQ79#xqrnbHb#QnP7" +
		"C$Md3yl1JSrgCOhoinXpNGFpaeloZBLdwN0x8Vdf$yvdXVgNQR4$Ejo1TEz3cnLNcVoQUoM" +
		"JwH3VG$5jQUl4kmJkKLuWLvNZR8HqgvIJvKzdrvNLwI5sMZV20lgynwUP9jkpmh7R9Edb6v" +
		"frNx7F6Ql2hlI9UyBM$9czB9p1R5$Q3QRcjqsIa$oIxvqNKdNwjsdnF$Adjv$tyh$oyshVo" +
		"cI#S#RnBMFcUT4YO1dWo6WudSgvjueaoYrZfDQicFfUr1fGjL7AC7pOovV$5y213MEMBoky" +
		"W2#2qJq4WZ#cbhwfofKCO#5igPAwAr4ECyOhJ5jDRtG8KwRmnVnCd5QUiqIXcNqevpeP3NA" +
		"QGjKGWI0Usx4U3Naq5Ovr20C33v7thI7UO=");

	static final Action RETURN6 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 6];
		}
	};

	static final Action RETURN2 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 2];
		}
	};

	static final Action RETURN3 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 3];
		}
	};

	static final Action RETURN9 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 9];
		}
	};

	static final Action RETURN4 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 4];
		}
	};

	static final Action RETURN5 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 5];
		}
	};

	static final Action RETURN7 = new Action() {
		public Symbol reduce(Symbol[] _symbols, int offset) {
			return _symbols[offset + 7];
		}
	};

	// turn off automated error recovery
	@Override
	protected void recoverFromError(Symbol token, TokenStream in) throws java.io.IOException, Exception {
		super.recoverFromError(new Symbol(0), in);
	}

	private final Action[] actions;

	public Parser() {
		super(PARSING_TABLES);
		actions = new Action[] {
			RETURN6,	// [0] Module = MODULE ID LCURLY Imports Declarations RCURLY; returns 'RCURLY' although none is marked
			RETURN2,	// [1] Imports = Import Imports; returns 'Imports' although none is marked
			Action.NONE,  	// [2] Imports = 
			RETURN3,	// [3] Import = IMPORT ID SEMICOLON; returns 'SEMICOLON' although none is marked
			RETURN2,	// [4] Declarations = Declaration Declarations; returns 'Declarations' although none is marked
			Action.NONE,  	// [5] Declarations = 
			Action.RETURN,	// [6] Declaration = FunctionDeclaration
			Action.RETURN,	// [7] Declaration = FieldDeclaration
			Action.RETURN,	// [8] Declaration = TypeDeclaration
			RETURN9,	// [9] FunctionDeclaration = AccSpecifier TypeName ID LPAREN ParameterList RPAREN LCURLY StatementList RCURLY; returns 'RCURLY' although none is marked
			RETURN4,	// [10] FieldDeclaration = AccSpecifier TypeName ID SEMICOLON; returns 'SEMICOLON' although none is marked
			RETURN6,	// [11] TypeDeclaration = AccSpecifier TYPE ID EQL STRING_LITERAL SEMICOLON; returns 'SEMICOLON' although none is marked
			Action.RETURN,	// [12] AccSpecifier = PUBLIC
			Action.NONE,  	// [13] AccSpecifier = 
			Action.RETURN,	// [14] TypeName = PrimitiveType
			Action.RETURN,	// [15] TypeName = ArrayType
			Action.RETURN,	// [16] TypeName = ID
			Action.RETURN,	// [17] PrimitiveType = VOID
			Action.RETURN,	// [18] PrimitiveType = BOOLEAN
			Action.RETURN,	// [19] PrimitiveType = INT
			RETURN3,	// [20] ArrayType = PrimitiveType LBRACKET RBRACKET; returns 'RBRACKET' although none is marked
			RETURN3,	// [21] ArrayType = ArrayType LBRACKET RBRACKET; returns 'RBRACKET' although none is marked
			RETURN3,	// [22] ArrayType = ID LBRACKET RBRACKET; returns 'RBRACKET' although none is marked
			Action.RETURN,	// [23] ParameterList = Parameters
			Action.NONE,  	// [24] ParameterList = 
			RETURN3,	// [25] Parameters = Parameter COMMA Parameters; returns 'Parameters' although none is marked
			Action.RETURN,	// [26] Parameters = Parameter
			RETURN2,	// [27] Parameter = TypeName ID; returns 'ID' although none is marked
			RETURN2,	// [28] StatementList = Statement StatementList; returns 'StatementList' although none is marked
			Action.NONE,  	// [29] StatementList = 
			RETURN3,	// [30] Statement = TypeName ID SEMICOLON; returns 'SEMICOLON' although none is marked
			RETURN3,	// [31] Statement = LCURLY StatementList RCURLY; returns 'RCURLY' although none is marked
			RETURN5,	// [32] Statement = IF LPAREN Expression RPAREN Statement; returns 'Statement' although none is marked
			RETURN7,	// [33] Statement = IF LPAREN Expression RPAREN Statement ELSE Statement; returns 'Statement' although none is marked
			RETURN5,	// [34] Statement = WHILE LPAREN Expression RPAREN Statement; returns 'Statement' although none is marked
			RETURN2,	// [35] Statement = BREAK SEMICOLON; returns 'SEMICOLON' although none is marked
			RETURN3,	// [36] Statement = RETURN Expression SEMICOLON; returns 'SEMICOLON' although none is marked
			RETURN2,	// [37] Statement = RETURN SEMICOLON; returns 'SEMICOLON' although none is marked
			RETURN2,	// [38] Statement = Expression SEMICOLON; returns 'SEMICOLON' although none is marked
			Action.RETURN,	// [39] Expression = Assignment
			Action.RETURN,	// [40] Expression = RightHandExp
			RETURN3,	// [41] Assignment = LeftHandExp EQL Expression; returns 'Expression' although none is marked
			Action.RETURN,	// [42] LeftHandExp = ID
			Action.RETURN,	// [43] LeftHandExp = ArrayAcc
			RETURN4,	// [44] ArrayAcc = ID LBRACKET Expression RBRACKET; returns 'RBRACKET' although none is marked
			Action.RETURN,	// [45] ArrayAcc = ArrayAccTail
			RETURN4,	// [46] ArrayAccTail = ArrayAcc LBRACKET Expression RBRACKET; returns 'RBRACKET' although none is marked
			Action.RETURN,	// [47] RightHandExp = ArithmeticExp
			RETURN3,	// [48] RightHandExp = ArithmeticExp ComparisonOp ArithmeticExp; returns 'ArithmeticExp' although none is marked
			Action.RETURN,	// [49] ComparisonOp = EQEQ
			Action.RETURN,	// [50] ComparisonOp = NEQ
			Action.RETURN,	// [51] ComparisonOp = LT
			Action.RETURN,	// [52] ComparisonOp = LEQ
			Action.RETURN,	// [53] ComparisonOp = GT
			Action.RETURN,	// [54] ComparisonOp = GEQ
			new Action() {	// [55] ArithmeticExp = ArithmeticExp AdditiveOp Term
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3]); return _symbols[offset + 1];
				}
			},
			new Action() {	// [56] ArithmeticExp = Term
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1]); return new Symbol(lst);
				}
			},
			Action.RETURN,	// [57] AdditiveOp = PLUS
			Action.RETURN,	// [58] AdditiveOp = MINUS
			new Action() {	// [59] Term = Term MultiplicativeOp Factor
				public Symbol reduce(Symbol[] _symbols, int offset) {
					((ArrayList) _symbols[offset + 1].value).add(_symbols[offset + 3]); return _symbols[offset + 1];
				}
			},
			new Action() {	// [60] Term = Factor
				public Symbol reduce(Symbol[] _symbols, int offset) {
					ArrayList lst = new ArrayList(); lst.add(_symbols[offset + 1]); return new Symbol(lst);
				}
			},
			Action.RETURN,	// [61] MultiplicativeOp = TIMES
			Action.RETURN,	// [62] MultiplicativeOp = DIV
			Action.RETURN,	// [63] MultiplicativeOp = MOD
			RETURN2,	// [64] Factor = MINUS Factor; returns 'Factor' although none is marked
			Action.RETURN,	// [65] Factor = PrimaryExp
			Action.RETURN,	// [66] PrimaryExp = LeftHandExp
			RETURN4,	// [67] PrimaryExp = ID LPAREN ExpressionList RPAREN; returns 'RPAREN' although none is marked
			RETURN3,	// [68] PrimaryExp = LBRACKET Expressions RBRACKET; returns 'RBRACKET' although none is marked
			Action.RETURN,	// [69] PrimaryExp = STRING_LITERAL
			Action.RETURN,	// [70] PrimaryExp = INT_LITERAL
			Action.RETURN,	// [71] PrimaryExp = TRUE
			Action.RETURN,	// [72] PrimaryExp = FALSE
			RETURN3,	// [73] PrimaryExp = LPAREN Expression RPAREN; returns 'RPAREN' although none is marked
			Action.RETURN,	// [74] ExpressionList = Expressions
			Action.NONE,  	// [75] ExpressionList = 
			RETURN3,	// [76] Expressions = Expression COMMA Expressions; returns 'Expressions' although none is marked
			Action.RETURN	// [77] Expressions = Expression
		};
	}

	protected Symbol invokeReduceAction(int rule_num, int offset) {
		return actions[rule_num].reduce(_symbols, offset);
	}
}
