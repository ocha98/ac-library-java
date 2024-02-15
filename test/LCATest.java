import org.junit.Assert;
import org.junit.Test;

import ac_library.LCA;

public class LCATest {
    @Test
    public void testLCA() {
        /*
            0
          /  \
         1     2
        / \   / \
        3  4 5   6
        */

        LCA lca = new LCA(7);
        lca.addEdge(0, 1);
        lca.addEdge(0, 2);
        lca.addEdge(1, 3);
        lca.addEdge(1, 4);
        lca.addEdge(2, 5);
        lca.addEdge(2, 6);
        lca.build();

        for(int i: new int[]{0, 1, 3, 4}) {
            for(int j: new int[]{0, 2, 5, 6}) {
                Assert.assertEquals(0, lca.getLCA(i, j));
            }
        }
        for(int i: new int[]{0, 2, 5, 6}) {
            for(int j: new int[]{0, 1, 3, 4}) {
                Assert.assertEquals(0, lca.getLCA(i, j));
            }
        }
    
        Assert.assertEquals(1, lca.getLCA(1, 3));
        Assert.assertEquals(1, lca.getLCA(1, 4));
        Assert.assertEquals(1, lca.getLCA(3, 4));

        Assert.assertEquals(2, lca.getLCA(2, 5));
        Assert.assertEquals(2, lca.getLCA(2, 6));
        Assert.assertEquals(2, lca.getLCA(5, 6));
    }

    @Test
    public void testDistance() {
        /*
            0
          /  \
         1     2
        / \   / \
        3  4 5   6
        */
        LCA lca = new LCA(7);
        lca.addEdge(0, 1);
        lca.addEdge(0, 2);
        lca.addEdge(1, 3);
        lca.addEdge(1, 4);
        lca.addEdge(2, 5);
        lca.addEdge(2, 6);
        lca.build();

        for(int i: new int[]{3, 4}) {
            for(int j: new int[]{5, 6}) {
                Assert.assertEquals(4, lca.dist(i, j));
            }
        }
        for(int i: new int[]{5, 6}) {
            for(int j: new int[]{3, 4}) {
                Assert.assertEquals(4, lca.dist(i, j));
            }
        }
        
        Assert.assertEquals(0, lca.dist(2, 2));
        Assert.assertEquals(1, lca.dist(2, 0));
        Assert.assertEquals(2, lca.dist(2, 1));
        Assert.assertEquals(3, lca.dist(2, 3));
        Assert.assertEquals(3, lca.dist(2, 4));

        Assert.assertEquals(2, lca.dist(0, 4));
        Assert.assertEquals(2, lca.dist(0, 5));
        Assert.assertEquals(2, lca.dist(0, 6));

        Assert.assertEquals(1, lca.dist(1, 3));
        Assert.assertEquals(1, lca.dist(1, 4));
        Assert.assertEquals(2, lca.dist(3, 4));

        Assert.assertEquals(1, lca.dist(2, 5));
        Assert.assertEquals(1, lca.dist(2, 6));
        Assert.assertEquals(2, lca.dist(5, 6));
    }
}