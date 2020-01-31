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

/** Represents the running class which has the main method
 * @author Hao Liang
*/
public class Man extends Command{
	// contentMessage for message printing
	private String contentMessage;
	/**
	 * constructor for man command set the content message and then
	 * print it to the console
	 * 
	 * @param listOfInputTargets (input )
	 * @param os
	 */
	public Man(ArrayList<String> listOfInputTargets, OperateSystem os) {
		super(listOfInputTargets, os);
		if (isValid()) {
			operateSystem(listOfInputTargets);
			output.setContent(contentMessage);
			System.out.println("the size of the contentMessage is:" + contentMessage.length());
			output.giveContent();
		}
	}

	/**
	 * validation method for Man command
	 * [called by itself]
	 * 
	 * @return isValid
	 */

	private boolean isValid() {
		// man command only takes one argument
		if (listOfTargets.size() == 1) {
			// check if input method is already in JShell method database
			if (ManEnum.contains(listOfTargets.get(0)) != ManEnum.NONE) {
				isValid = true;
			}
			// entered method not available error
			else {
				Error error = Error.MAN1;
				output.setError(error);
				output.printError();
			}
		}
		// wrong number of arguments entered error
		else {
			Error error = Error.MAN2;
			output.setError(error);
			output.printError();
		}
		return isValid;
	}
	
	/**
	 * execution method for Man command
	 * [called by itself]
	 * 
	 * @return contenMessage
	 */
	private String operateSystem(ArrayList<String> listOfInputTargets) {
		// find the correct method to get usage information
		ManEnum command = ManEnum.contains(listOfInputTargets.get(0));
		// combine three different components together
		contentMessage = (command.getMutateName())
		    .concat("\n").concat(command.getDescription()).concat("\n")
				.concat(command.getExample());
		return contentMessage;
	}
}
