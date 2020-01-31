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

/** Represents popd command.
 * @author Shinan Liu
*/
public class Popd extends Command {

  // this stores the target directory
  private Directory target;
  
  /**
   * A constructor for popped that set the basic value and report error while
   * operating.
   * @param listOfInputTargets
   * @param os
   */
  public Popd(ArrayList<String> listOfInputTargets, OperateSystem os) {
    super(listOfInputTargets, os);
    //set the target directory from the directory stack
    target = super.operateSys.popDirectory();
    if (isValid()) {
      operateSystem();
    }
    else {
      // if not valid, their is a stack empty error
      Error error = Error.Pop1;
      super.output.setError(error);
      super.output.printError();
    }
  }

  /**
   * This method helps to check if the target exist in the stack
   * @return result showing the validation
   */

  private boolean isValid() {
    boolean result;
    if (target == null) {
      result = false;
    }
    else {
      result = true;
    }
    return result;
  }
  
  /**
   * This method moves the current working directory to the target directory
   * we got from the stack
   * return boolean showing it's successful
   */

  private boolean operateSystem() {
    super.operateSys.setCurrentWorkingDir(target);
    return true;
  }
}