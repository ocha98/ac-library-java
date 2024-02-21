import ac_library.ArrayUtils;
import ac_library.Pair;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ArrayUtilsTest {
    @Test
    public void lowerBoundTestInt() {
        int[] a = {0, 1, 2, 3, 4, 5, 6};
        for(int i = 0;i < 7; ++i){
            assertEquals(i, ArrayUtils.lowerBound(a, i));
        }
        assertEquals(0, ArrayUtils.lowerBound(a, -1));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 7));

        a = new int[]{0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 6, 7, 7};
        int[] expected = {0, 1, 3, 6, 7, 10, 11, 12};
        for(int i = 0;i < 8; ++i){
            assertEquals(expected[i], ArrayUtils.lowerBound(a, i));
        }
        assertEquals(0, ArrayUtils.lowerBound(a, -1));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 8));


        a = new int[]{0, 0, 0, 0, 0, 0, 0};
        assertEquals(0, ArrayUtils.lowerBound(a, 0));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 1));

        a = new int[]{0, 0, 0, 0, 0, 0, 1, 1};
        assertEquals(0, ArrayUtils.lowerBound(a, 0));
        assertEquals(6, ArrayUtils.lowerBound(a, 1));

        a = new int[]{-3, -2, -1, 0, 1, 2, 3};
        for(int i = -3;i <= 3; ++i){
            assertEquals(i + 3, ArrayUtils.lowerBound(a, i));
        }
        assertEquals(0, ArrayUtils.lowerBound(a, -4));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 4));

        a = new int[]{1, 2};
        assertEquals(0, ArrayUtils.lowerBound(a, 0));
        assertEquals(0, ArrayUtils.lowerBound(a, 1));
        assertEquals(1, ArrayUtils.lowerBound(a, 2));
        assertEquals(2, ArrayUtils.lowerBound(a, 3));

        a = new int[]{1};
        assertEquals(0, ArrayUtils.lowerBound(a, 1));
        assertEquals(1, ArrayUtils.lowerBound(a, 2));

        a = new int[]{};
        assertEquals(0, ArrayUtils.lowerBound(a, 1));
    }

    @Test
    public void lowerBoundTestLong() {
        long[] a = {0, 1, 2, 3, 4, 5, 6};
        for(int i = 0;i < 7; ++i){
            assertEquals(i, ArrayUtils.lowerBound(a, (long)i));
        }
        assertEquals(0, ArrayUtils.lowerBound(a, -1L));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 7L));

        a = new long[]{0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 6, 7, 7};
        int[] expected = {0, 1, 3, 6, 7, 10, 11, 12};
        for(int i = 0;i < 8; ++i){
            assertEquals(expected[i], ArrayUtils.lowerBound(a, i));
        }
        assertEquals(0, ArrayUtils.lowerBound(a, -1L));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 8L));


        a = new long[]{0, 0, 0, 0, 0, 0, 0};
        assertEquals(0, ArrayUtils.lowerBound(a, 0L));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 1L));

        a = new long[]{0, 0, 0, 0, 0, 0, 1, 1};
        assertEquals(0, ArrayUtils.lowerBound(a, 0L));
        assertEquals(6, ArrayUtils.lowerBound(a, 1L));

        a = new long[]{-3, -2, -1, 0, 1, 2, 3};
        for(int i = -3;i <= 3; ++i){
            assertEquals(i + 3, ArrayUtils.lowerBound(a, i));
        }
        assertEquals(0, ArrayUtils.lowerBound(a, -4L));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 4L));

        a = new long[]{1, 2};
        assertEquals(0, ArrayUtils.lowerBound(a, 0L));
        assertEquals(0, ArrayUtils.lowerBound(a, 1L));
        assertEquals(1, ArrayUtils.lowerBound(a, 2L));
        assertEquals(2, ArrayUtils.lowerBound(a, 3L));

        a = new long[]{1};
        assertEquals(0, ArrayUtils.lowerBound(a, 1L));
        assertEquals(1, ArrayUtils.lowerBound(a, 2L));

        a = new long[]{};
        assertEquals(0, ArrayUtils.lowerBound(a, 1L));
    }

    @Test
    public void lowerBoundTestDouble() {
        double[] a = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
        for(int i = 0;i < 7; ++i){
            assertEquals(i, ArrayUtils.lowerBound(a, (double)i / 10));
        }
        assertEquals(0, ArrayUtils.lowerBound(a, -0.1));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 0.7));

        a = new double[]{0.0, 0.1, 0.1, 0.2, 0.2, 0.2, 0.3, 0.4, 0.4, 0.4, 0.5, 0.6, 0.7, 0.7};
        int[] expected = {0, 1, 3, 6, 7, 10, 11, 12};
        for(int i = 0;i < 8; ++i){
            assertEquals(expected[i], ArrayUtils.lowerBound(a, (double)i / 10));
        }
        assertEquals(0, ArrayUtils.lowerBound(a, -0.1));
        assertEquals(a.length, ArrayUtils.lowerBound(a, 0.8));

        a = new double[]{0.1, 0.2};
        assertEquals(0, ArrayUtils.lowerBound(a, 0.0));
        assertEquals(0, ArrayUtils.lowerBound(a, 0.1));
        assertEquals(1, ArrayUtils.lowerBound(a, 0.2));
        assertEquals(2, ArrayUtils.lowerBound(a, 0.3));

        a = new double[]{0.1};
        assertEquals(0, ArrayUtils.lowerBound(a, 0.1));
        assertEquals(1, ArrayUtils.lowerBound(a, 0.2));

        a = new double[]{};
        assertEquals(0, ArrayUtils.lowerBound(a, 0.1));
    }

    @Test
    public void lowerBoundTestPair() {
        Pair<Integer, Integer>[] a = new Pair[9];
        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                a[i * 3 + j] = new Pair<>(i, j);
            }
        }

        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                assertEquals(i * 3 + j, ArrayUtils.lowerBound(a, new Pair<>(i, j)));
            }
        }

        assertEquals(0, ArrayUtils.lowerBound(a, new Pair<>(-1, -1)));
        assertEquals(3, ArrayUtils.lowerBound(a, new Pair<>(1, -1)));
    }

    @Test
    public void upperBoundTestInt() {
        int[] a = {0, 1, 2, 3, 4, 5, 6};
        for(int i = 0;i < 7; ++i){
            assertEquals(i + 1, ArrayUtils.upperBound(a, i));
        }
        assertEquals(0, ArrayUtils.upperBound(a, -1));
        assertEquals(a.length, ArrayUtils.upperBound(a, 7));

        a = new int[]{0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 6, 7, 7};
        int[] expected = {1, 3, 6, 7, 10, 11, 12, 14};
        for(int i = 0;i < 8; ++i){
            assertEquals(expected[i], ArrayUtils.upperBound(a, i));
        }
        assertEquals(0, ArrayUtils.upperBound(a, -1));

        a = new int[]{0, 0, 0, 0, 0, 0, 0};
        assertEquals(0, ArrayUtils.upperBound(a, -1));
        assertEquals(a.length, ArrayUtils.upperBound(a, 0));
        assertEquals(a.length, ArrayUtils.upperBound(a, 1));

        a = new int[]{0, 0, 0, 0, 0, 0, 1, 1};
        assertEquals(0, ArrayUtils.upperBound(a, -1));
        assertEquals(6, ArrayUtils.upperBound(a, 0));
        assertEquals(a.length, ArrayUtils.upperBound(a, 1));

        a = new int[]{-3, -2, -1, 0, 1, 2, 3};
        for(int i = -3;i <= 3; ++i){
            assertEquals(i + 3 + 1, ArrayUtils.upperBound(a, i));
        }
        assertEquals(0, ArrayUtils.upperBound(a, -4));
        assertEquals(a.length, ArrayUtils.upperBound(a, 4));

        a = new int[]{1, 2};
        assertEquals(0, ArrayUtils.upperBound(a, 0));
        assertEquals(1, ArrayUtils.upperBound(a, 1));
        assertEquals(2, ArrayUtils.upperBound(a, 2));
        assertEquals(2, ArrayUtils.upperBound(a, 3));


        a = new int[]{1};
        assertEquals(0, ArrayUtils.upperBound(a, 0));
        assertEquals(1, ArrayUtils.upperBound(a, 1));

        a = new int[]{};
        assertEquals(0, ArrayUtils.upperBound(a, 1));
    }

    @Test
    public void upperBoundTestLong() {
        long[] a = {0, 1, 2, 3, 4, 5, 6};
        for(int i = 0;i < 7; ++i){
            assertEquals(i + 1, ArrayUtils.upperBound(a, (long)i));
        }
        assertEquals(0, ArrayUtils.upperBound(a, -1L));
        assertEquals(a.length, ArrayUtils.upperBound(a, 7L));

        a = new long[]{0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 6, 7, 7};
        int[] expected = {1, 3, 6, 7, 10, 11, 12, 14};
        for(int i = 0;i < 8; ++i){
            assertEquals(expected[i], ArrayUtils.upperBound(a, (long)i));
        }
        assertEquals(0, ArrayUtils.upperBound(a, -1L));

        a = new long[]{0, 0, 0, 0, 0, 0, 0};
        assertEquals(0, ArrayUtils.upperBound(a, -1L));
        assertEquals(a.length, ArrayUtils.upperBound(a, 0L));
        assertEquals(a.length, ArrayUtils.upperBound(a, 1L));

        a = new long[]{0, 0, 0, 0, 0, 0, 1, 1};
        assertEquals(0, ArrayUtils.upperBound(a, -1L));
        assertEquals(6, ArrayUtils.upperBound(a, 0L));
        assertEquals(a.length, ArrayUtils.upperBound(a, 1L));

        a = new long[]{-3, -2, -1, 0, 1, 2, 3};
        for(int i = -3;i <= 3; ++i){
            assertEquals(i + 3 + 1, ArrayUtils.upperBound(a, (long)i));
        }
        assertEquals(0, ArrayUtils.upperBound(a, -4L));
        assertEquals(a.length, ArrayUtils.upperBound(a, 4L));

        a = new long[]{1, 2};
        assertEquals(0, ArrayUtils.upperBound(a, 0L));
        assertEquals(1, ArrayUtils.upperBound(a, 1L));
        assertEquals(2, ArrayUtils.upperBound(a, 2L));
        assertEquals(2, ArrayUtils.upperBound(a, 3L));


        a = new long[]{1};
        assertEquals(0, ArrayUtils.upperBound(a, 0L));
        assertEquals(1, ArrayUtils.upperBound(a, 1L));

        a = new long[]{};
        assertEquals(0, ArrayUtils.upperBound(a, 1L));
    }

    @Test
    public void uppderBoundTestDouble() {
        double[] a = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6};
        for(int i = 0;i < 7; ++i){
            assertEquals(i + 1, ArrayUtils.upperBound(a, (double)i / 10));
        }
        assertEquals(0, ArrayUtils.upperBound(a, -0.1));
        assertEquals(a.length, ArrayUtils.upperBound(a, 0.7));

        a = new double[]{0.0, 0.1, 0.1, 0.2, 0.2, 0.2, 0.3, 0.4, 0.4, 0.4, 0.5, 0.6, 0.7, 0.7};
        int[] expected = {1, 3, 6, 7, 10, 11, 12, 14};
        for(int i = 0;i < 8; ++i){
            assertEquals(expected[i], ArrayUtils.upperBound(a, (double)i / 10));
        }
        assertEquals(0, ArrayUtils.upperBound(a, -0.1));
        assertEquals(a.length, ArrayUtils.upperBound(a, 0.8));

        a = new double[]{0.1, 0.2};
        assertEquals(0, ArrayUtils.upperBound(a, 0.0));
        assertEquals(1, ArrayUtils.upperBound(a, 0.1));
        assertEquals(2, ArrayUtils.upperBound(a, 0.2));
        assertEquals(2, ArrayUtils.upperBound(a, 0.3));

        a = new double[]{0.1};
        assertEquals(0, ArrayUtils.upperBound(a, 0.0));
        assertEquals(1, ArrayUtils.upperBound(a, 0.1));

        a = new double[]{};
        assertEquals(0, ArrayUtils.upperBound(a, 0.1));
    }

    @Test
    public void dedupTestInt() {
        int[] a = {0, 1, 1, 2, 3, 3, 3, 4, 5, 6, 7, 7};
        int[] retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7}, retu);

        a = new int[]{0, 1, 1, 0, 0, 1, 1, 0};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{0, 1, 0, 1, 0}, retu);

        a = new int[]{1, 0, 1, 0, 1};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{1, 0, 1, 0, 1}, retu);
        
        a = new int[]{1, 2, 3};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{1, 2, 3}, retu);

        a = new int[]{0, 0, 0, 0, 0, 0, 0};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{0}, retu);

        a = new int[]{1, 1, 2};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{1, 2}, retu);
        

        a = new int[]{1, 2, 2};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{1, 2}, retu);

        a = new int[]{1, 1, 1};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{1}, retu);

        a = new int[]{1, 2};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{1, 2}, retu);

        a = new int[]{1};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{1}, retu);

        a = new int[]{};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new int[]{}, retu);
    }

    @Test
    public void dedupTestLong() {
        long[] a = {0, 1, 1, 2, 3, 3, 3, 4, 5, 6, 7, 7};
        long[] retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{0, 1, 2, 3, 4, 5, 6, 7}, retu);

        a = new long[]{0, 1, 1, 0, 0, 1, 1, 0};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{0, 1, 0, 1, 0}, retu);

        a = new long[]{1, 0, 1, 0, 1};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{1, 0, 1, 0, 1}, retu);
        
        a = new long[]{1, 2, 3};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{1, 2, 3}, retu);

        a = new long[]{0, 0, 0, 0, 0, 0, 0};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{0}, retu);

        a = new long[]{1, 1, 2};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{1, 2}, retu);
        

        a = new long[]{1, 2, 2};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{1, 2}, retu);

        a = new long[]{1, 1, 1};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{1}, retu);

        a = new long[]{1, 2};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{1, 2}, retu);

        a = new long[]{1};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{1}, retu);

        a = new long[]{};
        retu = ArrayUtils.dedup(a);
        assertArrayEquals(new long[]{}, retu);
    }

    @Test
    public void upperBoundTestPair() {
        Pair<Integer, Integer>[] a = new Pair[9];
        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                a[i * 3 + j] = new Pair<>(i, j);
            }
        }

        for(int i = 0;i < 3; ++i){
            for(int j = 0;j < 3; ++j){
                assertEquals(i * 3 + j + 1, ArrayUtils.upperBound(a, new Pair<>(i, j)));
            }
        }

        assertEquals(0, ArrayUtils.upperBound(a, new Pair<>(-1, -1)));
        assertEquals(3, ArrayUtils.upperBound(a, new Pair<>(1, -1)));
    }
}
