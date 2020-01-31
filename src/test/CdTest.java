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

import static org.junit.Assert.*;

import SystemAndOperation.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Test the Cd command in the JShell
 * @author Jiasong Liang
 * @author liang101
 */

public class CdTest {

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
    public void cdwithrelativePath() {
      String order1 = "mkdir a1 a2 a3";
      String order2 = "cd a1";
      String order3 = "pwd";
      String res = "/a1\r\n";
      os.setInput(order1, os);
      os.setInput(order2, os);
      os.setInput(order3, os);
      assertNotEquals(null, os.getRoot().getDirectoryByName("a1"));
        assertNotEquals(null, os.getRoot().getDirectoryByName("a2"));
        assertNotEquals(null, os.getRoot().getDirectoryByName("a3"));
      assertEquals(res, getOutput());
    }

    @Test
    public void cdWithAbsPath() {
      String order1 = "mkdir b1";
      String order2 = "mkdir /b1/b2";
      String order3 = "mkdir /b1/b2/b3";
      String order4 = "cd /b1/b2/b3";
      String order5 = "pwd";
      String res = "/b1/b2/b3\r\n";
      os.setInput(order1, os);
      os.setInput(order2, os);
      os.setInput(order3, os);
      os.setInput(order4, os);
      os.setInput(order5, os);
      assertEquals(res, getOutput());
    }
}
