import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import ac_library.ContestScanner;

public class ContestScannerTest {
    @Test
    public void numberInputTest() {
        String input = "-3\n   -2     -1  0\n  \n\n1 2  3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        ContestScanner sc = new ContestScanner();
        for(int i = -3;i <= 3;i++){
            Assert.assertEquals(i, sc.nextLong());
        }

        System.setIn(System.in);
    }

    @Test
    public void numberLongMaxMinTest() {
        String input = String.format("%d %d", Long.MAX_VALUE, Long.MIN_VALUE);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner();
        Assert.assertEquals(Long.MAX_VALUE, sc.nextLong());
        Assert.assertEquals(Long.MIN_VALUE, sc.nextLong());

        System.setIn(System.in);
    }

    @Test
    public void numberLongOverflowTest() {
        String input = String.format("9223372036854775808 -9223372036854775809", Long.MAX_VALUE, Long.MAX_VALUE);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner();
        Assert.assertThrows(ArithmeticException.class, () -> { sc.nextLong();});
        Assert.assertThrows(ArithmeticException.class, () -> { sc.nextLong();});

        System.setIn(System.in);
    }

    @Test
    public void numberIntMaxMinTest() {
        String input = String.format("%d %d", Integer.MAX_VALUE, Integer.MIN_VALUE);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner();
        Assert.assertEquals(Integer.MAX_VALUE, sc.nextInt());
        Assert.assertEquals(Integer.MIN_VALUE, sc.nextInt());

        System.setIn(System.in);
    }

    @Test
    public void numberIntOverflowTest() {
        String input = String.format("2147483648 -2147483649", Integer.MAX_VALUE, Integer.MAX_VALUE);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner();
        Assert.assertThrows(NumberFormatException.class, () -> { sc.nextInt();});
        Assert.assertThrows(NumberFormatException.class, () -> { sc.nextInt();});

        System.setIn(System.in);
    }

    @Test
    public void readNextTest() {
        String input = "abc def \n 123 \r\nAlphabet \n \n\n !#$%&'()=~|`{+*}<>?_\n\n  \n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner();
        assertTrue(sc.hasNext());
        Assert.assertEquals("abc", sc.next());
        assertTrue(sc.hasNext());
        Assert.assertEquals("def", sc.next());
        assertTrue(sc.hasNext());
        Assert.assertEquals("123", sc.next());
        assertTrue(sc.hasNext());
        Assert.assertEquals("Alphabet", sc.next());
        assertTrue(sc.hasNext());
        Assert.assertEquals("!#$%&'()=~|`{+*}<>?_", sc.next());
        assertTrue(sc.hasNext() == false);
    }

    @Test
    public void readNextLongTest() {
        String input = "abc";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner();
        Assert.assertThrows(NumberFormatException.class, () -> { sc.nextLong();});
    }
}
