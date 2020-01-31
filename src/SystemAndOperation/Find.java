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
public class Find extends Command {

  // a list of file name to find
  private ArrayList<String> listOfNames;
  // a list of file/directory types to find
  // if element is true, then it's file, else it's diretcory
  private ArrayList<Boolean> listOfTypes;
  /**
   * A constructor for find command that generate the name list and type list
   * @param listOfInputTargets
   * @param os
   */
  public Find(ArrayList<String> listOfInputTargets, OperateSystem os) {
    super(listOfInputTargets, os);
    // generate the empty list
    listOfNames = new ArrayList<String>();
    listOfTypes = new ArrayList<Boolean>();
    // if valid, execute
    // a protection for crashing
    try {
      if (isValid()) {
        operateSystem();
      }
    }catch (Exception e){
      System.out.println("Oops, unsupported input detected,"
          + " please check user guide with Man command");
    }

  }
  
  /**
   * The input list should have at least 5 elements
   * also at specific indexs elements should be specific
   * @return boolean showing the validation
   */

  private boolean isValid() {
    // input should be at least 5 element
    // last four should be specific
    if ((super.listOfTargets.size() < 5)
        ||(!sortInput())){
      super.output.setError(Error.Gen1);
      super.output.printError();
      return false;
    }
    else {
      return true;
    }

  }

  /**
   * A method that sort the input list, check the specific strings at some
   * indexs, and give names and types to two lists 
   * @return boolean showing the validation
   */
  public boolean sortInput() {
    // initialize the separate index
    int separateIndex = 0;
    // default isfile will be false
    boolean isfile = false;    
    // loop through the input list
    for (int i = 0; i< listOfTargets.size(); i++) {
      // the separate point is where the -type at
      if (listOfTargets.get(i).equals("-type")) {
        separateIndex = i;
      }   
    }
    // if there is no -type, this is invalid
    if ((separateIndex == 0) || (listOfTargets.size() - separateIndex) != 4) {
      return false;
    }
    // there should be a [d|f] after -type to show if it's file or directory
    else {
      if ((listOfTargets.get(separateIndex + 1).equals("d"))
          ||(listOfTargets.get(separateIndex + 1).equals("f"))){
        isfile = (listOfTargets.get(separateIndex + 1).equals("f"));
      }
      // if there is no [f|d], there is an error
      else {
        return false;
      }
      // this stores the name of the searching file or directory
      String name = getName(separateIndex + 3);
      // empty string means getName() gives an negative result
      if (name.equals("")) {
        return false;
      }
      // loop through the list by separate index, manually add the list
      // of targets' path and file type into the lists
      for (int k = 0; k < separateIndex; k++) {
        if (!listOfTargets.get(k).equals("/")){
          listOfNames.add(listOfTargets.get(k)+"/"+name);
        }
        else {
          listOfNames.add("/" + name);
        }
        listOfTypes.add(isfile);
      }
    }
    return true;
  }
  
  /**
   * This method get the name string in the input and return "" if the input
   * is invalid
   * @param index is the name index
   * @return result when it's empty it means invalid
   */
  public String getName(int index) {
    // default result is "", which means invalid
    String result = "";
    // get the name string
    String input = listOfTargets.get(index);
    if (!listOfTargets.get(index - 1).equals("-name")) {
      return result;
    }
    // the name string should be starts with " and end with "
    if (input.endsWith("\"")&&input.startsWith("\"")) {
      result = input.substring(1, input.length()-1);
    }
    return result;
  }
  
  /**
   * Print found message for files and directorys that are found,
   * print not found message for names that are not found.
   * @return boolean that shows the operation is successful
   */

  private boolean operateSystem() {
    // the default result is empty
    String result = "";
    // get the storage list of found files and directorys
    ArrayList<Storage> storage =
        super.operateSys.getStorage(listOfNames, listOfTypes);
    // for items in the list, print not found for null items and 
    // print others' path for found items
    for (int i = 0; i < storage.size(); i++) {
      String curName = listOfNames.get(i);
      if (curName.equals("/")) {
        curName = "/";
      }
      if (storage.get(i) == null) {
        Error error= Error.Gen1; 
        error.setErrorMessage("Not found: " + curName);
        super.output.setError(error);
        super.output.printError();
      }
      else{
        result = result + "Found: " + curName + "\n";
      }      
    }
    // delete the last extra new line mark
    result = result.substring(0, result.length() - 1);
    super.output.setContent(result);
    super.output.giveContent();
    return true;
  }
}
