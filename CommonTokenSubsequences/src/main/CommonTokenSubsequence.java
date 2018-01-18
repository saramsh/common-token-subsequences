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
		String testCase,outPutPath;
		testCase="2-testcase";
		outPutPath=".\\Out.csv";
			try {
				if(args.length>0)
				{
					if(args.length==1)
					{
						 outPutPath=args[0];
							if(!outPutPath.matches("([a-zA-Z]\\:)?(\\\\[a-zA-Z0-9_\\-|\\.]+)+\\.csv"))
							{
								System.out.println("Error: Insert the input parameter in correct format. Example: d:\\Out.csv");
								return;
									}
					}											
					else
					{
						testCase=args[0];
						outPutPath=args[1];
							if(!testCase.matches("(2|10|100)\\-testcase"))
							{
								System.out.println("Error: Insert correct name for test case. (2-testcase or 10-testcase or 100-testcase");
								return;					
							}
							if(!outPutPath.matches("([a-zA-Z]\\:)?(\\\\[a-zA-Z0-9_\\-|\\.]+)+\\.csv"))
							{
								System.out.println("Error: Insert Second input parameter in correct format. Example: d:\\Out.csv");
								return;
									}
							}
			}
				//Read content of files and add them to listOfFilesContent
				File folder = new File(".\\"+testCase);
				//System.out.println(testCase+ "\\");
				File[] listOfFiles = folder.listFiles();
			//	System.out.println("aaaaaaaaa"+listOfFiles.length);
				for (File file : listOfFiles) {
					System.out.println(file.getName());
				    if (file.isFile()) {
				    	listOfFilesContent.add(new String(Files.readAllBytes(Paths.get(".\\"+testCase+ "\\"+file.getName()))));						
				    }
				}				
		  LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
			//String content = new String(Files.readAllBytes(Paths.get("bin\\test.txt")));
			StringTokenizer defaultTokenizer;
			//tokenize fileContent
			PrintWriter pw = new PrintWriter(new File(outPutPath));
	        StringBuilder sb = new StringBuilder();
	        System.out.println("Tokenize Files Content ....");
	        int y=1;
			for(String fileContent:listOfFilesContent)
			{
				defaultTokenizer= new StringTokenizer(fileContent);
				System.out.println(y);y++;
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
	        	 double score=((Math.log(tokenCount)/Math.log(2))*(Math.log(count)/Math.log(2)));
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
