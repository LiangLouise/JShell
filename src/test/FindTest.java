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
/** Represents find test.
 * @author Shinan Liu
*/
public class FindTest {

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
  public void testInvalidPathWithValidPathForFile(){
    String order1 = "echo 123 > f1";
    String order2 = "echo 123 > f3";
    String order3 = "mkdir d1 d2";
    String order4 = "cd d1";
    String order5 = "echo 123 > f1";
    String order6 = "find / /d1 /d2 -type f -name \"f1\"";
    String res = "Not found: /d2/f1\r\nFound: /f1\nFound: /d1/f1\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    os.setInput(order5, os);
    os.setInput(order6, os);
    assertEquals(res, getOutput());
  }

  @Test
  public void testFindFilesAllValid() {
    String order1 = "echo 123 > f1";
    String order2 = "echo 123 > f3";
    String order3 = "mkdir d1 d2";
    String order4 = "cd d1";
    String order5 = "echo 123 > f1";
    String order6 = "find / /d1 /d2 -type f -name \"f1\"";
    String res = "Not found: /d2/f1\r\nFound: /f1\nFound: /d1/f1\r\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    os.setInput(order5, os);
    os.setInput(order6, os);
    assertEquals(res, getOutput());
  }
  @Test
  public void testFindDirectorysAllValid() {
    String order3 = "mkdir d1 d2";
    String order4 = "cd d2";
    String order5 = "echo 123 > d1";
    String order6 = "find / /d1 /d2 -type d -name \"d1\"";
    String res = "Not found: /d1/d1\r\nNot found: /d2/d1\r\nFound: /d1\r\n";
    os.setInput(order3, os);
    os.setInput(order4, os);
    os.setInput(order5, os);
    os.setInput(order6, os);
    assertEquals(res, getOutput());
  }
  @Test
  public void testFindDirectorysSomeValid() {
    String order3 = "mkdir d1 d2";
    String order4 = "cd d2";
    String order5 = "echo 123 > d1";
    String order6 = "find / /d /d2 -type d -name \"d1\"";
    String res = "Not found: /d/d1\r\nNot found: /d2/d1\r\nFound: /d1\r\n";
    os.setInput(order3, os);
    os.setInput(order4, os);
    os.setInput(order5, os);
    os.setInput(order6, os);
    assertEquals(res, getOutput());
  }

}
