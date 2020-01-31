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

/** Represents echo command.
 * @author Zhiming Chen
*/
public class Echo extends Command{
    private File targetFile;
    private String argument;
    /**
     * he default Constructor for Echo Command. 
     * It will create the Object and execute it.
     * 
     * @param Input The args part of the input command string
     * @param os                 the OperateSystem object.
     */
    public Echo(ArrayList<String> Input, OperateSystem os){
        super(Input, os);
        //run Output's printing method to print the output,
        //whether it's an error or other message
        try {
          if (isValid()) {
            if(listOfTargets.size() == 1) {
              operateSystem();
            }else {
              Directory currentDir = os.getCurrentDir();
              // find the file we want
              targetFile = currentDir.getFileByName(listOfTargets.get(2));
              // if it does not exit, create a new file have the same name
              if(targetFile == null) {
                currentDir.addChild(new File(currentDir,
                    listOfTargets.get(2), ""));
                targetFile = currentDir.getFileByName(listOfTargets.get(2));
              }
                operateSystem();
            }
          }
        }catch (Exception e) {
          System.out.println("unsupported input detected,"
              +"please check user guide with man command");
        }
    }

    /**
     * a method checking correct number of arguments
     * echo has two different uses such as
     * echo string[>outfile] and echo string >> outfile
     * 
     * 
     * @return true if the input arguments are valid
     */
    private boolean isValid() {
        isValid = false;
        // check the number of arguments
        // as the rule of echo says, both 2 and 4 are possible for the size
        // of listOfTargets. Otherwise it will cause an error
        if (listOfTargets.size() == 1 || listOfTargets.size() == 3) {
            argument = listOfTargets.get(0);
            // check the usage of quotes
            isValid = true;
            if(!checkQuotes()) {
                isValid = false;
            }
        }else {
            Error error = Error.ECHO1;
            super.output.setError(error);
            super.output.printError();
        }
        return isValid;
    }
    
    /**
     * update the output inside
     * 
     * @return true if the echo can be successfully printed out.
     */

    private boolean operateSystem() {
        // if the argument is only string, print it
        if (listOfTargets.size() == 1) {
            super.output.setContent(argument);
            super.output.giveContent();
        // if we want to put string into file, check > and >>
        }else{
            if(listOfTargets.size() == 3) {
                // if we find >, we need to rewrite content in the file
                if(listOfTargets.contains(">>")) {
                    if(targetFile.getContent().equals("")) {
                      targetFile.appendContent(argument);
                    }else {
                      targetFile.appendContent("\n" + argument);
                    }
                // if we find >>, we need to append string into file
                }else if (listOfTargets.contains(">")) {
                    targetFile.rewriteContent(argument);
                }
            }
        }
        return true;
    }   
    
    /**
     * check if there is error about the usage of quote of string
     * 
     * @return true if the usage of quotes is correct
     */
    
    private boolean checkQuotes() {
        // if the string have double quotes, we need to ignore it
        if(argument.startsWith("\"")) {
            int index = argument.lastIndexOf("\"");
            argument = argument.substring(1,index);
        }
        // check the usage of quote, it will cause syntax error if any
        // quote exist inside the string
        if (argument.contains("\"")) {
            Error error = Error.ECHO2;
            super.output.setError(error);
            super.output.printError();
            return false;
        }
        return true;
    }
}