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

/**
 * The class to change the current working DIR
 *
 * @author Jiasong Liang
 * @author liang101
 */

public class Cd extends Command {

    // Store the Directory's name in a list
    private String[] directoryTree;

    // The boolean shows if the command works correctly
    private boolean validity;

    /**
     * The default constructor of the Cd obj. It will create
     * a Cd obj and change the current working Dir to the input dir
     *
     * @param listOfInputTargets The args part of the input command string
     * @param os                 the OperateSystem object.
     */
    public Cd(ArrayList<String> listOfInputTargets, OperateSystem os) {
        super(listOfInputTargets, os);
        this.validity = this.operateSystem();
        this.giveError();
    }

    /**
     * Check if the length of the Args match the requirements
     *
     * @return true if the length is 1. Or, false will be returned.
     */
    private boolean isValid() {
        return this.listOfTargets.size() == 1;
    }

    /**
     * Change the current working directory to the input DIR
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
        if (this.isValid()) {
            this.directoryTree = this.listOfTargets.get(0).split("/");
            // When the case 1
            if (this.listOfTargets.get(0).equals("..")) {
                return goBacktoParentDir();
            }
            // When the case 2
            else if (this.listOfTargets.get(0).equals("/")) {
                return goBackToRoot();
            }
            // When the case 3
            else if (this.directoryTree[0].equals("")) {
                return changeByAbsPath();
            }
            // When the case 4
            else {
                return changeByRelativePath();
            }
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
                (this.listOfTargets.get(0), false);
        this.operateSys.setCurrentWorkingDir(result);
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
        if (this.directoryTree.length == 0) {
            result = this.operateSys.getCurrentDir()
                    .getDirectoryByName(this.listOfTargets.get(0));
        }
        // When intending to change to the some sub DIR of the sub Dir of the
        // current working DIR like A/B, where A and B are DIR
        else {
            result = (Directory) this.operateSys.searchStorageRelative(this
                    .listOfTargets.get(0), false);
        }
        if (result != null) {
            // Change the working DIR to the result.
            this.operateSys.setCurrentWorkingDir(result);
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
    private void giveError() {
        if (!this.validity) {
            this.output.setError(Error.Path);
            this.output.printError();
        }
    }

}
