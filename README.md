# common-token-subsequences

INTRODUCTION
------------
Thank you for downloading the common-token-subsequences program. This program find the shared SEQUENCE of tokens with maximum length.
The program takes in the path of a folder that contains source code files and then tokenizes the files and report the longest common-token subseuences with their scores in a csv file.

REQUIREMENTS
------------
JDK 1.6 

CONTENTS
--------
This sample zip contains:

    /src - source code of the program and other libraries that is used and also testcases.
    
    /src/com - source code of the concurrenttrees library that is used for creating suffix tree.
    
    /src/lexer & /src/token - source code of libraries that are used for tokenizing and lexing the source code files.
    
    /src/2-testcase - a folder that contains 2 source code files for testing the program
    
    /src/10-testcase - a folder that contains 10 source code files for testing the program
    
    /src/100-testcase - a folder that contains 100 source code files for testing the program
    
    /src/bin - contains class files.
    
    /.classpath & .project - project files for eclipse
    
PRE-REQUISITES
--------------
The following is a pre-requisite to successfully run the sample code:

    Make sure that Windows can find the Java compiler and interpreter:
    
        1.Select Start -> Computer -> System Properties -> Advanced system settings -> Environment Variables -> System variables ->                 PATH.
        2.Prepend C:\Program Files\Java\jdk1.6.0_27\bin; to the beginning of the PATH variable.
        3.Click OK three times.
        
COMPILING THE PROGRAM
-------------------
1. Unzip the files contained in the common-token-subsequences-master.zip file to a folder on you hard drive.
2. Open command prompt.
3. From the Command Prompt, navigate to the directory containing .java files, by typing the cd command as below:

        cd ….CommonTokenSubsequences\src
4. Type the javac command in command prompt as below to compile the program:

        javac main/CommonTokenSubsequence.java
   
RUNNING THE PROGRAM
-------------------
Type the java command in command prompt as below to run the program:
  - Run without input parameters:
  
         java main/CommonTokenSubsequence
      
  - Run with one input parameter:
  
         java main/CommonTokenSubsequence input1(path and name of the output csv file)
         
         Example:
         java main/CommonTokenSubsequence g:\out.csv
      
  - Run with two input parameters:
  
         java main/CommonTokenSubsequence input1(name of the test case folder) input2(path and name of the output csv file)
         
         Example:
         java main/CommonTokenSubsequence 10-testcase g:\out.csv
      
If input parameters are not specified the default values are as follows:

Path and name of the output csv file: projectDirectory/src/out.csv

Name of the test case folder: 2-testcase

The test case folders are available in src directory of the program.
