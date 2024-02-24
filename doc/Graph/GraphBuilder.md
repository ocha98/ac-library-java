# クラス GraphBuilder
- - -

与えられた $0\text{index}$ の辺集合から無向グラフ及び、有向グラフを隣接リスト形式のグラフを生成します。

全てのメンバーは static でコンストラクターは定義されていません。

## メソッド
### makeGraph
```java 
public static int[][] makeGraph(int NumberOfNodes, int NumberOfEdges, int[] from, int[] to, boolean undirected)
```
指定された頂点数と `from` と `to` の配列から構成される $0\text{index}$ の隣接リストを返します。もし `undirected` が `true` の場合、辺は無向辺とみなされます。そうでなければ有向辺とされます。

`graph[u]` は頂点 $u$ から出る辺が繋ぐ頂点の集合である。

**制約**
* $0 \leq \mathrm{from}_i, \mathrm{to}_i < \mathrm{NumberOfNodes}$
* $\mathrm{from.length} = \mathrm{to.length} = \mathrm{NumberOfEdges}$

**計算量**
* $O(\mathrm{NumberOfNodes} + \mathrm{NumberOfEdges})$

### makeGraphWithEdgeInfo
```java
public static int[][][] makeGraphWithEdgeInfo(int NumberOfNodes, int NumberOfEdges, int[] from, int[] to, boolean undirected)
```

指定された頂点数と辺数のグラフの `from` 、 `to` で指定された辺を持つグラフを隣接リストを返します。 $0\text{index}$ です。 `undirected` が `true` の場合、辺は無向辺とみなされ、そうでなければ有向辺となります。また、このメソッドは辺の番号と方向を返します。


`graph[u]` は　`[v, idx, direction]` のタプルの配列であり、 `idx` 番目の辺は頂点 `u` と `v` を方向 $0$ または $1$ で結んでいる。

- `direction` が $0$ の時、 `idx` 番目の辺は `u` から `v` に向かう
- `direction` が $1$ の時、 `idx` 番目の辺は `v` から `u` に向かう

**制約**
* $0 \leq from_i, to_i < \mathrm{NumberOfNodes}$
* $\mathrm{from.length} = \mathrm{to.length} = \mathrm{NumberOfEdges}$

**計算量**
* $O(\mathrm{NumberOfNodes} + \mathrm{NumberOfEdges})$
