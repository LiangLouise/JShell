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

/** Represents cat command.
 * @author Shinan Liu
*/
public class Cat extends Command {
  
  // here is the list of booleans of 
  private ArrayList<Boolean> listOfIsFile;
  // this is the list of Storage of target
  private ArrayList<Storage> listOfStorage;
  /**
   * The constructor for cat, which takes the list of input String and the
   * current working operate system and use parent's constructor.
   * @param listOfInputTargets
   * @param os
   */
  public Cat(ArrayList<String> listOfInputTargets, OperateSystem os) {
    super(listOfInputTargets, os);
    // sort and set the list of storage
    listOfIsFile = new ArrayList<Boolean>();
    setListStorage();
    // if there are no input file names
    // there is an error of argument number
    if (!isValid()){
      Error error = Error.Gen1;
      super.output.setError(error);
      super.output.printError();
    }
    // test by isValid method
    // if it's not valid, show an error that presents file does't exit
    else {
      try {
        operateSystem();
      }catch (Exception e) {
        // a crash protection for the command
        System.out.println("Oops, unsupported input detected,"
            + " please check user guide with Man command");
      }
    }
  }

  /**
   * A helper function to set the input into the storage list
   */
  public void setListStorage() {
    for (int i=0; i < listOfTargets.size(); i++) {
      listOfIsFile.add(true);
    }
    listOfStorage = super.operateSys.getStorage(listOfTargets, listOfIsFile);
  }
  
  /**
   * execute the command method as it should in JShell
   * @return true as the operateSystem successes
   */
  private boolean operateSystem() {
    // default result is empty
    String result = "";
    // loop through the storage list
    for (int i = 0; i < listOfStorage.size(); i++) {
      // get content of each file and append it to the result
      File file = (File) listOfStorage.get(i);
      // we should ignore the null files that are not found
      if (file != null) {
        String curContent = file.getContent();
        result += curContent;
        // between each file's content there are three blank lines
        result += "\n\n\n";
      }
      else {
        // for not found files, remind users files are not found
        String currentName = super.listOfTargets.get(i);
        Error error= Error.Gen1; 
        error.setErrorMessage(currentName + ": Invalid path,"
            + " valid contents shown after the error part.");
        super.output.setError(error);
        super.output.printError();
      }
    }
    // if there are contents, delete the extra three blank lines
    if (result != ""){
      result = result.substring(0, result.length() - 3);
    }
    // output the result
    super.output.setContent(result);
    super.output.giveContent();
    return true;
  }

  /**
   * Check if the input is valid or not
   * @return result boolean of the validation
   */
  private boolean isValid() {
    // default result is true
    boolean result = true;
    // if there is no input, result should be false
    if (super.listOfTargets.size() == 0) {
      result = false;
    }
    return result;
  }
}
