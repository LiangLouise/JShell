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
public class Ls extends Command{
	
	// an content list for current working directory
	private ArrayList<?> content;
	
	// and content message for current
	// working directory applied current command
	private String contentMessage = "";
	
	// current directory the user is working on
	private Directory currentDir;
	
	// current working operating system
	private OperateSystem os;
	
	// a indicator for recursive test
	private boolean R = false;
	
	public Ls(ArrayList<String> listOfInputTargets, OperateSystem os) {
		super(listOfInputTargets, os);
		// set current directory
		currentDir = os.getCurrentDir();
		// set current operating system
		this.os = os;
		if (listOfTargets.size() == 0){
          contentMessage = operateCurrent(currentDir);
		}
		else {
    	  // test if the InputTargets are less than 2
    	  for (int index = 0; index < listOfTargets.size(); index++) {
    	    
    	    String path = listOfTargets.get(index);
    	    // recursive calls be executed
    	    if (isValid(path, index) && 
    	        !(path.equals("-R") || path.equals("-r"))) {
    	      
    	      if (index+1 == listOfTargets.size()) {
                contentMessage = contentMessage 
                    + operateSystem(currentDir, path);
    	      }
    	      else {
                // create contentMessage
    	        contentMessage = contentMessage 
    	            + operateSystem(currentDir, path) + "\n";
    	      }

    	    }
    	  }
		}
        // pass contentMessage to Output
        this.output.setContent(contentMessage);
        if (!contentMessage.equals("")) {
          output.giveContent();
        }
	}
	
	/**
	 * a method checking valid path and valid command expression
	 * [called by itself]
	 * 
	 * @return isValid (if all path entered by user is valid)
	 */
	private boolean isValid(String pathGiven, int index) {
	  // default isValid to be true
	  isValid = true;
	  // -r message appears in the middle
	  if (pathGiven.equals("-R") || pathGiven.equals("-r") ) {
        if (index == 0 && (index+1 != listOfTargets.size())) {
          R = true;
        }
        else if (index + 1 == listOfTargets.size()) {
          isValid = false;
          Error error = Error.LS3;
          output.setError(error);
          output.printError();
        }
        else {
          isValid = false;
          Error error = Error.LS1;
          output.setError(error);
          output.printError();
        }
      }
	  // storage object does not exist 
	  else if (getStorageByPath(pathGiven).get(0) == null) {
	    isValid = false;
	    Error mutable = Error.MUTABLE;
        Error error = Error.LS2;
        mutable.setErrorMessage("path : " + pathGiven + " has Error: " 
            + error.getErrorMessage());
        output.setError(mutable);
        output.printError();
        mutable.resetErrorMessage();
        
	  }
	  return isValid;
	}

	/**
	 * executing the method, setting contentMessage according to the
	 * storage situation and what command to run
	 * [called by itself]
	 * 
	 * @return contentMessage (the output message under targetDir and
	 * the path given)
	 */
	private String operateSystem(Directory targetDir, String pathGiven){
	  String localMessage = "";
	  if (!R) {
	    ArrayList<?> storageCheck = getStorageByPath(pathGiven);
	    // when target path is a file
	    if (storageCheck.get(1).equals(true)) {
	      localMessage = ((File) storageCheck.get(0)).getPath();
	    }
	    // when target path is a dir
	    else {
	      // 1. change targetDir
	      targetDir = (Directory) storageCheck.get(0);
	      // 2. set the head of the contentMessage
	      localMessage = localMessage 
	          + targetDir.getName().concat(": ");
	      // 3. get content of targetDir and append to contentMessage
	      localMessage = localMessage + operateCurrent(targetDir);
	    }
	  }
	  // -R is activated
	  else if (R) {
	    ArrayList<?> storageCheck = getStorageByPath(pathGiven);
	    // when target path is a file
	    if (storageCheck.get(1).equals(true)) {
	      localMessage = ((File) storageCheck.get(0)).getPath();
	    }
	    // when target path is a dir
	    else {
	      // a string head to indicate the replations between
	      // parent dir and children dir
	      String head = "";
	      // 1. change targetDir
	      targetDir = (Directory) storageCheck.get(0);
	      // 2. call recursive handler
	      localMessage = operateRecursive(targetDir, head);
	    }
	  }
	  return localMessage;
	}
	
	/**
	 * return all the contents under given directory, expand all
	 * contents in any directory it has as well
	 * 
     * @param currentDir (given directory)
     * @return storageMessage (message representing the content of the
     *                         given directory)
	 */
	private String operateRecursive(Directory targetDir, String head) {
	  String storageMessage = "";
	  // base cases
	  if (!targetDir.containsDir()) {
	    // base case 1:  empty directory
	    if (targetDir.children.isEmpty()) {
	      storageMessage = head + "/" + targetDir.name + ":"
	            + "\n" + "\n";
	    }
	    // base case 2: currentDir only contains files
	    else {
	      storageMessage = head + "/" + targetDir.name + ":" + 
	        "\n" + operateCurrent(targetDir);
	    }
	  }
	  // case 3: normal directories
	  else {
	    head = head + "/" + targetDir.name;
	    // print all content no matter its file or directory
	    storageMessage = storageMessage + head + ":"
	    + "\n" + operateCurrent(targetDir) + "\n";
	    for (Storage item: targetDir.children) {
	      // only recursive when item is a directory
	      if (!item.isFile) {
	        targetDir = (Directory) item;
	        storageMessage = storageMessage 
	            + operateRecursive(targetDir,head);
	      }
	    }
	  }
	  return storageMessage;
	}
	
	/**
	 * get all content in current directory (when no path specified)
	 * [called by itself]
	 * 
	 * @param currentDir (current working directory)
	 * @return storageMessage (message representing the content of the
	 *                         current working directory)
	 */
	private String operateCurrent(Directory currentDir) {
	  String storageMessage = "";
	  // check if current dir is empty
	  if (currentDir.getChildrenList() == null) {
	    content = null;
	   }
	  else {
	    content = currentDir.getChildrenList();
	  }
	  // add each components in current dir into contentMessage
	  for (Object child : content) {
	    if (storageMessage == ""){
	      storageMessage = ((Storage) child).getName();
	    }
	    else {
	    storageMessage = storageMessage + " " +
	        ((Storage) child).getName();
	    }
	  }
	  return storageMessage;
	}

	/**
	 * a helper function that return an arraylist of storage and isFile
	 * indicator, so the execution function can be neat and efficient
	 * 
	 * @param pathGiven (A path entered by user)
	 * @return result (An ArrayList that first item is storage
	 *                 second item is boolean of isFile
	 */
	private ArrayList<?> getStorageByPath(String pathGiven) {
	  ArrayList result = new ArrayList();
	  // given an absolute path
	  if (pathGiven.startsWith("/")) {
        File possibleFile = (File) os.searchStorageAbsolute(pathGiven,
            true);
        Directory possibleDir = (Directory) os.searchStorageAbsolute(
            pathGiven, false);
        // add possible file or directory into the result Arraylist
        if (possibleFile != null) {
          result.add(possibleFile); result.add(true);
        }
        else {
          result.add(possibleDir); result.add(false);
        }
	  }
	  // given an relative path
	  else {
        File possibleFile = (File) os.searchStorageRelative(
            pathGiven, true);
        Directory possibleDir = (Directory) os.searchStorageRelative(
            pathGiven, false);
        // add possible file or directory into the result Arraylist
        if (possibleFile != null) {
          result.add(possibleFile); result.add(true);
        }
        else {
          result.add(possibleDir); result.add(false);
        }
	  }
	  return result;
	}
	
}
