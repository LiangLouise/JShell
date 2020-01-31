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

/** Represents cp command.
 * @author Zhiming Chen
*/

public class Cp extends Command {

    // store the path of old file
    private String[] oldDirectoryTree;
    // store the path of new file
    private String[] newDirectoryTree;
    // The boolean shows if the command works correctly
    private boolean validity;
    // the old file itself
    private Storage copy;
    // the directory we put item
    private Directory destinationDirectory;
    /**
     * default constructor that takes a list of input targets,
     * validate them and print the output
     *
     * @param listOfInputTargets The args part of the input command string
     * @param os                 the OperateSystem object.
     */
    public Cp(ArrayList<String> listOfInputTargets, OperateSystem os) {
        super(listOfInputTargets, os);
        // error about number of iput arguments
        if (listOfTargets.size() != 2) {
          Error error = Error.Cp;
          super.output.setError(error);
          super.output.printError();
        }else {
          this.validity = this.operateSystem();
          this.givePathError();
        }
    }
    
    /**
     * Check if the length of the Args match the requirements
     *
     * @return true if the length is 2. Or, false will be returned.
     */
    private boolean isValid() {
        return this.listOfTargets.size() == 2;
    }
    /**
     * copy item from OLDPATH to NEWPATH
     * Four conditions to considers:
     * 1. "..": go back to its parent directory;
     * 2. "/" go back to the root Dir
     * 3. relative DIR: Searching from the current working DIR and change to
     * the DIR at the end of the input divided by forward slash;
     * 4. Abs DIR:Searching from the root and change to the DIR at the end of
     * the input path divided by forward slash and Starting with forward slash.
     *
     * @return true if the execution is successful
     */ 
    private boolean operateSystem() {
      // create a boolean to record if we find OLDPATH successfully
      boolean findpath1 = false;
      // create a boolean to record if we find NEWPATH successfully
      boolean findpath2 = false;
      if(this.isValid()) {
        this.oldDirectoryTree = this.listOfTargets.get(0).split("/");
        this.newDirectoryTree = this.listOfTargets.get(1).split("/");
        findpath1 = findCopy();
        findpath2 = findDestination();
      }
      // copy that item
      copy.copy(destinationDirectory);
      return findpath1 && findpath2;
    }
    
    /**
     * find the item we want to copy according to OLDPATH
     * 
     * @return true if the path is valid
     */
    
    private boolean findCopy() {
      // case: "." occurs
      if(this.listOfTargets.get(0).equals(".")) {
        if((copy = this.operateSys.getCurrentDir()) != null) {
          return true;
        }else {
          return false;
        }
      // case: ".." occurs
      }else if(this.listOfTargets.get(0).equals("..")) {
        return getStorageAtParentDir();
      // case: absolute path is given
      }else if(this.oldDirectoryTree[0].equals("")) {
        return getStorageByAbsolutePath();
      // case: relative path is given
      }else {
        return getStorageByRelativePath();
      }
    }
    
    /**
     * find if NEWPATH is valid
     * 
     * @return true if the path is valid
     */
    
    private boolean findDestination() {
      // case: ".." occurs
      if(this.listOfTargets.get(1).equals("..")) {
        return goBacktoParentDir();
      // case: copy something to root
      }else if(this.listOfTargets.get(1).equals("/")) {
        return goBackToRoot();
      // case: absolute path is given
      }else if(this.newDirectoryTree[0].equals("")) {
        return changeByAbsPath();
      // case: relative path is given
      }else {
        return changeByRelativePath();
      }
    }

    /**
     * Search from root to the Directory at the end of the input
     * to get the item we want
     * 
     * @return true if path is valid
     */
    
    private boolean getStorageByAbsolutePath() {
      if((copy = (Directory) this.operateSys.searchStorageAbsolute
          (this.listOfTargets.get(0), false)) == null) {
          copy = (File) this.operateSys.searchStorageAbsolute
            (this.listOfTargets.get(0), true);
      }
      return copy != null;
    }
    
    /**
     * Search from the current working DIR to the Directory at the end of the
     * input to get the item we want
     * 
     * @return true if path is valid
     */
    
    
    private boolean getStorageByRelativePath() {
      // change to the sub DIR/ File of the current Working DIR
      if (this.oldDirectoryTree.length == 0) {
          if((copy = this.operateSys.getCurrentDir()
                  .getDirectoryByName(this.listOfTargets.get(0))) == null) {
             copy = this.operateSys.getCurrentDir()
                 .getFileByName(this.listOfTargets.get(0));
          }
      }
      // change to the some sub DIR of the sub Dir of the
      // current working DIR like A/B, where A and B are DIR
      else {
          if((copy = (Directory) this.operateSys.searchStorageRelative(this
                  .listOfTargets.get(0), false)) == null) {
            copy = (File) this.operateSys.searchStorageRelative
                (this.listOfTargets.get(0), true);
          }
      }
      return copy != null;
    }

    /**
     * Change the Working DIR to its parent DIR
     *
     * @return true if the program can get back to the parent directory.
     */
    private boolean getStorageAtParentDir() {
        //Check if the current working DIR has parent DIR
        if (this.operateSys.getCurrentDir().getParent() != null) {
            copy = this.operateSys.getCurrentDir().getParent();
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Change the Working DIR to its parent DIR
     *
     * @return true if the program can get back to the parent directory.
     */
    private boolean goBacktoParentDir() {
        //Check if the current working DIR has parent DIR
        if (this.operateSys.getCurrentDir().getParent() != null) {
            this.operateSys.setCurrentWorkingDir(
                    this.operateSys.getCurrentDir().getParent());
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Search from root to the Directory at the end of the input
     *
     * @return true if the program can get to the directory at the end of the
     * input path.
     */
    private boolean changeByAbsPath() {
        Directory result = (Directory) this.operateSys.searchStorageAbsolute
                (this.listOfTargets.get(1), false);
        destinationDirectory = result;
        return result != null;
    }

    /**
     * Search from the current working DIR to the Directory at the end of the
     * input
     *
     * @return true if the program can get to the directory at the end of the
     * input path.
     */
    private boolean changeByRelativePath() {
        Directory result;
        // When intending to change to the sub DIR of the current Working DIR
        if (this.newDirectoryTree.length == 0) {
            result = this.operateSys.getCurrentDir()
                    .getDirectoryByName(this.listOfTargets.get(1));
        }
        // When intending to change to the some sub DIR of the sub Dir of the
        // current working DIR like A/B, where A and B are DIR
        else {
            result = (Directory) this.operateSys.searchStorageRelative(this
                    .listOfTargets.get(1), false);
        }
        if (result != null) {
            // record the directory where we put the item
            destinationDirectory = result;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Change the current working Dir to the Root Dir
     *
     * @return true, if this works successfully.
     */
    private boolean goBackToRoot() {
        this.operateSys.setCurrentWorkingDir(this.operateSys.getRoot());
        return true;
    }

    /**
     * Print out the Error when the regular operations failed to execute.
     */
    private void givePathError() {
        if (!this.validity) {
            Error error = Error.Path;
            this.output.setError(error);
            this.output.printError();
        }
    }
}
