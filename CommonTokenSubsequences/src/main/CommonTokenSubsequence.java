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

public class CommonTokenSubsequence {

	public static void main(String[] args) throws AnalyzerException  {
		// TODO Auto-generated method stub
			try {
			String content = new String(Files.readAllBytes(Paths.get("bin\\test.txt")));
			StringTokenizer defaultTokenizer = new StringTokenizer(content);
	         Lexer lx=new Lexer();
	         lx.tokenize(content);
	         List <Token> result=lx.getTokens();

	         for(int i=0;i<result.size();i++)
	       
	        {
	        	 
	            System.out.println(result.get(i).getTokenString());
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
