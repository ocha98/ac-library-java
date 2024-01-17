package test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import ac_library.DSU;

public class DSUTest {
    @Test
    public void test() {
        DSU dsu = new DSU(10);
        dsu.merge(0, 1);
        dsu.merge(1, 2);

        assertTrue(dsu.same(0, 1));
        assertTrue(dsu.same(1, 2));
        assertTrue(dsu.same(0, 2));

        assertTrue(dsu.size(0)== 3);
    }
}
