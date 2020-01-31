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
public class Exit extends Command{
	
	/**
	 * constructor for Exit command
	 * 
	 * @param listOfInputTargets
	 * @param os
	 */
	public Exit(ArrayList<String> listOfInputTargets, OperateSystem os) {
		super(listOfInputTargets, os);
		// test if the InputTargets are valid
	    if (isValid()) {
	    	operateSystem();
	    }
	    else {
	      super.output.setError(Error.Gen1);
	      super.output.printError();
	    }
	}
	
	/**
	 * a method checking correct number of arguments
	 * [called by itself]
	 * 
	 * @return
	 */
	private boolean isValid() {	    
		if (listOfTargets.size() == 0){
			isValid = true;
		}
		// no need for else cases since isValid is defaulted to be false
		return isValid;
	}
	
	
	/**
	 * Update the output inside
	 * [called by itself]
	 * 
	 * @return
	 */
	private boolean operateSystem() {
		this.operateSys.doExit();
		return true;
	}
	
}
