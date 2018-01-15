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
	         List <String> result=lx.getTokensStrings();
	         String str=result.toString();	       
	         str=str.replace("[", "");
	         str=str.replace("]", "");
	         str=str.replace(",", "");
	         solver.add(str);
	         String content2 = new String(Files.readAllBytes(Paths.get("bin\\test2.txt")));
	         String content3 = new String(Files.readAllBytes(Paths.get("bin\\test3.txt")));
	         Lexer lx2=new Lexer();
	         lx2.tokenize(content2);
	         List <String> result2=lx2.getTokensStrings();
	         String str2=result2.toString();	       
	         str2=str2.replace("[", "");
	         str2=str2.replace("]", "");
	         str2=str2.replace(",", "");
	         solver.add(str2);
	        Lexer lx3=new Lexer();
	         lx3.tokenize(content3);
	         List <String> result3=lx3.getTokensStrings();        
	         String str3=result3.toString();	       
	         str3=str3.replace("[", "");
	         str3=str3.replace("]", "");
	         str3=str3.replace(",", "");
	         solver.add(str3);
	         List<String> LongestCommonSubstrings=solver.getLongestCommonSubstrings(solver.getLongestCommonSubstring().toString());
	         System.out.println(LongestCommonSubstrings.size());
	         for(int i=0;i<LongestCommonSubstrings.size();i++)
	       
	        {
	        	 
	           System.out.println(LongestCommonSubstrings.get(i));
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
