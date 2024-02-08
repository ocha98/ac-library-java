import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import ac_library.ContestScanner;

public class ContestScannerTest {
    @Test
    public void numberInputTest1() {
        String input = "-3\n   -2     -1  0\n  \n\n1 2  3\n \n   ";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        ContestScanner sc = new ContestScanner(true);
        for(int i = -3;i <= 3;i++){
            Assert.assertEquals(i, sc.nextLong());
        }
        Assert.assertEquals(sc.hasNext(), false);
        System.setIn(System.in);
    }

    @Test
    public void numberInputTest2() {
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = -1000000; i <= 1000000; i++) {
            inputBuilder.append(i).append(" ");
        }

        String input = inputBuilder.toString().trim();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner(true);
        for (int i = -1000000; i <= 1000000; i++) {
            Assert.assertEquals(i, sc.nextLong());
        }

        System.setIn(System.in);
    }
    @Test
    public void numberInputTest3() {
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = -1000000; i <= 1000000; i++) {
            inputBuilder.append(i).append("\n");
        }

        String input = inputBuilder.toString().trim();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner();
        for (int i = -1000000; i <= 1000000; i++) {
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
        StringBuilder inputBuilder = new StringBuilder();
        {
            BigInteger bigIntMax = BigInteger.valueOf(Long.MAX_VALUE);
            for (int i = 0; i < 20; i++) {
                bigIntMax = bigIntMax.add(BigInteger.ONE);
                inputBuilder.append(bigIntMax.toString()).append(" ");
            }
            BigInteger bigIntMin = BigInteger.valueOf(Long.MIN_VALUE);
            for (int i = 0; i < 20; i++) {
                bigIntMin = bigIntMin.subtract(BigInteger.ONE);
                inputBuilder.append(bigIntMin.toString()).append(" ");
            }
        }
        String input = inputBuilder.toString().trim();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner();
        for(int i = 0;i < 40; ++i){
            Assert.assertThrows("i = "+ i, NumberFormatException.class, () -> { sc.nextLong();});
        }
        Assert.assertEquals(sc.hasNext(), false);
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
    
    @Test
    public void readLongArrayTest() {
        String input = "3\n1 2 3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ContestScanner sc = new ContestScanner();
        int n = sc.nextInt();
        long[] a = sc.nextLongArray(n);
        Assert.assertArrayEquals(new long[]{1, 2, 3}, a);
        Assert.assertEquals(sc.hasNext(), false);
    }

    @Test
    public void readLongMatrixTest() {
        String input = "1 2 3\n4 5 6\n7 8 9";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        ContestScanner sc = new ContestScanner();
        int h = 3, w = 3;
        long[][] a = sc.nextLongMatrix(h, w);
        Assert.assertArrayEquals(new long[]{1, 2, 3}, a[0]);
        Assert.assertArrayEquals(new long[]{4, 5, 6}, a[1]);
        Assert.assertArrayEquals(new long[]{7, 8, 9}, a[2]);
        Assert.assertEquals(sc.hasNext(), false);
    }
}
