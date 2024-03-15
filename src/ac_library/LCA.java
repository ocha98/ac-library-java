package ac_library;

public final class LCA {
    private final static int INF = 1<<29;
    private final int n;
    private final java.util.ArrayList<Edge> es;
    private boolean hasBuilt = false;
    // EularTour
    private final int[] eular_tour;
    private final int[] first;
    private final int[] depth;

    private SparseTable sp;

    final public static class Edge {
        final int from;
        final int to;
        Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }
    
    final static private class CSR {
        final int[] start;
        final int[] elist;
        // n: number of vertex
        CSR(int n, java.util.ArrayList<Edge> es) {
            start = new int[n+1];
            elist = new int[es.size()];
            final int m = es.size();
            for(int i = 0;i < m; ++i) {
                ++start[es.get(i).from + 1];
            }
            for(int i = 0;i < n; ++i) {
                start[i+1] += start[i];
            }
    
            int[] counter = start.clone();
            for(int i = 0;i < m; ++i) {
                final Edge e = es.get(i);
                elist[counter[e.from]++] = e.to;
            }
        }
    }

    final private class SparseTable {
        private final int[][] table;

        // \lfloor  \log_{2}{x} \rfloor
        private static int log2(int x) {
            return 31 - Integer.numberOfLeadingZeros(x);
        }

        SparseTable() {
            final int m = n<<1; // n is parent class n (Number of vertex).
            table = new int[log2(m)+1][m];

            System.arraycopy(eular_tour, 0, table[0], 0, m);

            final int log2m = log2(m);
            for(int k = 0;k < log2m; ++k) {
                final int r = m - (1<<k + 1);
                for(int i = 0;i <= r; ++i){
                    final int v1 = table[k][i];
                    final int v2 = table[k][i + (1<<k)];

                    table[k+1][i] = depth[v1] < depth[v2] ? v1 : v2;
                }
            }
        }

        public int query(int l, int r) {
            final int k = log2(r-l);
            final int v1 = table[k][l];
            final int v2 = table[k][r - (1<<k)];
            return depth[v1] < depth[v2] ? v1 : v2;
        }
    }

    public LCA(int n) {
        this.n = n;
        this.es = new java.util.ArrayList<>();
        this.eular_tour = new int[n<<1];
        this.depth = new int[n];
        this.first = new int[n];
        java.util.Arrays.fill(depth, INF);
    }

    private void eulerTour() {
        final CSR csr = new CSR(n, this.es);
        final IntArray st = new IntArray();
        st.reserve(n<<1);
        depth[0] = 0;
        st.push(0);
        int eular_tour_idx = 0;
        while(!st.isEmpty()) {
            final int v = st.pop();
            if(v >= 0) {
                first[v] = eular_tour_idx;
                eular_tour[eular_tour_idx] = v;
                ++eular_tour_idx;
                final int start = csr.start[v];
                final int end = csr.start[v+1];
                for(int i = start;i < end; ++i) {
                    final int nxt = csr.elist[i];
                    if(depth[nxt] != INF)continue;
                    depth[nxt] = depth[v] + 1;
                    st.push(~v);
                    st.push(nxt);
                }
            } else {
                eular_tour[eular_tour_idx] = ~v;
                ++eular_tour_idx;
            }
        }
    }

    public void addEdge(int u, int v) {
        AssertUtil.check(0 <= u && u < n && 0 <= v && v < n);
        es.add(new Edge(u, v));
        es.add(new Edge(v, u));
    }

    public void build() {
        this.hasBuilt = true;
        eulerTour();
        sp = new SparseTable();
    }

    public int getLCA(int u, int v) {
        AssertUtil.check(this.hasBuilt, "build has not been called yet");
        AssertUtil.check(0 <= u && u < n && 0 <= v && v < n);
        if(u == v)return u;
        int in_u = first[u];
        int in_v = first[v];
        if(in_u > in_v) {
            int tmp = in_u;
            in_u = in_v;
            in_v = tmp;
        }
        return sp.query(in_u, in_v);
    }

    public int dist(int u, int v) {
        AssertUtil.check(this.hasBuilt, "build has not been called yet");
        AssertUtil.check(0 <= u && u < n && 0 <= v && v < n);
        final int lca = this.getLCA(u, v);
        return depth[v] + depth[u] - (depth[lca]<<1);
    }
}
