package ac_library;

public final class LowLink {
    private final int n;
    private final java.util.ArrayList<Edge> edge;
    private final int[][][] graph;
    private final int[] start;
    private int time;
    private int[] disc, low;
    private boolean[] visited, isAP, isBridge;
    private boolean hasBuilt, obtainedArticulationPoints, obtainedBridges;

    private static final class Edge {
        private final int from;
        private final int to;
        private Edge(final int from, final int to) {
            this.from = from;
            this.to = to;
        }
    }

    public LowLink(final int n) {
        this.n = n;
        this.edge = new java.util.ArrayList<>();
        this.graph = new int[n][][];
        this.start = new int[n];
    }

    public void addEdge(final int u, final int v) {
        AssertUtil.check(0 <= u && u < this.n && 0 <= v && v < this.n);
        this.edge.add(new LowLink.Edge(u, v));
        ++this.start[u];
        ++this.start[v];
    }

    public void build() {
        this.hasBuilt = true;
        this.obtainedArticulationPoints = false;
        this.obtainedBridges = false;
        for (int i = 0; i < this.n; ++i) {
            this.graph[i] = new int[this.start[i]][];
        }
        final int[] index = new int[this.n];
        final int m = this.edge.size();
        for (int i = 0; i < m; ++i) {
            final LowLink.Edge e = this.edge.get(i);
            this.graph[e.from][index[e.from]++] = new int[]{  e.to, i};
            this.graph[  e.to][index[  e.to]++] = new int[]{e.from, i};
        }
    }

    public boolean[] articulationPoints() {
        AssertUtil.check(this.hasBuilt, "build has not been called yet");
        if (this.obtainedArticulationPoints) {
            return this.isAP;
        }
        this.time = -1;
        this.disc = new int[this.n];
        this.low = new int[this.n];
        this.visited = new boolean[this.n];
        this.isAP = new boolean[this.n];
        java.util.Arrays.fill(this.low, this.n<<1);

        for (int i = 0; i < this.n; ++i) {
            if (!this.visited[i]) {
                this.apUtil(i, -1);
            }
        }
        this.obtainedArticulationPoints = true;
        return this.isAP;
    }

    private void apUtil(final int u, final int p) {
        this.visited[u] = true;
        this.disc[u] = this.low[u] = ++this.time;

        int childCount = 0;
        for (final int[] e : this.graph[u]) {
            final int v = e[0];
            if (!this.visited[v]) {
                this.apUtil(v, u);

                this.low[u] = java.lang.Math.min(this.low[u], this.low[v]);
                ++childCount;

                if (p != -1 && this.low[v] >= this.disc[u]) {
                    this.isAP[u] = true;
                }
            }
            else if (v != p) {
                this.low[u] = java.lang.Math.min(this.low[u], this.disc[v]);
            }
        }
        if (p == -1 && childCount > 1) {
            this.isAP[u] = true;
        }
    }
    
    public boolean[] bridges() {
        AssertUtil.check(this.hasBuilt, "build has not been called yet");
        if (this.obtainedBridges) {
            return this.isBridge;
        }
        this.time = -1;
        this.disc = new int[this.n];
        this.low = new int[this.n];
        this.visited = new boolean[this.n];
        java.util.Arrays.fill(low, this.n<<1);

        this.isBridge = new boolean[this.edge.size()];
        for (int i = 0; i < this.n; ++i) {
            if (!this.visited[i]) {
                this.bridgeUtil(i, -1);
            }
        }
        this.obtainedBridges = true;
        return this.isBridge;
    }

    private void bridgeUtil(final int u, final int parentEdge) {
        this.visited[u] = true;
        this.disc[u] = this.low[u] = ++this.time;

        for (final int[] edge : this.graph[u]) {
            final int v = edge[0];
            final int edgeIndex = edge[1];
            if (parentEdge == edgeIndex) {
                continue;
            }
            if (this.visited[v]) {
                this.low[u] = java.lang.Math.min(this.low[u], this.disc[v]);
            }
            else{
                this.bridgeUtil(v, edgeIndex);
                this.low[u] = java.lang.Math.min(this.low[u], this.low[v]);
                if (this.low[v] > this.disc[u]) {
                    this.isBridge[edgeIndex] = true;
                }
            }
        }
    }
}
