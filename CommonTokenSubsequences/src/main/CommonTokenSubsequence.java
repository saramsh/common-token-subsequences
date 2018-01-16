package main;

import java.io.File;
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
		List<String> listOfFilesContent=new ArrayList<String>();
		List<String> listOfTokenizedContent=new ArrayList<String>();
			try {
				//Read content of files and add them to listOfFilesContent
				File folder = new File("bin\\2-testcase");
				File[] listOfFiles = folder.listFiles();
				
				for (File file : listOfFiles) {
					System.out.println(file.getName());
				    if (file.isFile()) {
				    	listOfFilesContent.add(new String(Files.readAllBytes(Paths.get("bin\\2-testcase\\"+file.getName()))));						
				    }
				}				
		  LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
			//String content = new String(Files.readAllBytes(Paths.get("bin\\test.txt")));
			StringTokenizer defaultTokenizer;
			//tokenize fileContent
			int kk=0;
			for(String fileContent:listOfFilesContent)
			{
				defaultTokenizer= new StringTokenizer(fileContent);
				//System.out.println("aaaa");
			//	System.out.println(fileContent);
				 Lexer lx=new Lexer();
		         lx.tokenize(fileContent);
		         System.out.println("bbbbb");
		         List<String> listOfTokenStrings=lx.getTokensStrings();
		         System.out.println("ccccc");
		         String tokenizedContent=listOfTokenStrings.toString();			         
		         tokenizedContent=tokenizedContent.replace("[", "");
		         tokenizedContent=tokenizedContent.replace("]", "");
		         tokenizedContent=tokenizedContent.replace(",", "");
		         listOfTokenizedContent.add(tokenizedContent);
		         solver.add(tokenizedContent);	     
		         System.out.println(kk);
		         kk++;
			}

	         
	         //String content2 = new String(Files.readAllBytes(Paths.get("bin\\test2.txt")));
	         //String content3 = new String(Files.readAllBytes(Paths.get("bin\\test3.txt")));
	         //Lexer lx2=new Lexer();
	         //lx2.tokenize(content2);
	         //List <String> result2=lx2.getTokensStrings();
	         //String str2=result2.toString();	       
	         //str2=str2.replace("[", "");
	         //str2=str2.replace("]", "");
	         //str2=str2.replace(",", "");
	         //solver.add(str2);
	        //Lexer lx3=new Lexer();
	         //lx3.tokenize(content3);
	         //List <String> result3=lx3.getTokensStrings();        
	         //String str3=result3.toString();	       
	         //str3=str3.replace("[", "");
	         //str3=str3.replace("]", "");
	         //str3=str3.replace(",", "");
	         //solver.add(str3);
	         List<String> LongestCommonSubstrings=solver.getLongestCommonSubstrings(solver.getLongestCommonSubstring().toString());
	         System.out.println(LongestCommonSubstrings.size());
	         for(int i=0;i<LongestCommonSubstrings.size();i++)
	       
	        {
	        	 for(String fileTokenizedContent: listOfTokenizedContent)
	        	 {
	           System.out.println(LongestCommonSubstrings.get(i)+" "+LongestCommonSubstrings.get(i).toString().split(" ").length);
	          System.out.println(fileTokenizedContent+"\n"+count(fileTokenizedContent,LongestCommonSubstrings.get(i) ));
	        	 }
	        }
	        
		  //  System.out.println(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		finally {
		   
		}

	}
	
	 public static int count(final String string, final String substring)
	  {
	     int count = 0;
	     int idx = 0;

	     while ((idx = string.indexOf(substring, idx)) != -1)
	     {
	        idx++;
	        count++;
	     }

	     return count;
	  }

}
