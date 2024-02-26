# クラス ArticulationPoint
- - -

`GraphBuilder.makeGraph()` によって生成されたグラフを与えると、このクラスは関節点を全て見つけます。 `GraphBuilder.makeGraph()` は $0\text{index}$ を仮定しているのでこのクラスも $0\text{index}$ で動作します。

全てのメンバーは static でコンストラクタは定義されていません。

## メソッド

### articulationPoints
```java
public static boolean[] articulationPoints(int[][] graph)
```
$n$ を与えられたグラフの頂点数とした時、長さ $n$ の `boolean` 配列を返します。 $i$ 番目の要素は $i$ 番目の頂点が関節点がどうかを示しています。

**制約**
* `graph` は `GraphBuilder.makeGraph()`で生成されたものであり、`makeGraph` メソッドに適応されるすべての制約が適応されます。

**計算量**
* $O(\mathrm{NumberOfNodes} + \mathrm{NumberOfEdges})$
