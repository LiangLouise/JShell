package SystemAndOperation;

import java.util.ArrayList;

/**
 * Represent the command ! NUMBER
 *
 * @author Jiasong Liang
 * @author liang101
 */

public class Number extends Command {

    // The length of arg is 1
    private static final int REQUIRED_LENGTH = 1;

    // The smallest index of the history list
    private static final int MINIMUM_INDEX = 0;

    // The index of the history command
    private int hisIndex;

    // The history command arrayList
    private ArrayList<String> history;

    /**
     * default constructor that takes a list of input targets,
     * validate them and execute the previous command at the index with
     * respect to the number input
     *
     * @param listOfInputTargets The args part of the input command string
     * @param os                 the OperateSystem object.
     */
    public Number(ArrayList<String> listOfInputTargets, OperateSystem os) {
        super(listOfInputTargets, os);
        this.operateSystem();
    }

    /**
     * When the command is valid, execute the previous command at the input
     * index again.
     *
     * @return true, if the command is executed successfully
     */

    private boolean operateSystem() {
        if (this.isValid()) {
            // Get the previous command which will be executed again
            String preCommand = this.history.get(hisIndex);
            // Execute the command again
            operateSys.setInput(preCommand, operateSys);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if the length of argument is 1. when the length is correct,
     * check the argument can be converted to be an integer and is within the
     * range of historyList
     *
     * @return true, if the argument is valid
     */

    private boolean isValid() {
        // Convert the string value to the integer and reduce 1 to get
        // the index
        try {
            hisIndex = Integer.valueOf(this.listOfTargets.get(0)) - 1;
            history = operateSys.getHistory();
            // Check if it is a valid index within the range
            if (hisIndex >= MINIMUM_INDEX && hisIndex < history.size() -
                    1) {
                return true;
            } else {
                // ERROR TYPE: exceed the index range
                this.output.setError(Error.History1);
                this.output.printError();
                return false;
            }
        } catch (NumberFormatException e) {
            // ERROR TYPE: Invalid argument: the argument is not a
            // integer
            this.output.setError(Error.History2);
            this.output.printError();
            return false;
        }
    }
}
