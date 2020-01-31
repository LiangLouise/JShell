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

/**
 * Represent an directory in the FileSystem
 *
 * @author Jiasong Liang
 * @author liang101
 */

public class Directory extends Storage {

    // a boolean shows if it's root or not
    private boolean isRoot;

    /**
     * This is the default constructor of the Directory obj which is a
     * sub class of Storage class
     *
     * @param inputName   The name of this DIR
     * @param inputParent the parent DIR of this new DIR
     */
    public Directory(Directory inputParent, String inputName) {
        super(inputParent, inputName);
        createChildrenList();
        isRoot = false;
    }

    /**
     * This method will check if this directory contains the directory with
     * the name input.
     * If not, null will be return.
     *
     * @param name the name of the DIR being searched for
     * @return the DIR obj is being searched for
     */
    public Directory getDirectoryByName(String name) {
        for (Storage storage : this.children) {
            if (storage.getName().equals(name) && !storage.isFile) {
                return (Directory) storage;
            }
        }
        return null;
    }

    /**
     * This method will check if this directory contains the file with
     * the name input.
     * If not, null will be return.
     *
     * @param name the name of the file being searched for
     * @return the file obj is being searched for
     */
    public File getFileByName(String name) {
        for (Storage storage : this.children) {
            if (storage.getName().equals(name) && storage.isFile) {
                return (File) storage;
            }
        }
        return null;
    }

    /**
     * This method will check if this directory directory the directory with
     * the name input and remove it if it exists
     *
     * @param name the name of the directory being searched for and removed
     */
    public void removeDirectoryByName(String name) {
        int i = 0;
        boolean notFind = true;
        while (notFind && i < this.children.size()) {
            Storage storage = this.children.get(i);
            if (storage.getName().equals(name) && !storage.isFile) {
                notFind = false;
            }
            i++;
        }
        if (!notFind) {
            this.children.remove(i - 1);
        }
    }

    /**
     * This method will check if this directory contains the file with
     * the name input and remove it if it exists
     *
     * @param name the name of the file being searched for and removed
     */
    public void removeFileByName(String name) {
        int i = 0;
        boolean notFind = true;
        while (notFind && i < this.children.size()) {
            Storage storage = this.children.get(i);
            if (storage.getName().equals(name) && storage.isFile) {
                notFind = false;
            }
            i++;
        }
        if (!notFind) {
            this.children.remove(i - 1);
        }
    }

    /**
     * Set the directory as a root
     */
    public void changeToRoot() {
        isRoot = true;
    }

    /**
     * Copy this DIR with its sub directories and files into the another
     * parent directory.
     * If there is a DIR under the parentDirectory with the same name, it
     * will be overwritten by this new DIR
     *
     * @param parentDirectory a directory object which will store the new dir
     * @return a pointer to the new directory being copied
     */
    public Directory copy(Directory parentDirectory) {
        Directory newCopy = new Directory(parentDirectory, this.name);
        for (Storage storage : this.getChildrenList()) {
            // When it is a file belongs to this DIR
            storage.copy(newCopy);
        }
        // Remove the existing DIR with this name under the parent DIR
        parentDirectory.removeDirectoryByName(this.name);
        // Add this DIR to the new Parent DIR
        parentDirectory.addChild(newCopy);
        return newCopy;
    }
	
	/**
	 * return true if this directory contains any directories
	 * 
	 * @return result
	 */
	public boolean containsDir() {
	  boolean result = false;
	  // check through all children in the current directory
	  for (Storage item:this.children) {
	    // if any item is not file
	    if (!item.isFile) {
	      result = true;
	    }
	  }
	  return result;
	}
	
}
