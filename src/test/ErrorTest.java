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

import static org.junit.Assert.assertEquals;
import SystemAndOperation.Error;
import org.junit.Test;

class ErrorTest {

  @Test
  void testConstructor() {
    Error defaultError = Error.DEFALUT;
    assertEquals(defaultError, Error.DEFALUT);
  }
  
  @Test
  void testGetErrorMessage() {
    Error defaultError = Error.DEFALUT;
    String errorMessage = defaultError.getErrorMessage();
    String expected = "Invalid command, please"
        + " see usage details using Man command";
    assertEquals(expected, errorMessage);
  }
  
  @Test
  void testSetErrorMessage() {
    Error error = Error.DEFALUT;
    error.setErrorMessage("new customized error message");
    String expected = "new customized error message";
    assertEquals(expected, error.getErrorMessage());
  }
    
   @Test
   void testResetErrorMessage() {
     Error error = Error.DEFALUT;
     error.resetErrorMessage();
     String expected = "";
     assertEquals(expected, error.getErrorMessage());
   }
}
