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
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/** Represents a class which analyze input and create command
 * @author Zhiming Chen
*/
public class Input {

	// The String representing the exclamation point
	private static final String NUMBER_COMMAND = "!";
	// The arrayList storing the argument of the command
	private ArrayList<String> analyzedCommand;
	// The pointer to the os
	private OperateSystem os;
	// The arrayList containing two arraylist of commands and possible
	// redirection
	private ArrayList<ArrayList<String>> resultList;
	
	// default constructor for input
	public Input(String inputString, OperateSystem os) {
		this.os = os;
		// Add the input string to the command history ArrayList
		this.os.addHistory(inputString);
		
		
	    // remove the whitespace to make it standard
		if(inputString.trim().startsWith("echo")) {
		  analyzedCommand = containEcho(inputString);
		  creatCommand();
		}else {
		  String standard = removeExtraSpaces(inputString);  
	      resultList = splitPathRedirection(standard);
		  // When it start with !
          if (standard.startsWith(NUMBER_COMMAND)) {
            analyzedCommand = containsNumber(standard);
			  creatCommand();
          } else {
            // no redirection needed
            if (resultList.get(1).isEmpty()) {
              // only update the array list when command is valid
              analyzedCommand = analyzedCommand(standard);
              creatCommand();
            }
            // redirection happened
            else {
              analyzedCommand = resultList.get(0);
              createWithRedirection();
            }

          }
        }
	}

	/**
	 * remove duplicate spaces in inputString to make it standard
	 * 
	 * example of standard string:
	 * [commandWord] ([argu] ([argu]))
	 *
	 * @param inputString the raw command String got from the terminal
	 * @return standard String the command string with extra whitespace
     * removed
	 */
	public String removeExtraSpaces(String inputString) {
	  // create a empty string for result
	  String standardString = "";
	  // remove whitespace at front and back of the inputString
	  inputString = inputString.trim();
	  // using loop to find each first space after characters in the code
	  // and copy words in front of the whitespace as part of standardString
	  while(inputString.indexOf(" ") > 0) {
	    standardString = standardString + 
	        inputString.substring(0, inputString.indexOf(" ")) + " ";
	    inputString = inputString.substring(inputString.indexOf(" ")).trim();
	  }
	  // we end the loop when inputString only have a string without
	  // any whitespace, add it to stadardString as we want
      standardString = standardString + inputString;
	  return standardString;
	}

	/**
	 * analyze standardString to a standard ArrayList
	 *
	 * @param standardString The command String where extra whiteSpaces are
	 *                       removed
	 * @return analyzedCommand the arrayList that stores each part of the
	 * command
	 */
	// analyze the entered input
	public ArrayList<String> analyzedCommand(String standardString) {
	    // create a new Arraylist	    
        ArrayList<String> analyzedCommand = new ArrayList<String>();
	    // split standardstring around matches of whitespace
	    String s[]  = standardString.split(" ");
	    // using loop put every part of string into the Arraylist
	    for(int i=0; i<s.length; i++){
	        analyzedCommand.add(s[i]);
	    }	    
		return analyzedCommand;
	}

    /**
     *
     * provide a special version of analyzedCommand for echo
     *
     *
	 * @param input The command String where extra whiteSpaces are
	 *                       removed
	 * @return analyzedCommand the arrayList that stores each part of the
	 * command
     */
	public ArrayList<String> containEcho(String input) {
        // create a new Arraylist       
        ArrayList<String> analyzedCommand = new ArrayList<String>();
        // create a empty String
        String standardString = "";
        // remove the space at top and bottom
        input = input.trim();
        // loop the whole string to find space between letters
        while(input.indexOf(" ") > 0 ) {
          // if it starts with a quotation mark, we define the whole string
          // inside the double quote as what we want
          if(input.startsWith("\"")) {
            standardString = standardString + 
                input.substring(1, input.lastIndexOf("\"")) + " ";
            analyzedCommand.add(input.substring(1,input.lastIndexOf("\"")));
            input = input.substring(input.lastIndexOf("\"")+1).trim();
          // otherwise, we delete duplicate whitespace and add each part of
          // string into an array list
          }else {
            standardString = standardString + 
              input.substring(0, input.indexOf(" ")) + " ";
            analyzedCommand.add(input.substring(0, input.indexOf(" ")));
            input = input.substring(input.indexOf(" ")).trim();
          }
        }
        //if there is no whitespace add the remaining part to array list
        standardString = standardString + input;
        analyzedCommand.add(input);
        return analyzedCommand;
	}

	/**
	 * provide a special version of analyzedCommand for Number command
	 * (!NUMBER)
	 * @param standardString The command String where extra whiteSpaces are
	 *                           removed
	 *
	 * @return analyzedCommand the arrayList that stores each part of the
	 * command
	 */
	public ArrayList<String> containsNumber(String standardString) {
        ArrayList<String> argumentList = new ArrayList<String>();
        argumentList.add(standardString.substring(0,1));
        argumentList.add(standardString.substring(1));
        return argumentList;
    }

	/**
	 * get the analyzed command as a ArrayList
	 *
	 * @return argumentList the arrayList that stores each part of the
	 * command
	 */
	// getter method for Input class
	public ArrayList<String> getAnalyzedCommand() {
		ArrayList<String> argumentList = new ArrayList<String>();
		for (int index=0; index<analyzedCommand.size(); index++) {
			if (index != 0) {
				argumentList.add(analyzedCommand.get(index));
			}
		}
		return argumentList;
	}


	public void createWithRedirection() {
	    if(resultList.get(1).size() == 0) {
	        creatCommand();
        } else
        {
            Output.setNeedRedirection(true);
            ArrayList<String> inputmessage = new ArrayList<>();
            creatCommand();
            String output = Output.giveContent();
            Output.setContent(null);
            Output.setNeedRedirection(false);
            inputmessage.add("\""+output+"\"");
            inputmessage.addAll(resultList.get(1));
            Echo newCommand = new Echo(inputmessage, os);
        }
	}

    /**
     * Try to create the command object with the input command input String.
     * If there is no Command can be constructed, the error message will be
     * given instead. Due to !Number command is unique from the other
     * commands, it is considered as a special case.
     */
    public void creatCommand() {
        String className = analyzedCommand.get(0);
    	try {
    	    // Change the fire letter to be capital and others be lowercase
            className = className.substring(0,1).toUpperCase() + className
                    .substring(1).toLowerCase();
            // Create class object
    		Class commandClass =
    		    Class.forName("SystemAndOperation."+className);
    		// Define the constructor
			Constructor commandConstructor = commandClass.getConstructor
					(ArrayList.class, OperateSystem.class);
			// Create the command
			Command command = (Command) commandConstructor.newInstance(this
					.getAnalyzedCommand(), os);
		} catch (Exception e) {
    	    // When it is ! type command
    	    if(className.startsWith(NUMBER_COMMAND)) {
    	        Number number = new Number(this.getAnalyzedCommand(), os);
            } else {
    	        Output output = new Output();
                output.setError(Error.DEFALUT);
                output.printError();
            }
		}
	}
    
    /**
     * a method added for Assignment2B
     * 
     * checking if redirection happens and 
     * 
     * @param inputString
     * @return
     */
    private ArrayList<ArrayList<String>> splitPathRedirection(
        String inputString){
      int redirecIndex = -1;
      ArrayList<ArrayList<String>> resultList =
          new ArrayList<ArrayList<String>>();
      ArrayList<String> analyzedList = analyzedCommand(inputString);
      ArrayList<String> commandList = new ArrayList<String>();
      ArrayList<String> redirection = new ArrayList<String>();
      for (int index = 0; index < analyzedList.size(); index++) {
        // append ">" into redirection list
        if (analyzedList.get(index).equals(">")) {
          redirection.add(">");
          redirecIndex = index;
          
        }
        // append ">>" into redirection list
        else if (analyzedList.get(index).equals(">>")){
          redirection.add(">>");
          redirecIndex = index;
        }
        else {
          // append redirection path
          if (redirecIndex != -1 & index == redirecIndex + 1) {
            redirection.add(analyzedList.get(index));
          }
          // append normal command
          else {
            commandList.add(analyzedList.get(index));
          }
        }
      }
      resultList.add(commandList);
      resultList.add(redirection);
      return resultList;
    }
}
