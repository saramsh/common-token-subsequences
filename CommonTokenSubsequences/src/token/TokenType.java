package token;

public enum TokenType {
	BlockComment,

	LineComment,

	WhiteSpace,

	Tab,

	NewLine,

	CloseBrace,

	OpenBrace,

	OpeningCurlyBrace,

	ClosingCurlyBrace,
	
	OpeningSquareBrace,

	ClosingSquareBrace,

	DoubleConstant,

	IntConstant,

	Plus,

	Minus,

	Multiply,

	Divide,

	Point,

	EqualEqual,

	Equal,

	ExclameEqual,

	Greater,

	Less,

	Static,

	Public,

	Private,

	Int,

	Double,

	Void,

	False,

	True,

	Null,

	Return,

	New,

	Class,

	If,

	While,

	Else,
	
	//added by sara

	Semicolon,

	Comma,
	
	Colon,
	
	Ampersand,
	
	OrCharacter,
	
	DoubleCotation,
	
	Cotation,
	
	Exclamation,
	
	//Package,
	
	//Import,

	Identifier;

	/**
	 * Determines if this token is auxiliary
	 * 
	 * @return {@code true} if token is auxiliary, {@code false} otherwise
	 */
	public boolean isAuxiliary() {
		return this == BlockComment || this == LineComment || this == NewLine || this == Tab
				|| this == WhiteSpace;
	}

}
