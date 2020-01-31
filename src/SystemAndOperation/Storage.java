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
 * Represent an file or folder in the FileSystem
 *
 * @author Jiasong Liang
 * @author liang101
 */

public abstract class Storage {

    // is file or not
    protected boolean isFile;
    // name of the storage object
    protected String name;

    // absolute address in local storage device
    protected String path;

    // Directory which this storage object belongs to
    protected Directory parent;

    // An ArrayList for storing the child file or directory(For Directory use
    // only)
    protected ArrayList<Storage> children;

    /**
     * default constructor for Storage.
     *
     * @param inputParent The Parent Dir of this storage obj
     * @param inputName   the name of this storage obj
     */
    public Storage(Directory inputParent, String inputName) {

        isFile = false;
        // initialize parent directory of this storage object
        parent = inputParent;
        // initialize name of this storage object
        name = inputName;
        // update the absolute address of this storage object
        updatePath();
    }

    /**
     * Create the absolute path of the storage obj. When it has parent
     * directory, add its' parent's directory's path
     * to its head. When there is no parent directory, its name becomes the
     * absolute path.
     */
    public void updatePath() {
        if (this.parent != null) {
            if (!this.parent.getName().equals("/")) {
                this.path = this.parent.getPath() + "/" + this.name;
            } else {
                this.path = "/" + this.name;
            }
        } else {
            this.path = this.name;
        }
    }

    /**
     * Get the abs path of the storage
     *
     * @return path the abs path of the storage
     */
    public String getPath() {
        return path;
    }

    /**
     * Get the storage's name
     *
     * @return name of the Storage obj
     */
    public String getName() {
        return name;
    }

    /**
     * Set the storage with the new name
     *
     * @param name a String representing the new name of the file
     */
    public void setName(String name) {
        this.name = name;
        // update the new absolute path
        updatePath();
    }

    /**
     * Add a new Storage obj to this storage obj's content list
     *
     * @param child a Storage object which will be the sub directory and file
     *              in this Dir
     */
    public void addChild(Storage child) {
        this.children.add(child);
    }

    /**
     * Get the List containing all the storage objs belongs to this storage obj
     *
     * @return an AarrayList which contains all the child of this storage obj
     */
    public ArrayList<Storage> getChildrenList() {
        return this.children;
    }

    /**
     * Initialize an empty list to store the children storage obj.
     */
    public void createChildrenList() {
        this.children = new ArrayList<>();
    }

    /**
     * Get the parent Dir of this Storage obj
     *
     * @return an Dir obj which is the parent of this storage obj
     */
    public Directory getParent() {
        return this.parent;
    }

    /**
     * The abstract method to copy file which will be overrode in the
     * Directory and file class to copy this
     *
     * @param parentDirectory
     */
    public abstract Storage copy(Directory parentDirectory);

    /**
     * Only if the object is a file, this will return True;
     */
    public boolean getIsFile() {
        return isFile;
    }
}
