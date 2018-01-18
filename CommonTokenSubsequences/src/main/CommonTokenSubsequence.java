package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import java.math.*;

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
				File folder = new File("C:\\Users\\Vaio\\workspace\\CommonTokenSubsequences\\src\\10-testcase");
				File[] listOfFiles = folder.listFiles();
				
				for (File file : listOfFiles) {
					System.out.println(file.getName());
				    if (file.isFile()) {
				    	listOfFilesContent.add(new String(Files.readAllBytes(Paths.get("C:\\Users\\Vaio\\workspace\\CommonTokenSubsequences\\src\\10-testcase\\"+file.getName()))));						
				    }
				}				
		  LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
			//String content = new String(Files.readAllBytes(Paths.get("bin\\test.txt")));
			StringTokenizer defaultTokenizer;
			//tokenize fileContent
			PrintWriter pw = new PrintWriter(new File("C:\\Users\\Vaio\\workspace\\CommonTokenSubsequences\\src\\10-testcase\\out.csv"));
	        StringBuilder sb = new StringBuilder();
	        System.out.println("Tokenize Files Content ....");
			for(String fileContent:listOfFilesContent)
			{
				defaultTokenizer= new StringTokenizer(fileContent);
				//System.out.println("aaaa");
			//	System.out.println(fileContent);
				 Lexer lx=new Lexer();
		         lx.tokenize(fileContent);
		         List<String> listOfTokenStrings=lx.getTokensStrings();
		         String tokenizedContent=listOfTokenStrings.toString();			         
		         tokenizedContent=tokenizedContent.replace("[", "");
		         tokenizedContent=tokenizedContent.replace("]", "");
		         tokenizedContent=tokenizedContent.replace(",", "");
		         listOfTokenizedContent.add(tokenizedContent);
		         solver.add(tokenizedContent);	     

			}
		
			System.out.println("Finding Longest Common Token Subsequence ....");
	        sb.append("Score, Tokens, Count, SourceCode\n");
	        //String st=solver.getLongestCommonSubstring().toString();
	        //System.out.println( "longestcommon:"+st);
	         List<String> LongestCommonSubstrings=solver.getLongestCommonSubstrings(solver.getLongestCommonSubstring().toString());
	         System.out.println("Number of Longest Common Token Subsequences: "+LongestCommonSubstrings.size());
	         for(int i=0;i<LongestCommonSubstrings.size();i++)
	       
	        {
	        	// System.out.println("11"+LongestCommonSubstrings.get(i));
	        	 //System.out.println("22"+LongestCommonSubstrings.get(i).toString());
	        	 int tokenCount=LongestCommonSubstrings.get(i).toString().split(" ").length;
	        	 int count=0;
	        	 for(String fileTokenizedContent: listOfTokenizedContent)
	        	 {
	        		 count+=count(fileTokenizedContent,LongestCommonSubstrings.get(i));
	                // System.out.println(LongestCommonSubstrings.get(i)+" "+tokenCount);
	                 //System.out.println(fileTokenizedContent+"\n"+ count);
	                 
	        	 }
	        	 double score=(Math.log(tokenCount)*Math.log(count));
                 if(score!=0)
                 sb.append(score+", "+tokenCount+", "+count+", "+LongestCommonSubstrings.get(i)+"\n");
          
	        }
	         pw.write(sb.toString());
	         System.out.println("Output CSV file created.");
	         pw.close();
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
