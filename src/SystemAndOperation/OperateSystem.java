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
import java.util.Collection;
import java.util.List;
import java.util.Stack;

/** Represents the system to be operated.
 * @author Shinan Liu
 * @author Hao Liang
 * @author Jiasong Liang
 * @author Zhiming Chen
*/
public class OperateSystem {
    private static OperateSystem ref;
    // a boolean that presents the running status of the system
    private boolean exit;
    // history Stack for ever entered commands
    private ArrayList<String> historyCommand;
    // root for the storage system
    private Directory root;
    // stack for working directory / previous working directory records
    private Stack<Directory> directoryFootprint;
    // current working directory
    private Directory currentWorkingDirectory;
    // a input object for JShell to set
    private Input input;
    
    // private ArrayList<String> listOfInputTargets;
    
    /**
     * Default constructor for operate system that set the storage for
     * files and directories,
     * it also initializes root, command history and
     * directory history for the system.
     */
    public OperateSystem() {
        // default exit status is false
        this.exit =false;
        // a new stack to store the directory history
        directoryFootprint = new Stack<Directory>();
        // a new stack to store the command history
        historyCommand = new ArrayList<>();
        // a default root that is empty
        root = new Directory(null, "/");
        root.changeToRoot();
        currentWorkingDirectory = root;
    }

    /**
     * The method to create OperateSystem, which will create only one OS
     * object between multiple windows and terminal.
     * @return the pointer to the OperateSystem
     */
    public static OperateSystem connectToFileSystem() {
        if (ref == null) {
            ref = new OperateSystem();
        }
        return ref;
    }
    /**
     * Setter method for input variable that takes user's input
     * @param enteredInput
     * @param os
     */
    public void setInput(String enteredInput, OperateSystem os) {
        input = new Input(enteredInput, os);
    }

    /**
     * It returns the storage with the absolute path in input
     * A valid input should be like "/" for root or "/a/b/c",
     * Only valid inputs are allowed,
     * path should be correct or it will return null.
     * @param absoStorageName
     * @param isFile
     * @return result
     */
    public Object searchStorageAbsolute(
        String absoStorageName, boolean isFile){
      // default result should be root
      Object result;
      // sort the input and separate each level of the path
      String[] sortedPath = absoStorageName.split("/");
      // the first object of the sorted list is always empty
      // length 1 means input is "/", so we start from index 1
      // loop through the path list from the second element
      result = root;
      int i = 1;
      while ((result != null) && (i < sortedPath.length)) {
        // if it's the last element in the path, it should be the storage
        //   we want to get
        if (i + 1 == sortedPath.length){
          // get file or directory depends on the input boolean isFile
          if (isFile) {
            result = ((Directory) result).getFileByName(sortedPath[i]);
          }
          else {
            result = ((Directory) result).
                getDirectoryByName(sortedPath[i]);
          }
        }
        else {
          // intermediate elements 
          // should only be directorys but not files 
          result = ((Directory) result).
              getDirectoryByName(sortedPath[i]);           
        }
        i++;
      }
      return result;
    }
    
    /**
     * It returns the storage with the absolute path in input.
     * A valid input should be like "a" 
     * (a is in current directory)or "a/b/c".
     * Only valid inputs are allowed,
     * path should be correct or it will return null.
     * @param relaStorageName
     * @param isFile
     * @return result
     */
    public Storage searchStorageRelative(
      String relaStorageName, boolean isFile){
      // the default result should be the current working directory
      Storage result = currentWorkingDirectory;
      // sort the input and separate each level of the path
      String[] sortedPath = relaStorageName.split("/"); int i = 0;
      // it starts from the first element which should be under current
      // directory     
      while ((result != null) && (i < sortedPath.length)) {
        if (sortedPath[i].equals("..")){
          result = result.getParent();
          if (i + 1 == sortedPath.length){
            if (isFile) {
              result = null;
            }}
          i++;
        }
        else if (sortedPath[i].equals(".")) {
          i++;
        }
        // only the last element in the path could be a file
        else {
          if (i + 1 == sortedPath.length){
            if (isFile) {
              result = ((Directory) result).getFileByName(sortedPath[i]);
            }
            else {result = ((Directory) result).
                getDirectoryByName(sortedPath[i]);
            }}
          else {
            // intermediate elements 
            // should only be directories but not files
            result = ((Directory) result).
                getDirectoryByName(sortedPath[i]);
          }
          i++;}}
      return result;
    }

    /**
     * Get the current working directory stack
     * @return currentWorkingDirectory
     */
    public Directory getCurrentDir() {
        return currentWorkingDirectory;
    }

    /**
     * Add an nonempty input command 
     * String to the command history ArrayList
     * @param command A string input in the terminal,
     *  representing the command
     */
    public void addHistory(String command) {
        if(!command.equals("")) {
            historyCommand.add(command);
        }
    }

    /**
     * Get the history of the command history ArrayList
     * @return historyCommand
     */
    public ArrayList<String> getHistory() {
        return historyCommand;
    }
    
    /**
     * Get the top element in the stack and remove it.
     * @return result
     */
    public Directory popDirectory() {
      Directory result;
      if (directoryFootprint.empty()) {
        result = null;
      }
      else {
        result = directoryFootprint.pop();
      }
      return result;
    }

    /**
     * Reset the current working directory.
     * @param input
     */
    public void setCurrentWorkingDir(Directory input) {
      currentWorkingDirectory = input;
    }
    
    /**
     * Get the root of the system
     * @return root
     */
    public Directory getRoot() {
        return root;
    }
    
    /**
     * Setter function for exit variable
     * 
     */
    public void doExit() {
        this.exit = true;
    }
    /**
     * Push the current working directory
     *  into the directory history stack
     */
    public void pushCurrentDirectory() {
      directoryFootprint.push(currentWorkingDirectory);
    }

    
    /**
     * getter function for exit variable
     * 
     * @return
     */
    public boolean getExit() {
    	return this.exit;
    }

    /**
     * Get an arraylist of storage by inputing
     * their names and types in two lists.
     * @param inputList
     * @param inputTypes
     * @return storageList
     */
    public ArrayList<Storage> getStorage(ArrayList<String> inputList,
      ArrayList<Boolean> inputTypes){
      // initialize the return list
      ArrayList<Storage> storageList = new ArrayList<Storage>();
      // loop thorough each element in the input name list
      for (int i = 0; i < inputList.size(); i++) {
        // set current name and current type
        String currentName = inputList.get(i);
        Storage currentStorage = null;
        boolean isFile = inputTypes.get(i);
        // if the name starts with "/", it's an absolute path
        if ((currentName.length() != 0) 
            && (currentName.charAt(0) == "/".charAt(0))) {
          currentStorage =
              (Storage) searchStorageAbsolute(currentName, isFile);
        }
        // else it's a relative path
        else {
          currentStorage =
              (Storage) searchStorageRelative(currentName, isFile);
        }
        storageList.add(currentStorage);
      }
      return storageList;     
    }

    /**
     * For debug use only.
     *  To create multi OS for different operation test
     * @return a new OperateSystem object
     */
    public static OperateSystem createNewOperateSystem() {
      return new OperateSystem();
    }
}
