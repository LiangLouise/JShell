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

/** Represents the running class which has the main method
 * @author Hao Liang
*/
public class Output {

    private static boolean needRedirection = false;

    // an arraylist for output message storing
    private String errorMessage;

    private static String outPutMessage;

    // default constructor
    public Output() {
    }

    /**
     * set if need to redirect the output content message to a file
     * @param state the boolean representing if need
     */
    public static void setNeedRedirection(boolean state) {
        needRedirection = state;
    }

    public static boolean getNeedRedirection() {
        return needRedirection;
    }

    /**
     * update output message to Output class for future printing
     * [called by each subCommand]
     *
     * @param inputMessage The message which will be print
     */
    public static void setContent(String inputMessage) {
        outPutMessage = inputMessage;
    }

    /**
     * update Error object in Output class for error presentation
     * [called by each subCommand]
     *
     * @param inputError
     */
    public void setError(Error inputError) {
        // change Error class to an Error message
        errorMessage = inputError.getErrorMessage();
    }

    /**
     * print out error message
     * [called by OperateSystem]
     */
    public void printError() {
      System.out.println(this.errorMessage);
    }

    /**
     * If we do not need to redirect the output message, it will return null
     * and print out the message on the terminal
     * @return the output message if we need to redirect
     */
    public static String giveContent() {
      // no redicrection needed
      if (!needRedirection) {
        System.out.println(outPutMessage);
        return null;
      }
      String res = outPutMessage;
      return res;
    }

    /**
     * one way of knowing if error occurs
     * [called by Output]
     *
     * @return boolean
     */
    public boolean hasError() {
        if (this.errorMessage != null) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * test only, to get the output
     * @return
     */
    public String getContent() {
      return this.outPutMessage;
    }
    /**
     * test only, to get the error
     * @return
     */
    public String getError() {
      return this.errorMessage;
    }
}
