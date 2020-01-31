package SystemAndOperation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Represent the command curl URL
 *
 * @author Jiasong Liang
 * @author liang101
 */

public class Curl extends Command {

    private static final int REQUIRED_LENGTH = 1;

    private URL target;
    private URLConnection targetConnection;


    /**
     * The default constructor for the Curl command. check the input URL,
     * get the content from that URL and create a file with this content
     * under the current working DIR
     *
     * @param listOfInputTargets The args part of the input command string
     * @param os                 the OperateSystem object.
     */
    public Curl(ArrayList<String> listOfInputTargets, OperateSystem os) {
        super(listOfInputTargets, os);
        this.operateSystem();
    }

    /**
     * When the URL is valid, get the raw content from this URL and create a
     * file with this content named after the fileName of this URL.
     * If there is some file with this name as well, it will be replaced with
     * this one
     *
     * @return true, if work successfully
     */

    private boolean operateSystem() {
        if (this.isValid()) {
            try {
                // Use reader to get the content at each line
                BufferedReader in = new BufferedReader(new InputStreamReader
                        (targetConnection.getInputStream()));
                String fileName = getFileName();
                String curr;
                // Create the new file which will be stored
                File newFile = new File(this.operateSys.getCurrentDir(),
                        fileName);
                this.operateSys.getCurrentDir().removeFileByName(fileName);
                this.operateSys.getCurrentDir().addChild(newFile);
                while ((curr = in.readLine()) != null) {
                    // add each line to the file
                    newFile.appendContent(curr + "\n");
                }
                // Close the reader
                in.close();
                return true;
            } catch (Exception e) {
                // Unable to get file content from the address
                this.output.setError(Error.Curl2);
                this.output.printError();
                return false;
            }
        }
        return false;
    }

    /**
     * Check if the argument length is valid. if length is valid, try to
     * create URL object with input String representing URL.
     *
     * @return true, if URL can be constructed successfully
     */

    private boolean isValid() {
        // Check if the length of argument matches the REQ
        if (this.listOfTargets.size() == REQUIRED_LENGTH) {
            try {
                target = new URL(this.listOfTargets.get(0));
                targetConnection = target.openConnection();
                return true;
            } catch (Exception e) {
                // ERROR TYPE: Invalid address
                this.output.setError(Error.Curl1);
                this.output.printError();
                return false;
            }
        }
        // ERROR TYPE: Invalid argument
        this.output.setError(Error.DEFALUT);
        this.output.printError();
        return false;
    }

    /**
     * Split the given fileName of URL and get only fileName we want
     *
     * @return String representing the fileName
     */
    private String getFileName() {
        String rawName = this.target.getFile();
        String[] nameList = rawName.split("/");
        String name = nameList[nameList.length - 1];
        return name;
    }
}
