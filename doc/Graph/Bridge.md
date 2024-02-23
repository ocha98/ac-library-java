# クラス Bridge
- - -

`GraphBuilder.makeGraphWithEdgeInfo()` により生成された無向グラフを与えると、このクラスは全ての橋を見つける。 `GraphBuilder` は $0\text{index}$ だと仮定しているのでこのクラスも $0\text{index}$ を仮定して動作する。このクラスは多重辺や自己ループも扱える。

全てのメンバーは static でありコンストラクタは定義されていない。

## メソッド
### bridges
```java
public static boolean[] bridges(int[][][] graphWithEdgeInfo, int edgeCount)
```

長さ $\mathrm{edgeCount}$ の `boolean` の配列を返す。ここで、$\mathrm{EdgeCount}$ は与えられたグラフの辺数を示す。 $i$ 番目の要素は $i$ 番目の辺が橋かどうかを示す。辺は `GraphBuilder.makeGraphWithEdgeInfo()` で定義された入力の順序に従って番号づけられる。

**制約**
* グラフは `GraphBuilder.makeGraphWithEdgeInfo()` により生成されたものであり、`makeGraphWithEdgeInfo`ので適応される全ての制約が適応される。
* $\mathrm{edgeCount} = \mathrm{Number}$ は `GraphBuilder.makeGraphWithEdgeInfo()`で指定された辺の数。

**計算量**
* $O(\mathrm{NumberOfNodes}+ \mathrm{NumberOfEdges})$


```java
public static boolean[] bridges(int[][][] graphWithEdgeInfo)
```
このメソッドは指定されたグラフの辺数を計算し、`boolean[] bridges(int[][][] graphWithEdgeInfo, int edgeCount)` を呼び出して `boolea` の配列を返す。  $i$ 番目の要素は $i$ 番目の辺が橋かどうかを示す。


**制約**
* グラフは `GraphBuilder.makeGraphWithEdgeInfo()` により生成されたものであり、`makeGraphWithEdgeInfo`ので適応される全ての制約が適応される。

**計算量**
* $O(\mathrm{NumberOfNodes}+ \mathrm{NumberOfEdges})$
