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
public enum ManEnum {
	
	LS ("ls","For each operand that names a file of a type other than"
	    + " directory, ls displays its path.  "
			+ "For each operand that names a file of type directory,"
			+ " ls displays the path of that file"
			+ " followed by a colon and the contents of that directory,"
			+ " then an extra new line." ,
			"ls [PATH ...],ls,ls /users/Desktop,ls /users/Desktop/newFile.txt"
			),
	MAN("man", "Show the user manul for users to be familiar"
	    + " with specific command usage in JShell",
			"man [argument],man ls,man mkdir"),
	NONE("none","default method with internal usage only",
			"none"),
	PWD ("pwd", "Print current working"
	    + " directory's absolute path to the console",
			"pwd"),
	CAT ("cat", "When file paths are inputed, print out the file content"
	    + "into the console, then a path is invalid, print out error"
	    + "and keep printing other valid files",
			"cat [PATH ...],cat /users/Desktop/newFile.txt"),
	CD ("cd", "Change the current the working directory with the input path",
			"cd [PATH ...],cd /users/Desktop"),
	MKDIR("mkdir","Make directory with given directory name",
			"mkdir [dirName],mkdir f1"),
	EXIT("exit","End and exit the JShell",
			"exit"),
	PUSHD("pushd","Push the current directory to the history stack,"
	    + "and change current working directory to the new directory",
	        "pushd [PATH of a directory],pushd /users/Desktop"),
    POPD("popd","Pop from the history stack to move back to the previous"
        + "directory",
	        "popd"),
    FIND("find","Find files or diretcorys that have the exact name as input"
        + "within the input paths, print out found: [path] for each found"
        + "file or directory, and print not found: [path] for each"
        + " not found directory or file."
        + " WARN: file or directory name must be quoted by double quotes.",
            "find [PATH...] -type [f|d] name \"expression\""),
	TREE("tree", "Print out the directories and files sotred in the root",
			"tree"),
	ECHO("echo", "Print string or put string into file OUTFILE",
	    "echo string [>OUTFILE], echo string >> OUTFILE"),
	HISTORY("history", "Print out the history of"
	    + " command input. If [num] is " +
			"given, the last [num] of command will be printed out"
			, "history [Num]"),
	GREP("grep", "When -R not implied, "
	    + "print lines that contain the input regex in the input file path,"
	    + " when -R is implied,if input is a directory,"
	    + "recursively check lines in each file "
	    + "in input directory, print each line with their path; if input path"
	    + "is a file, print lines that contain the input regex in the input"
	    + "file. WARN: REGEX must be quoted in double quotes",
	    "grep [a-z]* File.., grep -R [a-z]* Directory.."),
	CURL("curl", "Retrieve the file at that URL and add it to the current " +
			"working directory. Then name this file after the fileName the URL."
			, "curl http:/example.com/example.txt"),
	NUMBER("![number]", "This command will recall any of previous history by " +
			"its number and execute it again",
			"if the third command input is 'mkdir abc', !3 will execute mkdir" +
					" abc again"),
	CP("cp", "copy item from a given path to another path,"
	    + "Both OLDPATH and NEWPATH may be" + 
	    "relative to the current directory or may be full paths,"
	    + "If OLDPATH is a directory, recursively copy the contents",
	     "cp OLDPATH NEWPATH");
  

	// private value of errorMessage where
    //each enum error has to show to the users
	private String name;
	private String description;
	private String example;
	
	/**
	 * default constructor setting all methods implemented in JShell
	 * 
	 * @param inputErrorMessage
	 */
	ManEnum(String inputName, String inputDescription, String inputExample) {
		name = inputName;
		description = inputDescription;
		example = inputExample;
	}
	
	/**
	 * mutate the description to a user friendly format
	 * set the line limit length to be 80 char
	 * 
	 * @param inputDescription
	 * @return standardDescription
	 */
	public String mutateDescription(String inputDescription) {
		// header for better presentation
		String standardDescription = "Description:\n";
		// storage container for input message
		String subDescription = "";
		for (int index = 0; index < inputDescription.length(); index++) {
			subDescription = subDescription.concat(Character.toString(
					inputDescription.charAt(index)));
			if (subDescription.length() == 60) {
				// add subDescription to standard then reset subDescription
				standardDescription = standardDescription.concat(
						"\t").concat(subDescription).concat("\n");
				subDescription = "";
			}
		}
		// after the index equal to the length of inputDescription
		standardDescription = standardDescription.concat(
				"\t").concat(subDescription).concat("\n");
		return standardDescription;
	}
	
	/**
	 * mutate inputExample and return a standard
	 * example component to store in enum
	 * @param inputExample
	 * @return standardExample
	 */
	public String mutateExample(String inputExample) {
		String standardExample = "Examples:\n";
		// the example input are divided by comma,
		// each example should be printed in different lines
		String[] standardExampleList = inputExample.split(",");
		for (String component : standardExampleList) {
			standardExample =
			    standardExample.concat("\t").concat(component).concat("\n");
		}
		return standardExample;
	}
	
	/**
	 * return true if input method are pre-written method in our JShell
	 * 
	 * @param inputMethod
	 * @return
	 */
	public static ManEnum contains(String inputMethod) {
		// default to an constant with no acutual meaning
		ManEnum result = NONE;
		for (ManEnum aEnum : values()) {
			// if input method is already stored in ManEnum
			if (aEnum.getName().equals(inputMethod)) {
				result = aEnum;	
			}
		}
		return result;
	}

	/**
	 * getter function for name variable
	 * [called by Input and others]
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter function for user friendly version Description
	 * [called by Man only]
	 * 
	 * @return mutatedDescription
	 */
	public String getDescription() {
		return mutateDescription(description);
	}

	/**
	 * getter function for user friendly version Example
	 * [called by Man only]
	 * 
	 * @return mutatedExample
	 */
	public String getExample() {
		return mutateExample(example);
	}

	/**
	 * getter function for user friendly version Name
	 * [called by Man only]
	 * 
	 * @return mutatedName
	 */
	public String getMutateName() {
		String standardName = "Name:\n";
		standardName = standardName.concat("\t").concat(name).concat("\n");
		return standardName;
	}
}
