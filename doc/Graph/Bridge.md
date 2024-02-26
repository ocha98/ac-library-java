# クラス Bridge
- - -

`GraphBuilder.makeGraphWithEdgeInfo()` により生成された無向グラフを与えると、このクラスは全ての橋を見つけます。 `GraphBuilder` は $0\text{index}$ だと仮定しているのでこのクラスも $0\text{index}$ を仮定して動作します。このクラスは多重辺や自己ループも扱えます。

全てのメンバーは static でありコンストラクタは定義されていません。

## メソッド
### bridges
```java
public static boolean[] bridges(int[][][] graphWithEdgeInfo, int edgeCount)
```

長さ $\mathrm{edgeCount}$ の `boolean` の配列を返します。ここで、$\mathrm{EdgeCount}$ は与えられたグラフの辺数を示しています。 $i$ 番目の要素は $i$ 番目の辺が橋かどうかを示すします。辺は `GraphBuilder.makeGraphWithEdgeInfo()` で定義された入力の順序に従って番号づけられます。

**制約**
* グラフは `GraphBuilder.makeGraphWithEdgeInfo()` により生成されたものであり、`makeGraphWithEdgeInfo` で適応される全ての制約が適応されます。
* $\mathrm{edgeCount} = \mathrm{Number}$ は `GraphBuilder.makeGraphWithEdgeInfo()`で指定された辺の数です。

**計算量**
* $O(\mathrm{NumberOfNodes}+ \mathrm{NumberOfEdges})$


```java
public static boolean[] bridges(int[][][] graphWithEdgeInfo)
```
このメソッドは指定されたグラフの辺数を計算し、`boolean[] bridges(int[][][] graphWithEdgeInfo, int edgeCount)` を呼び出して `boolean` の配列を返します。  $i$ 番目の要素は $i$ 番目の辺が橋かどうかを示します。


**制約**
* グラフは `GraphBuilder.makeGraphWithEdgeInfo()` により生成されたものであり、`makeGraphWithEdgeInfo` ので適応される全ての制約が適応されます。

**計算量**
* $O(\mathrm{NumberOfNodes}+ \mathrm{NumberOfEdges})$
