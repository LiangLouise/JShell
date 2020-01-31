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
 * Represent an file object in the FileSystem
 *
 * @author Jiasong Liang
 * @author liang101
 */

public class File extends Storage {

    private String content;


    /**
     * The Constructor of file without content input.
     * The content is initially an empty String
     *
     * @param inputParent The name of this file
     * @param inputName   the parent DIR of this new file
     */
    public File(Directory inputParent, String inputName) {
        // every File includes a Storage
        super(inputParent, inputName);
        super.isFile = true;
        this.content = "";
    }

    /**
     * The Constructor of file with content input
     *
     * @param inputParent The name of this file
     * @param inputName   the parent DIR of this new file
     * @param content     the String content of the file
     */
    public File(Directory inputParent, String inputName, String content) {
        super(inputParent, inputName);
        super.isFile = true;
        this.content = content;
    }

    /**
     * Replace the original contents with the new content.
     *
     * @param content a String representing the content of the file
     */
    public void rewriteContent(String content) {
        this.content = content;
    }

    /**
     * Append the new content to the original contents
     *
     * @param newContent a String representing the content will be added
     */
    public void appendContent(String newContent) {
        this.content = this.content + newContent;
    }

    /**
     * Get the String content of the file
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Copy this file into the input parent directory.
     * If there is a file under the parentDirectory with the same name, it
     * will be replaced with this new DIR
     *
     * @param parentDirectory a directory object which will store the new dir
     * @return a pointer to the new file being copied
     */
    public File copy(Directory parentDirectory) {
        // Create a new file under that parent Directory
        File copy = new File(parentDirectory, this.name,
                this.content);
        parentDirectory.removeFileByName(this.name);
        parentDirectory.addChild(copy);
        return copy;
    }

}
