# クラス LowLink
- - -

与えられた無向辺を元に無向辺を構築し、関節点と橋を探索します。  
どのメソッドも $0$-indexed を仮定しています。

## コンストラクタ
```java
public LowLink(int n)
```
指定された頂点数の、辺数 $0$ の無向グラフを生成します。

**計算量**
* $O(n)$

## メソッド
### addEdge
```java 
public void addEdge(int u, int v)
```
無向辺　$u,v$ を追加します。

**制約**
* $0 \leq u, v < n$

**計算量**
* ならし $O(1)$

### build
```java 
public void build()
```
これまで追加された無向辺を元にグラフを構築します。
`articulationPoints` と `bridges` メソッドを呼び出す前には必ず呼び出して下さい。

**計算量**
* 追加された辺の数を $m$ とすると $O(n+m)$

### articulationPoints
```java
public boolean[] articulationPoints()
```
長さ $n$ の `boolean` 配列を返します。 $i$ 番目の要素は $i$ 番目の頂点が関節点がどうかを示しています。

**計算量**
* 追加された辺の数を $m$ とすると $O(n+m)$

### bridges
```java
public boolean[] bridges()
```
長さ $n$ の `boolean` 配列を返します。  $i$ 番目の要素は $i$ 番目の辺が橋かどうかを示します。

**計算量**
* 追加された辺の数を $m$ とすると $O(n+m)$
