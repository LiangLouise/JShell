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
import SystemAndOperation.*;
import static org.junit.Assert.assertEquals;
import java.io.*;
import org.junit.*;
public class LsTest {

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
  // empty directory from root
  public void testValidInput(){
    String order1 = "ls";
    String res = "";
    os.setInput(order1, os);
    assertEquals(res, getOutput());
    System.out.println("Case Done");
  }
  
  @Test
  // normal ls situation
  public void testValidInput2(){
    String order1 = "mkdir dir1";
    String order2 = "mkdir dir2";
    String order3 = "ls";
    String res = "dir1 dir2\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    assertEquals(res, getOutput());
  }
  
  @Test
  // back to parent directory
  public void testValidInput3(){
    String order1 = "mkdir dir1";
    String order2 = "mkdir dir2";
    String order3 = "cd dir1";
    String order4 = "ls ..";    
    String res = "/: dir1 dir2\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    assertEquals(res, getOutput());
  }
  
  @Test
  // empty child directory
  public void testValidInput4(){
    String order1 = "mkdir dir1";
    String order2 = "mkdir dir2";
    String order3 = "cd dir1";
    String order4 = "ls";    
    String res = "";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    assertEquals(res, getOutput());
  }
  
  @Test
  // absolute directory
  public void testValidInput5(){
    String order1 = "mkdir dir1";
    String order2 = "mkdir dir2";
    String order3 = "cd dir1";
    String order4 = "mkdir dir3";
    String order5 = "mkdir dir4";
    String order6 = "cd dir3";
    String order7 = "ls /dir1";  
    String res = "dir1: dir3 dir4\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    os.setInput(order5, os);
    os.setInput(order6, os);
    os.setInput(order7, os);
    assertEquals(res, getOutput());
  }
  
  @Test
  // recursive
  public void testValidInput6(){
    String order1 = "mkdir dir1";
    String order2 = "mkdir dir2";
    String order3 = "cd dir1";
    String order4 = "mkdir dir3";
    String order5 = "mkdir dir4";
    String order6 = "cd dir3";
    String order7 = "ls -R /dir1";  
    String res = "/dir1:\ndir3 dir4\n/dir1/dir3:"
        + "\n\n/dir1/dir4:\n\n\n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    os.setInput(order5, os);
    os.setInput(order6, os);
    os.setInput(order7, os);
    assertEquals(res, getOutput());
  }
  
  @Test
  // multiple inputs
  public void testValidInput7(){
    String order1 = "mkdir dir1";
    String order2 = "mkdir dir2";
    String order3 = "cd dir1";
    String order4 = "mkdir dir3";
    String order5 = "mkdir dir4";
    String order6 = "cd dir3";
    String order7 = "ls /dir1 /dir2";  
    String res = "dir1: dir3 dir4\ndir2: \n";
    os.setInput(order1, os);
    os.setInput(order2, os);
    os.setInput(order3, os);
    os.setInput(order4, os);
    os.setInput(order5, os);
    os.setInput(order6, os);
    os.setInput(order7, os);
    assertEquals(res, getOutput());
  }
}
