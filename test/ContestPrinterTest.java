import ac_library.ContestPrinter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Test;

public class ContestPrinterTest {
    // methods test
    @Test
    public void printTestExFloat() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.print((byte) 1);
        cp.print((byte) -1);
        cp.print('1');
        cp.print((short) 1);
        cp.print((short) -1);
        cp.print((int) 1);
        cp.print((int) -1);
        cp.print((long) 1);
        cp.print((long) -1);
        cp.print(true);
        cp.print(false);
        cp.print("abcd");
        cp.flush();
        Assert.assertEquals("1-111-11-11-1truefalseabcd", out.toString());
        System.setOut(System.out);
    }

    @Test
    public void printlnTestExFloat() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.println((byte) 1);
        cp.println((byte) -1);
        cp.println('1');
        cp.println((short) 1);
        cp.println((short) -1);
        cp.println((int) 1);
        cp.println((int) -1);
        cp.println((long) 1);
        cp.println((long) -1);
        cp.println(true);
        cp.println(false);
        cp.println("abcd");
        cp.flush();
        Assert.assertEquals("1\n-1\n1\n1\n-1\n1\n-1\n1\n-1\ntrue\nfalse\nabcd\n", out.toString());
        System.setOut(System.out);
    }

    @Test
    public void doublePrintTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.print(123.456);
        cp.flush();
        String s = out.toString();
        Assert.assertTrue(s.length() >= 20);
        Assert.assertEquals(Double.parseDouble(s), 123.456, 1e-9);
        System.setOut(System.out);
    }

    @Test
    public void doublePrintlnTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.println(123.456);
        cp.flush();
        String s = out.toString();
        Assert.assertTrue(s.endsWith("\n"));
        Assert.assertTrue(s.length() >= 20);
        Assert.assertEquals(Double.parseDouble(s), 123.456, 1e-9);
        System.setOut(System.out);
    }

    @Test
    public void floatPrintTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.print(123.456f);
        cp.flush();
        String s = out.toString();
        Assert.assertTrue(s.length() >= 20);
        Assert.assertEquals(Float.parseFloat(s), 123.456f, 1e-9);
        System.setOut(System.out);
    }

    @Test
    public void floatPrintlnTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.println(123.456f);
        cp.flush();
        String s = out.toString();
        Assert.assertTrue(s.endsWith("\n"));
        Assert.assertTrue(s.length() >= 20);
        Assert.assertEquals(Float.parseFloat(s), 123.456f, 1e-9);
        System.setOut(System.out);
    }

    @Test
    public void printlnArrayTestInt() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.printlnArray(new int[] {1, 2, 3}, "$");
        cp.flush();
        Assert.assertEquals("1$2$3\n", out.toString());
        System.setOut(System.out);
    }

    @Test
    public void printlnArrayTest2Int() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.printlnArray(new int[] {1, 2, 3});
        cp.flush();
        Assert.assertEquals("1 2 3\n", out.toString());
        System.setOut(System.out);
    }

    @Test
    public void printlnArrayTestLong() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.printlnArray(new long[] {1, 2, 3}, "$");
        cp.flush();
        Assert.assertEquals("1$2$3\n", out.toString());
        System.setOut(System.out);
    }

    @Test
    public void printlnArrayTest2Long() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        cp.printlnArray(new long[] {1, 2, 3});
        cp.flush();
        Assert.assertEquals("1 2 3\n", out.toString());
        System.setOut(System.out);
    }

    // additional tests
    @Test
    public void printNumberTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        StringBuilder builder = new StringBuilder();
        for (int i = -1000000; i <= 1000000; i++) {
            cp.print(i);
            builder.append(i);
        }
        cp.flush();
        Assert.assertEquals(builder.toString(), out.toString());
        System.setOut(System.out);
    }

    @Test
    public void printNumberTestNearLongMax() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        StringBuilder builder = new StringBuilder();
        for (long i = Long.MAX_VALUE - 10; i < Long.MAX_VALUE; i++) {
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
    public void printNumberTestNearLongMin() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        StringBuilder builder = new StringBuilder();
        for (long i = Long.MIN_VALUE; i < Long.MIN_VALUE + 10; i++) {
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
    public void printnumberTestNearIntMax() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        StringBuilder builder = new StringBuilder();
        for (int i = Integer.MAX_VALUE - 10; i < Integer.MAX_VALUE; i++) {
            cp.print(i);
            builder.append(i);
        }
        cp.print(Integer.MAX_VALUE);
        builder.append(Integer.MAX_VALUE);
        cp.flush();
        Assert.assertEquals(builder.toString(), out.toString());
        System.setOut(System.out);
    }

    @Test
    public void printNumberTestNearIntMin() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        ContestPrinter cp = new ContestPrinter();
        StringBuilder builder = new StringBuilder();
        for (int i = Integer.MIN_VALUE; i < Integer.MIN_VALUE + 10; i++) {
            cp.print(i);
            builder.append(i);
        }
        cp.print(Integer.MIN_VALUE);
        builder.append(Integer.MIN_VALUE);
        cp.flush();
        Assert.assertEquals(builder.toString(), out.toString());
        System.setOut(System.out);
    }

    @Test
    public void printDoubleRangeTest() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (double i = -100.123; i <= 100.123; i += 0.123) {
            System.setOut(new PrintStream(out));
            ContestPrinter cp = new ContestPrinter();
            cp.println(i);
            cp.flush();
            Assert.assertEquals(Double.parseDouble(out.toString()), i, 1e-9);
            out.reset();
            System.setOut(System.out);
        }
    }
}
