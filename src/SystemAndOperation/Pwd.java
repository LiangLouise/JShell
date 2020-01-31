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

/** Represents pwd command.
 * @author Shinan Liu
*/
public class Pwd extends Command {

    /**
     * A constructor for pwd that give the path of current working directory
     * and give errors if there is any of them
     * @param input
     * @param os
     */
	public Pwd(ArrayList<String> input, OperateSystem os) {
		super(input, os);
		// there should be no input argument
		if (input.size() == 0) {
		  // give the path
		  String path = os.getCurrentDir().getPath();
		  super.output.setContent(path);
          super.output.giveContent();
		}
		else {		  
		  // give error of invalid arguments
		  Error error = Error.Gen1;
		  super.output.setError(error);
          super.output.printError();
		}
	} 
}
