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

/** Represents pushd command.
 * @author Shinan Liu
*/
public class Pushd extends Command {

  /**
   * This is the constructor for pushd that operate the system and give errors
   * @param listOfInputTargets
   * @param os
   */
  public Pushd(ArrayList<String> listOfInputTargets, OperateSystem os) {
    super(listOfInputTargets, os);
    if (isValid()) {
      operateSystem();
      // cd(change directory) to the target directory
      Cd command = new Cd(listOfInputTargets, os);
    }
    // give error if not valid
    else {
      Error error = Error.Gen1;
      output.setError(error);
      output.printError();
    }
  }
  
  /**
   * Push the current directory to the directory stack
   * @return result showing if it's successful
   */

  private boolean operateSystem() {
    super.operateSys.pushCurrentDirectory();
    return true;
  }
  
  /**
   * This check if there is an input directory,
   * further validation will be checked by command cd
   * result boolean showing the validation
   */

  private boolean isValid() {
    boolean result;
    if (super.listOfTargets.size() == 1) {
      result = true;
    }
    else {
      result = false;
    }
    return result;
  }



}
