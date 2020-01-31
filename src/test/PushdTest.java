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
/** Represents cat test.
 * @author Shinan Liu
*/
public class PushdTest {

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
  // successful case
  public void PushdOnceRelativePathTest(){
    String order1 = "mkdir d1 d2";
    String order2 = "pushd d1";
    String order3 = "popd";
    String order4 = "pwd";
    String res = "/\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    assertEquals(res, getOutput());
  }

  @Test
  // unsuccessful case
  public void PushdMultipeTestWithAbosulutePathTest() {
    String order1 = "mkdir d1 d2";
    String order2 = "pushd d1";
    String order21 = "pushd /d2";
    String order3 = "popd";
    String order31 = "popd";
    String order4 = "pwd";
    String res = "/\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order21, os);
    os.setInput(order3, os);
    os.setInput(order31, os);
    os.setInput(order4, os);
    assertEquals(res, getOutput());
  }

  @Test
  public void PushdIsTargetDirectoyTest(){
    String order1 = "mkdir d1 d2";
    String order2 = "pushd d1";
    String order3 = "pwd";
    String res = "/d1\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    assertEquals(res, getOutput());
  }
 
}
