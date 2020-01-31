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
public class CatTest {

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
  public void testInvalidInput(){
    String order1 = "echo 123 > f1";
    String order2 = "cat error1";
    String res = "error1: Invalid path,"
        + " valid contents shown after the error part.\r\n\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    assertEquals(res, getOutput());
  }

  @Test
  // unsuccessful case
  public void testInvalidInput2() {
    String order1 = "echo 123 > f1";
    String order2 = "cat f1 error1";
    String res = "error1: Invalid path,"
        + " valid contents shown after the error part.\r\n123\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    assertEquals(res, getOutput());

  }

  @Test
  public void testRelativePath1() {
    String order1 = "echo 123 > f1";
    String order2 = "cat f1";
    String res = "123\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    assertEquals(res, getOutput());
  }
  
  @Test
  public void testRelativePath2() {
    String order1 = "echo 123 > f1";
    String order2 = "echo 456 > f2";
    String order3 = "cat f1 f2";
    String res = "123\n\n\n456\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    assertEquals(res, getOutput());
    
  }
  @Test
  public void testRelativePathWithError() {
    String order1 = "echo 123 > f1";
    String order2 = "echo 456 > f2";
    String order3 = "cat f1 error1 f2";
    String res = "error1: Invalid path,"
        + " valid contents shown after the error part.\r\n" + 
        "123\n\n\n456\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    assertEquals(res, getOutput());
    
  }
  @Test
  public void testRelativeAndAbosolutePath1() {
    String order1 = "echo 123 > f1";
    String order2 = "mkdir d1";
    String order3 = "cd d1";
    String order4 = "echo 456 > f2";
    String order5 = "cat /f1 /d1/f2";
    String res = "123\n\n\n456\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    os.setInput(order5, os);
    assertEquals(res, getOutput());    
  }
  @Test
  public void testRelativeAndAbosolutePath2() {
    String order1 = "echo 123 > f1";
    String order2 = "echo 234 > f3";
    String order3 = "mkdir d1";
    String order4 = "cd d1";
    String order5 = "echo 456 > f2";
    String order6 = "cat /f1 /d1/f2 ../f3";
    String res = "123\n\n\n456\n\n\n234\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    os.setInput(order5, os);
    os.setInput(order6, os);
    assertEquals(res, getOutput());    
  }
}
