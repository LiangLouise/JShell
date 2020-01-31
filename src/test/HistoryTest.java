package test;

import static org.junit.Assert.*;

import SystemAndOperation.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Test the History command in the JShell
 * @author Jiasong Liang
 * @author liang101
 */

public class HistoryTest {
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
    public void historywithoutNum() {
        String order1 = "mkdir a1 a2 a3";
        String order2 = "echo 123 > b1";
        String order3 = "history";
        String res = "1.mkdir a1 a2 a3\n2.echo 123 > b1\n3.history\r\n";
        os.setInput(order1, os);
        os.setInput(order2, os);
        os.setInput(order3, os);
        assertEquals(res, getOutput());
    }

    @Test
    public void historywithNum() {
        String order1 = "mkdir a1 a2 a3";
        String order2 = "echo 123 > b1";
        String order3 = "cd a1";
        String order4 = "history 3";
        String res = "2.echo 123 > b1\n3.cd a1\n4.history 3\r\n";
        os.setInput(order1, os);
        os.setInput(order2, os);
        os.setInput(order3, os);
        os.setInput(order4, os);
        assertEquals(res, getOutput());
    }

    @Test
    public void historyOutOfRange(){
        String order1 = "history 3";
        String res = "Number of range, please try again\r\n";
        os.setInput(order1, os);
        assertEquals(res, getOutput());
    }
}
