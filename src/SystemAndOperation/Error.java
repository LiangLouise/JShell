// **********************************************************
// Assignment2:
// Student1: Hao Liang
// UTORID user_name: liangh21
// UT Student #: 1002100102
// Author: Hao Liang
//
// Student2:Shinan Liu
// UTORID user_name:liushina
// UT Student #:1002916936
// Author:Shinan Liu
//
// Student3: Jiasong Liang
// UTORID user_name: liang101
// UT Student #: 1004203337
// Author: Jiasong Liang
//
// Student4:Zhiming Chen
// UTORID user_name:chenz145
// UT Student #:1002917016
// Author:Zhiming Chen
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package SystemAndOperation;

/** Represents the running class which has the main method
 * @author Hao Liang
*/
public enum Error {
	LS1("-R has to be indicated at the first not in the middle"
	    + " or you shouldnt name your storage file using -r or -R"),
	LS2("Given invalid File/Directory,"
	    + " please check the storage address for LS"),
	MAN1("Given command are not available"),
	MAN2("Wrong number of arguments entered for MAN command"),
	ECHO1("Invalid input arguments for echo,"
        + " please see ussage details for echo using Man command"),
	ECHO2("Syntax error: quotes don't have its matches"),
    Gen1("Invalid input arguments"),
    Gen2("Some input Files or Directorys do not exist"),
    Gen3("Input file or directory does not exist"),
    Gen4("Input directory does not exist"),
	DEFALUT("Invalid command, please see usage details using Man command"),
	Duplicate("This directory has existed, Please use another name"),
	Path("Invalid Path, Please try again"),
	Path2("Illegal char in the path or no path input, please check the input"),
    Pop1("No further directories in the stack"),
	History1("Number of range, please try again"),
	History2("Not a integer, please try again"),
	History3("Extra arguments, please try again"),
	Regex1("Regex syntex error, please try again"),
	Curl1("Invalid URL, please check if it is valid"),
	Curl2("Unable to get any content from this page"), 
	LS3("invalid number of arguments entered, please check Man menu"),
	Cp("Invalid input arguments for cp,"
        + " please see ussage details for cp using Man command"),
	Mv("Invalid input arguments for mv,"
        + " please see ussage details for mv using Man command"),
	MUTABLE("");

	// private value of errorMessage where each 
    // enum error has to show to the users
	private String errorMessage;
	
	/**
	 * default constructor setting all enum error with errorMessage value
	 * 
	 * @param inputErrorMessage
	 */
	Error(String inputErrorMessage) {
		errorMessage = inputErrorMessage;
	}
	
	/**
	 * getter function for errorMessage
	 * [called by Output]
	 * 
	 * @return errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	
	/**
	 * setter function for customized error
	 * @param input (customized error in order to show the exact line)
	 */
	public void setErrorMessage(String input) {
	  errorMessage = input;
	}
	
	/**
	 * reset the error message everytime the use print an error out
	 */
	public void resetErrorMessage() {
	  errorMessage = "";
	}
}