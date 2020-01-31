package test;

import static org.junit.Assert.*;
import org.junit.Test;
import SystemAndOperation.Output;
import SystemAndOperation.Error;

public class OutputTest {
  
  private Output output = new Output();
  
  @Test
  public void testGetContent() {
    String expected = null;
    assertEquals(expected, output.getContent());
  }
  
  @Test
  public void testSetContent() {
    String expected = "testing output content";
    output.setContent("testing output content");
    assertEquals(expected, output.getContent());
  }
  
  @Test
  public void testSetError() {
    boolean expectedBefore = false;
    assertEquals(expectedBefore, output.hasError());
    Error error = Error.DEFALUT;
    output.setError(error);
    boolean expectedAfter = true;
    assertEquals(expectedAfter, output.hasError());
  }
  
  @Test
  public void testGetError() {
    Error error = Error.DEFALUT;
    output.setError(error);
    String errorMessage = output.getError();
    String expected = "Invalid command, please see usage"
        + " details using Man command";
    assertEquals(expected, errorMessage);
  }

  @Test
  public void testHasError() {
    boolean expectedBefore = false;
    assertEquals(expectedBefore, output.hasError());
    Error error = Error.DEFALUT;
    output.setError(error);
    boolean expectedAfter = true;
    assertEquals(expectedAfter, output.hasError());
  }
  
  
}
