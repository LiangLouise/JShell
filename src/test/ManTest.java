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

/** Represents the running class which has the main method
 * @author Hao Liang
*/
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import SystemAndOperation.OperateSystem;

public class ManTest {
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;

  private ByteArrayInputStream testIn;
  private ByteArrayOutputStream testOut;
  private OperateSystem os = OperateSystem.connectToFileSystem();

  @Before
  public void setUpOutput() {

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
  // ls descriptions and usage
  public void testValidInput1(){
    String order1 = "man ls";
    int expected = 410;
    os.setInput(order1, os);
    assertEquals(expected, getOutput().length());
  }

  @Test
  // pwd descriptions and usage
  public void testValidInput2(){
    String order1 = "man pwd";
    int expected = 146;
    os.setInput(order1, os);
    assertEquals(expected, getOutput().length());
  }
  
  @Test
  // tree descriptions and usage
  public void testValidInput3(){
    String order1 = "man tree";
    int expected = 137;
    os.setInput(order1, os);
    assertEquals(expected, getOutput().length());
  }
  
  @Test
  // invalid output, too much arguments entered
  public void testInValidInput1(){
    String order1 = "man pwd ls";
    String expected = "Wrong number of arguments"
        + " entered for MAN command\n";
    os.setInput(order1, os);
    assertEquals(expected, getOutput());
  }
  
  @Test
  // invalid output no arguments entered
  public void testInValidInput2(){
    String order1 = "man";
    String expected = "Wrong number of arguments"
        + " entered for MAN command\n";
    os.setInput(order1, os);
    assertEquals(expected, getOutput());
  }
  
  
}
