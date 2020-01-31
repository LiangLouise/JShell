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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Represents cat command.
 * @author Shinan Liu
*/
public class Grep extends Command {
  
  private ArrayList<String> pathList;
  // a string of the target path
  private String path;
  // a string of the sorted input regex
  private String regex;
  // a string of the result that should be finally printed
  private String result;
  // a string indicating if it's an -R
  private boolean isR;
  
  /**
   * The constructor for Grep, which takes the list of input String and the
   * current working operate system and use parent's constructor.
   * @param listOfInputTargets
   * @param os
   */
  public Grep(ArrayList<String> listOfInputTargets, OperateSystem os) {
    super(listOfInputTargets, os);
    //initializing the value
    pathList = new ArrayList<String>();
    result = "";
    regex = "";
    isR = false;
    // validate and operate the system
    //try {
      if (isValid()) {
        operateSystem();
      }   
    //}catch (Exception e){
      // here is a crash protection
     // System.out.println("Oops, invalid input.");
    //}
  }
  
  /**
   * check if the input is valid
   * @return boolean of the validation
   */
  private boolean isValid() {
    // check while sorting the arguments
    if (!sortArguments()){
      Error error = Error.Gen1;
      super.output.setError(error);
      super.output.printError();
      return false;
    }
    return true;
  }

  /**
   * check and sort the input arguments
   * @return boolean showing the validation of arguments
   */
  private boolean sortArguments() {
    int sizeOfList = super.listOfTargets.size();
    // store where the regex part start
    int startAt = 0;int endAt = findRegexEnd()-1;
    if (endAt < 0) {endAt++;}
    // there should be at least two arguments
    if (sizeOfList>=2) {
        // set isR if there is a -R
        if (super.listOfTargets.get(0).equals("-R")) {
          isR = true;startAt = 1;
        }
        // the regex was seperated by whitespaces, here I am
        // combining each part together with a space in between        
        String start = super.listOfTargets.get(startAt);
        String end = super.listOfTargets.get(endAt);
        for (int k = endAt+1; k<sizeOfList;k++) {
          if (k!=sizeOfList) {pathList.add(super.listOfTargets.get(k));}
        }
        if (start.length() == 0 || end.length() == 0) {return false;}
        if (start.charAt(0) != ("\"").charAt(0)||
            end.charAt(end.length()-1) != ("\"").charAt(0)) {
          return false;}
        else {
          regex += start.substring(1, start.length());
          for (int i = startAt+1; i<=endAt-1; i++) {
            regex = regex+ " " + super.listOfTargets.get(i);}
          if (!start.equals(end)) {
            regex =  regex + " " + end.substring(0, end.length()-1);}
          else {regex = regex.substring(0, regex.length()-1);}
        }
    }
    else {return false;}
    return true;
  }
  /**
   * a helper funtion to find the regex end point in the list
   * @return the end index of regex
   */
  private int findRegexEnd() {
    int listSize = super.listOfTargets.size();
    int thisResult = listSize;
    if (listSize != 0) {
      while (thisResult > 0)
      {
        String curString = listOfTargets.get(thisResult-1);
        if (curString!=""&&curString!=null) {
          if (curString.substring(curString.length()-1).equals("\"")) {
            return thisResult;
          }
        }
        thisResult--;
      }
    }
    
    return thisResult;
  }
  /**
   * get a file's content and return lines that contain the regex
   * @param content which is a string for a file content
   * @return lines that contain the regex
   */
  private ArrayList<String> getRegexLines(String content) {
    ArrayList<String> validLines = new ArrayList<String>();
    // make sure the content is not null
    if (content == null) {
      return validLines;
    }
    // separate content into lines
    String[] lines = content.split("\n");
    // make sure the lines is not empty
    if (lines.length == 0) {
      return validLines;
    }
    // for each line, check if it contains the regex
    for (int i=0; i<lines.length; i++) {
      String curLine = lines[i];
      Pattern p = Pattern.compile(regex);
      Matcher m = p.matcher(curLine);
      if (curLine != null&&m.find()) {
        validLines.add(curLine);
      }
    }
    return validLines;  
  }
  
  /**
   * set the result when there is an -R applied
   */
  private void getAllContentR(String thisPath) {
    // get the target path
    Storage target = getTarget(true, thisPath);
    // if the target directory is not found, check if there is a file
    if (target == null) {
      getAllContent(thisPath);
    }
    // if the directory is found
    else {
      // get the child List
      ArrayList<Storage> childList= target.getChildrenList();
      // for every file in the list
      if (childList != null) {
        for (int i = 0;i<childList.size();i++) {
          Storage curChild = childList.get(i);
          if (curChild.getIsFile()) {
            // get the path of the file
            String curPath = curChild.getPath();
            // get all lines inside the file
            ArrayList<String> lines =
                getRegexLines(((File) curChild).getContent());
            // add lines contain the regex into the result
            for (int k=0; k<lines.size();k++) {
              result =  result + curPath + "," + lines.get(k) + "\n";
            }
          }
          else {
            String curDir = curChild.getPath();
            getAllContentR(curDir);
          }
        }
      }

    }
  }
  
  /**
   * Set the result when there was no R applied
   */
  private void getAllContent(String thisPath) {
    // get the target file
    Storage target = getTarget(false, thisPath);
    // if file is not found, print error
    if (target == null) {
      Error error= Error.Path; 
      error.setErrorMessage(thisPath +
          ": Invalid Path");
      super.output.setError(error);
      super.output.printError();
      //result = result + thisPath + ": Invalid Path, Please try again\n";
      return;
    }
    // if file is found
    String content =  ((File) target).getContent();
    ArrayList<String> lines = getRegexLines(content);
    // get lines that matches the regex, add them into the result
    if (lines!=null) {
      for (int i=0; i<lines.size();i++) {
        result = result + lines.get(i) + "\n";
      }
    }

  }
  
  /**
   * Get the storage at path, return null when it's invalid
   * @param isDirectory, which indicate if it is a directory
   * @return a target directory or file
   */
  private Storage getTarget(boolean isDirectory, String thisPath) {
    // create two list and input path string and isFile boolean,
    // to search the storage by path
    ArrayList<String> pathList = new ArrayList<String>();
    ArrayList<Boolean> isFile = new ArrayList<Boolean>();
    pathList.add(thisPath);
    isFile.add(!isDirectory);
    // default result is null
    Storage curresult = null;
    // make sure the return list is not empty
    ArrayList<Storage> storageList =
        super.operateSys.getStorage(pathList, isFile);
    if (storageList.size()!=0) {
      curresult = storageList.get(0);
    }
    return curresult;
  }
  

  /**
   * Operate the command on the system, always return true
   */
  private boolean operateSystem() {
    if (isR) {
      for (int i=0;i<pathList.size();i++) {
        getAllContentR(pathList.get(i));
      }
    }
    else {
      for (int i=0;i<pathList.size();i++) {
        getAllContent(pathList.get(i));
      }
    }
    // delete the extra "\n"
    if (!result.equals("")) {
      if (result.substring(result.length()-1).equals("\n")){
        result = result.substring(0, result.length()-1);
      }
    }
    super.output.setContent(result);
    super.output.giveContent();
    return true;
  }

  
}
