package ac_library;

public class DSU {
    private int n;
    private int[] parentOrSize;

    public DSU(int n) {
        this.n = n;
        this.parentOrSize = new int[n];
        java.util.Arrays.fill(parentOrSize, -1);
    }

    public int merge(int a, int b) {
        if (!(0 <= a && a < n)) throw new IndexOutOfBoundsException("a=" + a);
        if (!(0 <= b && b < n)) throw new IndexOutOfBoundsException("b=" + b);

        int x = leader(a);
        int y = leader(b);
        if (x == y) return x;
        if (-parentOrSize[x] < -parentOrSize[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        parentOrSize[x] += parentOrSize[y];
        parentOrSize[y] = x;
        return x;
    }

    public boolean same(int a, int b) {
        if (!(0 <= a && a < n)) throw new IndexOutOfBoundsException("a=" + a);
        if (!(0 <= b && b < n)) throw new IndexOutOfBoundsException("b=" + b);
        return leader(a) == leader(b);
    }

    public int leader(int a) {
        if (parentOrSize[a] < 0) {
            return a;
        } else {
            parentOrSize[a] = leader(parentOrSize[a]);
            return parentOrSize[a];
        }
    }

    public int size(int a) {
        if (!(0 <= a && a < n)) throw new IndexOutOfBoundsException("" + a);
        return -parentOrSize[leader(a)];
    }

    public java.util.ArrayList<java.util.ArrayList<Integer>> groups() {
        int[] leaderBuf = new int[n];
        int[] groupSize = new int[n];
        for (int i = 0; i < n; i++) {
            leaderBuf[i] = leader(i);
            groupSize[leaderBuf[i]]++;
        }
        java.util.ArrayList<java.util.ArrayList<Integer>> result = new java.util.ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(new java.util.ArrayList<>(groupSize[i]));
        }
        for (int i = 0; i < n; i++) {
            result.get(leaderBuf[i]).add(i);
        }
        result.removeIf(java.util.ArrayList::isEmpty);
        return result;
    }
}
