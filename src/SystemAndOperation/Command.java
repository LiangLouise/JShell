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

import java.util.ArrayList;

/** Represents command class.
 * @author Shinan Liu
*/
public abstract class Command{
	protected boolean isValid;
	// A list of string of input  ** command name not included
	protected ArrayList<String> listOfTargets;
	// a default Output
	protected Output output;
    // a directory or file that will be operated
    protected Storage targetStorage;
    // this value helps command to find the current operate system
    protected OperateSystem operateSys;


	/**
	 * default constructor that takes a list of input targets,
	 * validate them and print the output
	 * 
	 * @param listOfInputTargets
	 */
	 public Command(ArrayList<String> listOfInputTargets, OperateSystem os) {
		// set the list of targets as input
		listOfTargets = listOfInputTargets;
		// set the operate system as the input
		operateSys = os;
		isValid = false;
		output = new Output();
		
		//// test if the InputTargets are valid
	    //if (isValid()) {
	    	//operateSystem();
	    //// run Output's printing method to print the output,
	    //// whether it's an error or other message
	    //System.out.println(Output.toString);
	    }
	
	/**
	 * a method checking correct number of arguments
	 * here it's blank
	 * 
	 * @return
	 */
	private boolean isValid() {
		// pass an error to the output if it's not valid
		boolean isValid = false;
		// code
		return isValid;
	}

	/**
	 * Update the output inside
	 * here it's blank
	 * @return
	 */
	private boolean operateSystem() {
		return true;
	}
	/**
	 * get the output object
	 */
	public Output getOutput() {
	  return this.output;
	}
}