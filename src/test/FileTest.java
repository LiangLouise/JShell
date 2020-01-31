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

import SystemAndOperation.Directory;
import SystemAndOperation.File;
import org.junit.Before;
import org.junit.Test;

/**
 * Test File class in the JShell
 * @author Jiasong Liang
 * @author liang101
 */

class FileTest {

  private Directory root = new Directory(null, "/");
  private Directory a1 = new Directory(root, "a1");
  private File fileWithoutContent = new File(root, "fileWithoutContent");
  private File fileWithContent = new File(root, "fileWithContent", "hello");
  private Directory a2 = new Directory(a1, "a2");

  @Before
  public void setUp() {
    root.addChild(a1);
    root.addChild(fileWithoutContent);
    root.addChild(fileWithContent);
    a1.addChild(a2);
  }

  @Test
  public void testGetEmptyContent(){
    assertEquals("", fileWithoutContent.getContent());
  }

  @Test
  public void testRewriteContent() {
    String res = "good bye";
    fileWithoutContent.rewriteContent(res);
    assertEquals(res, fileWithoutContent.getContent());
  }

  @Test
  public void testAppendContent() {
    String res = "World";
    fileWithContent.appendContent(res);
    assertEquals("hello"+res, fileWithContent.getContent());
  }
  
  @Test
  public void testGetCopy() {
    File file3 = fileWithContent.copy(root);
    assertEquals(file3, root.getFileByName("fileWithContent"));
  }
}