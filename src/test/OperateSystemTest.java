// **********************************************************
// Assignment2:
package test;

import SystemAndOperation.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.*;
import org.junit.*;
/** Represents cat test.
 * @author Shinan Liu
*/
public class OperateSystemTest {


  // Attention!!!!!!!!
  // Almost every public method is used by one or several commands,
  // so there will not be duplicated testing for those method!
  // I will comment out the command where the method is tested.
  @Test
  public void SingletonTest(){
    OperateSystem os = OperateSystem.connectToFileSystem();
    OperateSystem os2 = OperateSystem.connectToFileSystem();
    assertTrue(os == os2);
  }
  @Test
  public void getStorageTest() {
    // This method is wildly used by commands to find storage,
    // for example, Cat, Find, Grep...
  }
  @Test
  public void getExitTest() {
    // This method is used by Exit
  }
  @Test
  public void doExitTest() {
    // This method is used by Exit
  }
  @Test
  public void getRootTest() {
    // This method is a getter for root in the file system
  }
  @Test
  public void setCurrentDirectoryTest() {
    // This method is used by Cd
  }
  @Test
  public void popDirectoryTest() {
    // This method is used by Popd
  }
  @Test
  public void pushCurrentDirectoryTest() {
    // This method is used by Pushd
  }
  @Test
  public void getHistoryTest() {
    // This method is used by History
  }
  @Test
  public void addHistoryTest() {
    // This method is used by History
  }
  @Test
  public void getCurrentDirTest() {
    // This method is a getter for current working directory
  }
  @Test
  public void searchStorageRelativeTest() {
    // This method is a helper for getStorage()
  }
  @Test
  public void searchStorageAbosuluteTest() {
    // This method is a helper for getStorage()
  }
  @Test
  public void setInputTest() {
    // This method is used by all other commands as a readline reader
  }
  

}
