package driver;

import java.util.ArrayList;

public class Echo extends Command{
    private ArrayList<Boolean> listOfIsFile;
    private ArrayList<Storage> listOfStorage;
    
    public Echo(ArrayList<String> listOfInputTargets, OperateSystem os){
        super(listOfInputTargets, os);
        //run Output's printing method to print the output,
        //whether it's an error or other message
        setListStorage();
        if (isValid()) {
            operateSystem();
        } 
        System.out.println(Output.toString);
    }

    /**
     * a method checking correct number of arguments
     * echo has two different uses such as
     * echo string[>outfile] and echo string >> outfile
     * 
     * 
     * @return
     */
    public boolean isValid() {
        isValid = true;
        // check the number of arguments
        // as the rule of echo says, both 2 and 4 are possible for the size
        // of listOfTargets. Otherwise it will cause an error
        if (listOfTargets.size() != 2 || listOfTargets.size() != 4) {
            isValid = false;
            Error error = Error.ECHO1;
            output.setError(error);
        }else {
            String argument = listOfTargets.get(1);
            // check the usage of quotes
            if(!checkquotes(argument)) {
                isValid = false;
            }
        }
        return isValid;
    }
    
    /**
     * Update the output inside
     * 
     * @return
     */
    public boolean operateSystem() {

        if (listOfTargets.size() == 2) {
            output.setContent(listOfTargets.get(1).replaceAll("\"", ""));
        } else {
            if (listOfTargets.size() == 4) {
                if (listOfTargets.get(2) == ">") {
                    File file = (File) listOfStorage.get(0);
                    file.rewriteContent(listOfTargets.get(1));
                } else if (listOfTargets.get(2) == ">>") {
                    File file = (File) listOfStorage.get(0);
                    file.appendContent(listOfTargets.get(1));
                }
            }
        }

        /**
         * check if there is error about the usage of quote of string
         *
         * @param argument
         * @return
         */

        public boolean checkquotes(String argument){
            // string need to be compared(quote)
            String compareStr = "\"";
            // where we start the searching of quote
            int indexStart = 0;
            int compareStrLength = compareStr.length();
            int count = 0;
            while (true) {
                // if we find a quote, tm is bigger than -1
                int tm = str.indexOf(compareStr, indexStart);
                if (tm >= 0) {
                    count++;
                    //after find a quote, we only need to check the remaining part
                    indexStart = tm + compareStrLength;
                } else {
                    break;
                }
            }
            // if count could be divide by 2, it means every quote has its matches
            // Otherwise, it will cause syntax error
            if (count % 2 == 0) {
                return true;
            } else {
                Error error = Error.ECHO2;
                output.setError(error);
                return false;
            }
        }

        /**
         * set storage for operating the files
         *
         */
        public void setListStorage() {
            listOfIsFile.add(true);
            ArrayList<String> fileArgument = new ArrayList<>();
            fileArgument.add(listOfTargets.get(3));
            listOfStorage = super.operateSys.getStorage(fileArgument, listOfIsFile);
        }
}