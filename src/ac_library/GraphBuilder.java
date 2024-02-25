package ac_library;

public final class GraphBuilder {
    private final int n;
    private final java.util.ArrayList<Edge> edge;
    private final int[][][] graph;
    private final int[] start;
    private int time;
    private int[] disc, low;
    private boolean[] visited,isAP,isBridge;
    private boolean hasBuilt,obtainedArticulationPoints,obtainedBridges;

    private static final class Edge {
        private final int from;
        private final int to;
        private final boolean isUndirect;
        private Edge(final int from, final int to,final boolean isUndirect) {
            this.from = from;
            this.to = to;
            this.isUndirect = isUndirect;
        }
    }

    public GraphBuilder(final int n) {
        this.n = n;
        this.edge = new java.util.ArrayList<>();
        this.graph = new int[n][][];
        this.start = new int[n];
    }

    public void addEdge(final int u, final int v,final boolean undirect) {
        AssertUtil.check(0 <= u && u < n && 0 <= v && v < n);
        edge.add(new Edge(u, v, undirect));
        ++start[u];
        if(undirect){
            ++start[v];
        }
    }

    public int[][][] build() {
        hasBuilt = true;
        obtainedArticulationPoints = false;
        obtainedBridges = false;
        for(int i=0;i<n;++i){
            graph[i] = new int[start[i]][];
        }
        final int[] index = new int[n];
        final int m = edge.size();
        for(int i=0;i<m;i++){
            final Edge e = edge.get(i);
            graph[e.from][index[e.from]++] = new int[]{e.to,i};
            if(e.isUndirect){
                graph[e.to][index[e.to]++] = new int[]{e.from,i};
            }
        }
        return graph;
    }

    public boolean[] articulationPoints() {
        AssertUtil.check(hasBuilt,"build has not been called yet");
        if(obtainedArticulationPoints){
            return isAP;
        }
        this.time = -1;
        this.disc = new int[n];
        this.low = new int[n];
        this.visited = new boolean[n];
        this.isAP = new boolean[n];
        java.util.Arrays.fill(low, n<<1);

        for(int i=0;i<n;++i){
            if(!visited[i]){
                apUtil(i, -1);
            }
        }
        obtainedArticulationPoints = true;
        return isAP;
    }

    private void apUtil(final int u, final int p) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        int childCount = 0;
        for(final int[] e:graph[u]){
            final int v = e[0];
            if(!visited[v]){
                apUtil(v, u);

                low[u] = Math.min(low[u], low[v]);
                childCount++;

                if (p != -1 && low[v] >= disc[u]){
                    isAP[u] = true;
                }
            }
            else if(v!=p){
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if(p == -1 && childCount > 1){
            isAP[u] = true;
        }
    }
    
    public boolean[] bridges() {
        AssertUtil.check(hasBuilt,"build has not been called yet");
        if(obtainedBridges){
            return isBridge;
        }
        this.time = -1;
        this.disc = new int[n];
        this.low = new int[n];
        this.visited = new boolean[n];
        java.util.Arrays.fill(low, n<<1);

        this.isBridge = new boolean[edge.size()];
        for (int i = 0; i < n; ++i){
            if(!visited[i]){
                bridgeUtil(i, -1);
            }
        }
        obtainedBridges = true;
        return isBridge;
    }

    private void bridgeUtil(final int u, final int parentEdge) {
        visited[u] = true;
        disc[u] = low[u] = ++time;

        for (final int[] edge : graph[u]) {
            final int v = edge[0];
            final int edgeIndex = edge[1];
            if (parentEdge == edgeIndex){
                continue;
            }
            if(visited[v]){
                low[u] = Math.min(low[u], disc[v]);
            }
            else{
                bridgeUtil(v, edgeIndex);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]){
                    isBridge[edgeIndex] = true;
                }
            }
        }
    }
}
