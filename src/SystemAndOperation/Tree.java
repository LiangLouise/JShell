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
 * The class to print the Directories and files in the fileSystem
 *
 * @author Jiasong Liang
 * @author liang101
 */

public class Tree extends Command {

    private static final int ARGUMENT_LENGTH = 0;

    /**
     * The default constructor of the Tree obj. It will create
     * a tree obj and print out the file System
     *
     * @param listOfInputTargets The args part of the input command string
     * @param os                 the OperateSystem object.
     */
    public Tree(ArrayList<String> listOfInputTargets, OperateSystem os) {
        super(listOfInputTargets, os);
        this.operateSystem();
    }

    /**
     * Check if there are any extra Args after the command args
     *
     * @return false if there is any extra Args
     */
    
    private boolean isValid() {
        return this.listOfTargets.size() == ARGUMENT_LENGTH;
    }

    /**
     * Print out the entire file system as a tree. For every level of the tree,
     * a table character is added.
     *
     * @return true if the tree can be successfully printed out.
     */
    
    private boolean operateSystem() {
        if (this.isValid()) {
            // Create the tree
            String res = this.printTree(this.operateSys.getRoot(), "\\\n", 1);
            // Remove the last enter and give it to the output part
            this.output.setContent(res.substring(0, res.length() - 1));
            this.output.giveContent();
            return true;
        }
        this.output.setError(Error.DEFALUT);
        this.output.printError();
        return false;
    }

    /**
     * Construct the tree with each level indented by the corresponding number
     * of tab characters. each new DIR will add one level. the root is
     * represented by a \.
     *
     * @param curr       a Directory obj which give its childList to get deeper
     *                   level
     * @param resultTree The result which will be constructed during the
     *                   recursion
     * @param countTime  an int determines the level of the directory
     * @return a String which is the tree
     */
    private String printTree(Directory curr, String resultTree, int
            countTime) {
        // Go through all the child Dir and file under this Dir
        for (Storage child : curr.getChildrenList()) {
            // Add this child dir of child file's name to the child indented by
            // corresponding number of tab
            resultTree += new String(new char[countTime]).replace("\0", "\t")
                    + child.getName() + "\n";
            // If this is a Dir, go through this Dir
            if (!child.isFile) {
                resultTree = printTree((Directory) child,
                        resultTree, countTime + 1);
            }
        }
        return resultTree;
    }

}
