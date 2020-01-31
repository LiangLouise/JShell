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

/** The class to get the history of input in JShell
 * @author Jiasong Liang
 * @author liang101
 */
public class History extends Command{

	public History(ArrayList<String> listOfTargets, OperateSystem os) {
		super(listOfTargets, os);
		this.operateSystem();
	}

	/**
	 * Check if the args matches the requirements that history
	 * command can have zero following input or an int input
	 * @return true if the input size is right
	 */
	private boolean isValid() {
		return this.listOfTargets.size() <= 1;
	}

	/**
	 * Operate with the input command. If it is valid, generate and print out
	 * the history of input with two columns. The first one shows how recent
	 * the command is input, the most recent one will have the largest num.
	 * The second column shows the actual content of the command
	 * @return true if the table of history can be generated.
	 */
	private boolean operateSystem() {
		if(this.isValid()) {
			// If requesting to get the entire history
			if(this.listOfTargets.size() == 0) {
				return this.operateWithoutNum();
			}
			// If request to get partial history
			else {
				return this.operateWithNum();
			}
		} else {
			this.output.setError(Error.History3);
			this.output.printError();
			return false;
		}

	}

	/**
	 * Construct the table of command history as described above.
	 * But this func will construct the table with the commands at startIndex
	 * and end at the endIndex
	 * @param startIndex an int representing the startIndex
	 * @param endIndex an int representing the endIndex
	 * @return true, if the table can be created
	 */
	private String constructHistory(int startIndex, int endIndex) {
		ArrayList<String> histroy = this.operateSys.getHistory();
		StringBuilder historyInfo = new StringBuilder();
		int i = startIndex;
		while(i < endIndex) {
			//Create each row of the table
			historyInfo.append(String.valueOf(i +1) + "." + histroy.get(i)
					+ "\n");
			i++;
		}
		return historyInfo.toString();
	}

	/**
	 * When input is with an integer <Num>. create the table of history 
	 * with the commands from the last one to the one <Num> times before it. 
	 * If is not an int, this will not create a table
	 * @return true, if the table can be created
	 */
	private boolean operateWithNum() {
		String res;
		try {
			int historynum = Integer.parseInt(this.listOfTargets.get(0));
			// If the input number is out of range or an negative int
			if(historynum > this.operateSys.getHistory().size()
					|| historynum <=0) {
				this.output.setError(Error.History1);
				this.output.printError();
				return false;
			} else {
				// Construct the table from the command at end index minus the
				// input number to the last command
				res = this.constructHistory(
						this.operateSys.getHistory().size() - historynum,
						this.operateSys.getHistory().size());
				this.output.setContent(res.substring(0, res.length() -1));
				this.output.giveContent();
				return true;
			}
			//If the input number is not a valid int
		} catch (NumberFormatException e) {
			this.output.setError(Error.History2);
			this.output.printError();
			return false;
		}
	}

	/**
	 * Create the table with all the commands stored in the history list
	 * @return true, if the table can be created
	 */
	private boolean operateWithoutNum() {
		String res;
		// Construct the table from the first command to the last one
		res = this.constructHistory(0, this.operateSys.getHistory().size());
		this.output.setContent(res.substring(0, res.length() - 1));
		this.output.giveContent();
		return true;
	}

}
