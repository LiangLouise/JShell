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

import SystemAndOperation.File;
import org.junit.Before;
import org.junit.Test;
import SystemAndOperation.Directory;

public class DirectoryTest {

  private Directory root = new Directory(null, "/");
  private Directory a1 = new Directory(root, "a1");
  private File file1 = new File(root, "file1");
  private Directory a2 = new Directory(a1, "a2");

  @Before
  public void setUp() {
    root.addChild(a1);
    root.addChild(file1);
    a1.addChild(a2);
  }
 
  @Test
  // successful case
  public void testGetDirectoryByName1() {
    assertEquals(a1, root.getDirectoryByName("a1"));
  }
  
  @Test
  // unsuccessful case
  public void testGetDirectoryByName2() {
    assertEquals(null, root.getDirectoryByName("a2"));
    
  }
  
  @Test
  // successful case
  public void testGetFileByName1() {
    assertEquals(file1, root.getFileByName("file1"));
  }
  
  @Test
  // successful case
  public void testGetFileByName2() {
    assertEquals(null, root.getFileByName("file2"));
  }

  @Test
  public void testRemoveDirectoryByName() {
    root.removeDirectoryByName("a1");
    assertNull(root.getDirectoryByName("a1"));
  }

  @Test
  public void testRemoveFileByName() {
    root.removeFileByName("file1");
    assertNull(root.getFileByName("a1"));
  }

  @Test
  // successful case
  public void testContainsDIR1() {
      assertTrue(root.containsDir());
  }

  @Test
  public void testContainsDIR2() {
      assertTrue("root has DIR a1",root.containsDir());
      assertFalse("a2 has no DIR",a2.containsDir());
  }

  @Test
  public void testCopy() {
      Directory a3 =  a2.copy(root);
      assertEquals(a3, root.getDirectoryByName("a2"));
  }

  @Test
  public void testIsNotFile() {
    assertTrue(!root.getIsFile());
  }

  @Test
  public void testGetNonParent() {
    assertEquals(null, root.getParent());
  }

  @Test
  public void testGetParent() {
      assertEquals(root, a1.getParent());
  }

  @Test
    public void testSetName() {
      String newName = "a11";
      a1.setName(newName);
      assertEquals(newName, a1.getName());
  }

  @Test
    public void testGetPath() {
      String path = "/a1";
      assertEquals(path, a1.getPath());
  }
}
