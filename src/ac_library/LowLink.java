package ac_library;

public final class LowLink {
    private final int n;
    private final java.util.ArrayList<Edge> edge;
    private boolean[] isAP, isBridge;
    private boolean hasBuilt;

    private static final class CSR {
        final int[] start;
        final int[] elist;
        // es is considered to be an undirected edge
        CSR(int n, java.util.ArrayList<Edge> es) {
            final int m = es.size();
            start = new int[n+1];
            elist = new int[m << 1];
            for (int i = 0;i < m; ++i) {
                ++start[es.get(i).from + 1];
                ++start[es.get(i).to + 1];
            }
            for (int i = 0;i < n; ++i) {
                start[i+1] += start[i];
            }

            int[] counter = start.clone();
            for (int i = 0;i < m; ++i) {
                final Edge e = es.get(i);
                elist[counter[e.from]++] = e.to;
                elist[counter[e.to]++] = e.from;
            }
        }
    }

    private static final class Edge {
        private final int from;
        private final int to;
        private final int id;
        private Edge(final int from, final int to, final int id) {
            this.from = from;
            this.to = to;
            this.id = id;
        }
    }

    public LowLink(final int n) {
        this.n = n;
        this.edge = new java.util.ArrayList<>();
    }

    public void addEdge(final int u, final int v) {
        AssertUtil.check(0 <= u && u < this.n && 0 <= v && v < this.n);
        this.edge.add(new LowLink.Edge(u, v, this.edge.size()));
    }

    public void build() {
        this.hasBuilt = true;

        final CSR csr = new CSR(this.n, this.edge);
        final int[] counter = csr.start.clone();
        final int[] par = new int[n];
        final int[] low = new int[n];
        final int[] ord = new int[n];
        java.util.Arrays.fill(par, -1);
        java.util.Arrays.fill(low, Integer.MAX_VALUE);
        int k = 0;
        for (int s = 0;s < n; ++s) {
            if (par[s] != -1) continue;
            ord[s] = low[s] = k++;
            int v = s;
            while (true) {
                if (counter[v] == csr.start[v+1]) {
                    if (v == s) break;
                    final int p = par[v];
                    low[p] = Math.min(low[p], low[v]);
                    v = p;
                } else {
                    final int to = csr.elist[counter[v]++];
                    if (to == par[v]) continue;

                    if (low[to] == Integer.MAX_VALUE) {
                        par[to] = v;
                        ord[to] = low[to] = k++;
                        v = to;
                    } else {
                        low[v] = Math.min(low[v], ord[to]);
                    }
                }
            }
        }

        this.isBridge = new boolean[this.edge.size()];
        for (Edge e: this.edge) {
            int u = e.from;
            int v = e.to;
            if (ord[u] > ord[v]) {
                int tmp = u;
                u = v;
                v = tmp;
            }

            if (ord[u] < low[v]) {
                this.isBridge[e.id] = true;
            }
        }

        final int[] count_child = new int[n];
        this.isAP = new boolean[n];
        for (int i = 0;i < n; ++i) {
            final int p = par[i];
            if(p == -1)continue;
            ++count_child[p];
            this.isAP[p] |= ord[p] <= low[i];
        }
        for (int i = 0;i < n; ++i) {
            if (par[i] == -1) {
                isAP[i] = count_child[i] >= 2;
            }
        }
    }

    public boolean[] articulationPoints() {
        AssertUtil.check(this.hasBuilt, "build has not been called yet");
        return this.isAP.clone();
    }

    public boolean[] bridges() {
        AssertUtil.check(this.hasBuilt, "build has not been called yet");
        return this.isBridge.clone();
    }
}