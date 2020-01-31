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
package driver;

import java.util.Scanner;
import SystemAndOperation.*;

/** Represents the running class which has the main method
 * @author Shinan Liu
 * @author Hao Liang
 * @author Jiasong Liang
 * @author Zhiming Chen
*/
public class JShell {

  	/*
  	 *  
  	 */
	public static void main(String[] args) {
		// a string data "input" for input message storage
		String typedIn = null;
		// a string data "Output" for output message storage
		String outPut = null;
	    
	    // step 2: initialize JShell
	    OperateSystem os = OperateSystem.connectToFileSystem();
	    
	    do {
	      // step1: get the typed in information
	      String message = "/#: ";
	      System.out.print(message);
	      Scanner in = new Scanner(System.in);
	      typedIn = in.nextLine();

	      // step 3: get the output of JShell
	      os.setInput(typedIn, os); 
	    }
	    while (!os.getExit());
	}
}
