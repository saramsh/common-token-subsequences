package Lexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.AnalyzerException;

import token.Token;
import token.TokenType;

public class Lexer {

	
	/** Mapping from type of token to its regular expression */
	private Map<TokenType, String> regEx;

	/** List of tokens as they appear in the input source */
	private List<Token> result;
	
	//added by sara
	private List<String> resultString;
	
	/**
	 * Initializes a newly created {@code Lexer} object
	 */
	public Lexer() {
		regEx = new TreeMap<TokenType, String>();
		launchRegEx();
		result = new ArrayList<Token>();
		//added by sara
		resultString=new ArrayList<String>();
	}

	/**
	 * Performs the tokenization of the input source code.
	 * 
	 * @param source
	 *            string to be analyzed
	 * @throws AnalyzerException
	 *             if lexical error exists in the source
	 * 
	 */
	public void tokenize(String source) throws AnalyzerException {
		int position = 0;
		Token token = null;
		//System.out.println(source);
		do {
			//System.out.println("gettoken"+" "+position);
			token = separateToken(source, position);
			
			if (token != null ) {
				//System.out.println(token.getTokenString()+"---"+token.getTokenString().length());
				//System.out.println("pppppppp");
				position = token.getEnd();
				//edited by sara
				//if added by sara
				if(!token.getTokenType().isAuxiliary())
				{
				result.add(token);
				resultString.add(token.getTokenString());
				}
			}
			//System.out.println(position+"-"+"qqqq"+position);
		} while (token != null && position != source.length());
		if (position != source.length()) {
			throw new AnalyzerException("Lexical error at position # "+ position, position);

		}
	}

	/**
	 * Returns a sequence of tokens
	 * 
	 * @return list of tokens
	 */
	public List<Token> getTokens() {
		return result;
	}
	
	public List<String> getTokensStrings() {
		return resultString;
	}

	/**
	 * Returns a sequence of tokens without types {@code BlockComment},
	 * {@code LineComment} , {@code NewLine}, {@code Tab}, {@code WhiteSpace}
	 * 
	 * @return list of tokens
	 */
	public List<Token> getFilteredTokens() {
		List<Token> filteredResult = new ArrayList<Token>();
		for (Token t : this.result) {
			if (!t.getTokenType().isAuxiliary()) {
				filteredResult.add(t);
			}
		}
		return filteredResult;
	}

	/**
	 * Scans the source from the specific index and returns the first separated
	 * token
	 * 
	 * @param source
	 *            source code to be scanned
	 * @param fromIndex
	 *            the index from which to start the scanning
	 * @return first separated token or {@code null} if no token was found
	 * 
	 */
	private Token separateToken(String source, int fromIndex) {
		//System.out.println("st");
		//System.out.println(source.substring(630, 640));

		//System.out.println(source.charAt(fromIndex));
		//System.out.println(source.charAt(fromIndex+10));
		if (fromIndex < 0 || fromIndex >= source.length()) {
			throw new IllegalArgumentException("Illegal index in the input stream!");
		}
		//System.out.println("mdl");
		for (TokenType tokenType : TokenType.values()) {
			Pattern p = Pattern.compile(".{" + fromIndex + "}" + regEx.get(tokenType),
					Pattern.DOTALL);
			//System.out.println(p.toString());
			Matcher m = p.matcher(source);
			if (m.matches()) {
				//System.out.println("match");
				String lexema = m.group(1);
				//System.out.println("lexema"+" "+lexema+"eeeeeeeee");
				return new Token(fromIndex, fromIndex + lexema.length(), lexema, tokenType);
			}
		}
		//System.out.println("end");
		return null;
	}

	/**
	 * Creates map from token types to its regular expressions
	 * 
	 */
	private void launchRegEx() {
		regEx.put(TokenType.BlockComment, "(/\\*.*?\\*/).*");
		regEx.put(TokenType.LineComment, "(//(.*?)[\r$]?\n).*");
		regEx.put(TokenType.WhiteSpace, "( ).*");
		regEx.put(TokenType.OpenBrace, "(\\().*");
		regEx.put(TokenType.CloseBrace, "(\\)).*");
		regEx.put(TokenType.Semicolon, "(;).*");
		regEx.put(TokenType.Colon, "(:).*");
		regEx.put(TokenType.Comma, "(,).*");
		regEx.put(TokenType.OpeningCurlyBrace, "(\\{).*");
		regEx.put(TokenType.ClosingCurlyBrace, "(\\}).*");
		regEx.put(TokenType.OpeningSquareBrace, "(\\[).*");
		regEx.put(TokenType.ClosingSquareBrace, "(\\]).*");
		regEx.put(TokenType.DoubleConstantandWord, "\\b(\\d{1,100}\\.\\d{1,100}[a-zA-Z]{1})\\b.*");
		regEx.put(TokenType.IntConstantandWord, "\\b(\\d{1,100}[a-zA-Z]{1})\\b.*");
		regEx.put(TokenType.DoubleConstant, "\\b(\\d{1,100}\\.\\d{1,100})\\b.*");
		regEx.put(TokenType.IntConstant, "\\b(\\d{1,100})\\b.*");
		regEx.put(TokenType.Void, "\\b(void)\\b.*");
		regEx.put(TokenType.Int, "\\b(int)\\b.*");
		regEx.put(TokenType.Double, "\\b(int|double)\\b.*");
		regEx.put(TokenType.Tab, "(\\t).*");
		//regEx.put(TokenType.NewLine, "(\\n).*");
		//edited by sara
		regEx.put(TokenType.NewLine, "([\r\n|\r|\n]).*");
		regEx.put(TokenType.Ampersand, "(\\&).*");
		regEx.put(TokenType.OrCharacter, "(\\|).*");
		regEx.put(TokenType.DoubleCotation, "(\").*");
		regEx.put(TokenType.Cotation, "(\').*");
		regEx.put(TokenType.Exclamation, "(\\!).*");
		regEx.put(TokenType.QuestionMark, "(\\?).*");
		regEx.put(TokenType.Percent, "(\\%).*");
		regEx.put(TokenType.Dollar, "(\\$).*");
		regEx.put(TokenType.Sharp, "(\\#).*");
		regEx.put(TokenType.SpecialCharacter1, "(\\~).*");
		regEx.put(TokenType.SpecialCharacter2, "(\\^).*");
		regEx.put(TokenType.AtSign, "(\\@).*");
		regEx.put(TokenType.Public, "\\b(public)\\b.*");
		regEx.put(TokenType.Private, "\\b(private)\\b.*");
		regEx.put(TokenType.False, "\\b(false)\\b.*");
		regEx.put(TokenType.True, "\\b(true)\\b.*");
		regEx.put(TokenType.Null, "\\b(null)\\b.*");
		regEx.put(TokenType.Return, "\\b(return)\\b.*");
		regEx.put(TokenType.New, "\\b(new)\\b.*");
		regEx.put(TokenType.Class, "\\b(class)\\b.*");
		regEx.put(TokenType.If, "\\b(if)\\b.*");
		regEx.put(TokenType.Else, "\\b(else)\\b.*");
		regEx.put(TokenType.While, "\\b(while)\\b.*");
		regEx.put(TokenType.Static, "\\b(static)\\b.*");
		regEx.put(TokenType.Point, "(\\.).*");
		regEx.put(TokenType.Plus, "(\\+{1}).*");
		regEx.put(TokenType.Minus, "(\\-{1}).*");
		regEx.put(TokenType.Multiply, "(\\*).*");
		regEx.put(TokenType.Divide, "(/).*");
		regEx.put(TokenType.EqualEqual, "(==).*");
		regEx.put(TokenType.Equal, "(=).*");
		regEx.put(TokenType.ExclameEqual, "(\\!=).*");
		regEx.put(TokenType.Greater, "(>).*");
		regEx.put(TokenType.Less, "(<).*");
		//regEx.put(TokenType.Package, "\\b(package)\\b.*");
		//regEx.put(TokenType.Import, "\\b(import)\\b.*");
		regEx.put(TokenType.Identifier, "\\b([a-zA-Z_]{1}[0-9a-zA-Z_]{0,1000})\\b.*");
	}

}
