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
 * The class to create a new directory
 *
 * @author Jiasong Liang
 * @author liang101
 */

public class Mkdir extends Command {

    private static final int MINIMUM_LENGTH = 1;

    // Store the Directory's name in a list
    private String[][] directoryTree;

    /**
     * The default Constructor for Mkdir Command. It will create the Object and
     * execute it.
     *
     * @param listOfInputTargets The args part of the input command string
     * @param os                 the OperateSystem object.
     */
    public Mkdir(ArrayList<String> listOfInputTargets, OperateSystem os) {
        super(listOfInputTargets, os);
        //Execute the Command.
        operateSystem();
    }

    /**
     * Test the length of the args part.
     *
     * @return true, if there is only one word after the Command itself.
     */

    private boolean isValid() {
        if (this.listOfTargets.size() >= MINIMUM_LENGTH) {
            this.createDirTree();
            boolean validity = true;
            for (int i = 0; i < this.listOfTargets.size(); i++) {
                if (!this.isvalidName(this.getNewDirectoryName(i))) {
                    validity = false;
                }
            }
            return validity;
        }
        return false;
    }

    /**
     * Check if there is any char rather than a-z, A-Z and 0-9 in the new dir's
     * name
     *
     * @param name a String represents the name of the new Dir
     * @return true if there is no illegal char.
     */
    public boolean isvalidName(String name) {
        String regexString = "[a-z]*[A-Z]*[0-9]*";
        return name.replaceAll(regexString, "").length() == 0;
    }

    /**
     * Get the name of the DIR intended to created
     *
     * @param i an int representing the index of the new Dir in the input list
     * @return the name of the new DIR
     */
    private String getNewDirectoryName(int i) {
        return directoryTree[i][directoryTree[i].length - 1];
    }

    /**
     * Create the new DIR based on the input DIR string
     *
     * @return boolean to check if the DIR has been created successfully.
     */

    private boolean operateSystem() {
        if (isValid()) {
            // Split the args into an array.
            boolean res = true;
            // the index of Dir path in the list
            int i = 0;
            for (String[] path : this.directoryTree) {
                // If the path is an abs path
                if (path[0].equals("")) {
                    res = operateonAbsolutePath(i);
                    // If the path is a relative path
                } else {
                    res = operatonRelativePath(i);
                }
                i++;
            }
            return res;
        } else {
            this.output.setError(Error.Path2);
            this.output.printError();
            return false;
        }
    }

    /**
     * when finding the input is a relative path, searching from the ROOT to
     * create the new DIR
     *
     * @param i an int representing the index of the new Dir in the input list
     * @return boolean to check if the DIR has been created successfully.
     */
    private boolean operateonAbsolutePath(int i) {
        // Finding the parent directory of the new DIR
        Directory result = (Directory) operateSys.searchStorageAbsolute
                (listOfTargets.get(i).substring(0, listOfTargets.get(i)
                                .length() - (this.getNewDirectoryName(i))
                                .length() - 1),
                        false);
        return createDir(result, i);
    }

    /**
     * when finding the input is a relative path, searching from the current
     * working directory to create the new DIR
     *
     * @param i an int representing the index of the new Dir in the input list
     * @return boolean to check if the DIR has been created successfully.
     */
    private boolean operatonRelativePath(int i) {
        Directory result;
        //When new DIR in the sub DIR of the current working DIR
        if (directoryTree[i].length > 1) {
            // Find the parent Dir
            result = (Directory) operateSys.searchStorageRelative
                    (listOfTargets.get(i).substring(0, listOfTargets.get(i)
                            .length() - (this.getNewDirectoryName(i)).
                            length() - 1), false);
            // When it is only the name of the new Dir
        } else if (directoryTree[i].length == 1) {
            result = this.operateSys.getCurrentDir();
        } else {
            result = null;
        }
        return createDir(result, i);
    }

    /**
     * With given parentDir, create the new directory under this parent
     * directory
     *
     * @param parentDir a directory obj will be the parent directory of the new
     *                  dir
     * @param i         an int representing the index of the new Dir in the
     *                  input list
     * @return true if the DIR can be created successfully.
     */
    private boolean createDir(Directory parentDir, int i) {
        // If some DIR in the path doesn't exist or the name is not valid.
        if (parentDir == null) {
            this.output.setError(Error.Path);
            this.output.printError();
            return false;
            // When the Dir wanted to be created doesn't exist
        } else if (parentDir.getDirectoryByName(this.getNewDirectoryName(i))
                == null) {
            // Create this new Dir
            parentDir.addChild(new Directory(parentDir,
                    this.getNewDirectoryName(i)));
            return true;
            // When there has existing the DIR with this name
        } else {
            this.output.setError(Error.Duplicate);
            this.output.printError();
            return false;
        }
    }

    /**
     * Split the input directory path list into an Array. And each path will be
     * split by "/" into a list.
     */
    private void createDirTree() {
        this.directoryTree = new String[this.listOfTargets.size()][];
        int i = 0;
        for (String dir : this.listOfTargets) {
            // Split all the paths into an array
            this.directoryTree[i] = dir.split("/");
            i++;
        }
    }
}
