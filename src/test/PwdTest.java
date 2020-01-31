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
public class PwdTest {

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
  public void PwdRootTest(){
    String order1 = "mkdir d1 d2";
    String order4 = "pwd";
    String res = "/\r\n";
    os.setInput(order1, os);
    os.setInput(order4, os);
    assertEquals(res, getOutput());
  }

  @Test
  // unsuccessful case
  public void PwdDeepPathWithBranchTest() {
    String order1 = "mkdir d1 d2";
    String order2 = "mkdir /d1/d2 /d2/d3";
    String order21 = "mkdir /d1/d2/d3";    
    String order3 = "mkdir /d1/d2/d3/d4";
    String order31 = "cd /d1/d2/d3/d4";
    String order4 = "pwd";
    String res = "/d1/d2/d3/d4\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order21, os);
    os.setInput(order3, os);
    os.setInput(order31, os);
    os.setInput(order4, os);
    assertEquals(res, getOutput());
  }


}
