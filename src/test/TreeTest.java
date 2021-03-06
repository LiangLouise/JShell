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
package test;

import SystemAndOperation.*;
import static org.junit.Assert.assertEquals;
import java.io.*;
import org.junit.*;

/**
 * Test the tree command in the JShell
 * @author Jiasong Liang
 * @author liang101
 */
public class TreeTest {
	private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

	private OperateSystem os;

    @Before
    public void setUpOutput() {
        os = OperateSystem.createNewOperateSystem();
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private String getOutput() {
        return testOut.toString();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }
		
	@Test
	public void TestValidCommand() {
		String order1 = "mkdir a1 a2 a3";
		String order2 = "tree";
		String res = "\\\n" +
				"\ta1\n" +
				"\ta2\n" +
				"\ta3\r\n";
		os.setInput(order1, os);
		os.setInput(order2, os);
		assertEquals(res, getOutput());
	}

	@Test
	public void TestInvalidCommand() {
    	String order1 = "tree 1234";
		String res = "Invalid command, please see usage details using Man " +
				"command\r\n";
		os.setInput(order1, os);
		assertEquals(res, getOutput());
	}
  
}
