import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Assert;
import org.junit.Test;

import ac_library.ContestPrinter;

public class ContestPrinterTest {
    @Test
    public void printTestExFloat() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.print((byte)1);
        cp.print((byte)-1);
        cp.print('1');
        cp.print((short)1);
        cp.print((short)-1);
        cp.print((int)1);
        cp.print((int)-1);
        cp.print((long)1);
        cp.print((long)-1);
        cp.print(true);
        cp.print(false);
        cp.flush();
        Assert.assertEquals("1-111-11-11-1truefalse", out.toString());
        System.setOut(System.out);
    }
    @Test
    public void printlnTestExFloat() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.println((byte)1);
        cp.println((byte)-1);
        cp.println('1');
        cp.println((short)1);
        cp.println((short)-1);
        cp.println((int)1);
        cp.println((int)-1);
        cp.println((long)1);
        cp.println((long)-1);
        cp.println(true);
        cp.println(false);
        cp.flush();
        Assert.assertEquals("1\n-1\n1\n1\n-1\n1\n-1\n1\n-1\ntrue\nfalse\n", out.toString());
        System.setOut(System.out);
    }
    @Test
    public void doublePrintTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.print(1.0);
        cp.flush();
        String s = out.toString();
        Assert.assertTrue(s.length() >= 20);
        Assert.assertTrue(Math.abs(1.0 - Double.parseDouble(out.toString())) < 1e-9);
        System.setOut(System.out);
    }
    @Test
    public void doublePrintlnTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.println(1.0);
        cp.flush();
        String s = out.toString();
        Assert.assertTrue(s.length() >= 20);
        Assert.assertTrue(Math.abs(1.0 - Double.parseDouble(out.toString())) < 1e-9);
        System.setOut(System.out);
    }

    @Test
    public void printNumberTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        StringBuilder builder = new StringBuilder();
        for(int i = -1000000; i <= 1000000; i++){
            cp.print(i);
            builder.append(i);
        }
        cp.flush();
        Assert.assertEquals(builder.toString(), out.toString());
        System.setOut(System.out);
    }
    @Test
    public void printNumberTestNearMax() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        StringBuilder builder = new StringBuilder();
        for(long i = Long.MAX_VALUE - 10; i < Long.MAX_VALUE; i++){
            cp.print(i);
            builder.append(i);
        }
        cp.print(Long.MAX_VALUE);
        builder.append(Long.MAX_VALUE);
        cp.flush();
        Assert.assertEquals(builder.toString(), out.toString());
        System.setOut(System.out);
    }
    @Test
    public void printNumberTestNerMin() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        StringBuilder builder = new StringBuilder();
        for(long i = Long.MIN_VALUE; i < Long.MIN_VALUE + 10; i++){
            cp.print(i);
            builder.append(i);
        }
        cp.print(Long.MIN_VALUE);
        builder.append(Long.MIN_VALUE);
        cp.flush();
        Assert.assertEquals(builder.toString(), out.toString());
        System.setOut(System.out);
    }

    @Test
    public void printStringTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.print("test");
        cp.flush();
        Assert.assertEquals("test", out.toString());
        System.setOut(System.out);
    }

    @Test
    public void printArrayIntTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.printlnArray(new int[]{1, 2, 3}, " ");
        cp.flush();
        Assert.assertEquals("1 2 3\n", out.toString());
        System.setOut(System.out);
    }
    @Test
    public void printArrayLongTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.printlnArray(new long[]{1, 2, 3}, " ");
        cp.flush();
        Assert.assertEquals("1 2 3\n", out.toString());
        System.setOut(System.out);
    }
}
