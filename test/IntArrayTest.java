import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ac_library.IntArray;

public class IntArrayTest {
    @Test
    public void testSize() {
        IntArray array = new IntArray();
        assertEquals(0, array.size());
        
        array.push(10);
        assertEquals(1, array.size());
        
        array.push(20);
        assertEquals(2, array.size());
        array.pop();
        assertEquals(1, array.size());
        array.pop();
        assertEquals(0, array.size());
    }
    
    @Test
    public void testPushAndPop() {
        IntArray array = new IntArray();
        array.push(10);
        array.push(20);
        array.push(30);

        assertEquals(30, array.pop());
        assertEquals(20, array.pop());
        assertEquals(10, array.pop());
    }
    
    @Test
    public void testGetAndSet() {
        IntArray array = new IntArray();
        array.push(10);
        array.push(20);
        array.push(30);
        
        assertEquals(20, array.get(1));
        
        array.set(1, 50);
        assertEquals(50, array.get(1));
    }
    
    @Test
    public void testBack() {
        IntArray array = new IntArray();
        array.push(10);
        array.push(20);
        array.push(30);
        
        assertEquals(30, array.back());
        
        array.pop();
        assertEquals(20, array.back());
    }
    
    @Test
    public void testIsEmpty() {
        IntArray array = new IntArray();
        assertTrue(array.isEmpty());
        
        array.push(10);
        assertFalse(array.isEmpty());
        
        array.pop();
        assertTrue(array.isEmpty());
    }
    
    @Test
    public void testCopy() {
        IntArray array = new IntArray();
        array.push(10);
        array.push(20);
        array.push(30);
        
        IntArray copy = array.copy();
        assertEquals(array.size(), copy.size());
        
        for (int i = 0; i < array.size(); i++) {
            assertEquals(array.get(i), copy.get(i));
        }
        array.set(0, 4);
        assertNotEquals(array.get(0), copy.get(0));
    }

    @Test
    public void throwTest() {
        IntArray array = new IntArray();
        assertThrows(IndexOutOfBoundsException.class, () -> array.pop());
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> array.set(0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> array.back());
    }
}