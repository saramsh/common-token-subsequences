package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringTokenizer;

import Lexer.Lexer;
import exceptions.AnalyzerException;
import token.Token;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import token.TokenType;

import com.googlecode.concurrenttrees.common.PrettyPrinter;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharSequenceNodeFactory;
import com.googlecode.concurrenttrees.solver.LCSubstringSolver;
public class CommonTokenSubsequence {

	public static void main(String[] args) throws AnalyzerException  {
		// TODO Auto-generated method stub
			try {
		  LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
			String content = new String(Files.readAllBytes(Paths.get("bin\\test.txt")));
			StringTokenizer defaultTokenizer = new StringTokenizer(content);
	         Lexer lx=new Lexer();
	         lx.tokenize(content);
	         List <Token> result=lx.getTokens();
	         solver.add(result.toString());
	         String content2 = new String(Files.readAllBytes(Paths.get("bin\\test2.txt")));
	         String content3 = new String(Files.readAllBytes(Paths.get("bin\\test3.txt")));
	         Lexer lx2=new Lexer();
	         lx2.tokenize(content2);
	         List <Token> result2=lx2.getTokens();
	         solver.add(result2.toString());
	        Lexer lx3=new Lexer();
	         lx3.tokenize(content3);
	         List <String> result3=lx3.getTokensStrings();         
	         solver.add(result3.toString());
	         String longestCommonSubstring = solver.getLongestCommonSubstring().toString();
	         System.out.println(longestCommonSubstring);
	         for(int i=0;i<result3.size();i++)
	       
	        {
	        	 
	          //  System.out.println(result3.get(i).getTokenString());
	        }
		  //  System.out.println(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		finally {
		   
		}

	}

}
