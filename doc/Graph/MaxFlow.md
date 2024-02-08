# クラス MaxFlow
- - -

## コンストラクタ

```java
public MaxFlow(int n)
```

$n$ 頂点 $0$ 辺のグラフを作ります。

**計算量**
* $O(n)$

## メソッド

### addEdge

```java
public void addEdge(int from, int to, long cap)
```

`from` から `to` へ最大容量 `cap` 、流量 $0$ の辺を追加し、何番目に追加された辺かを返します。

**制約**
* $0 \leq \mathrm{from}, \mathrm{to} < n$
* $0 \leq \mathrm{cap}$

**計算量**
* ならし $O(1)$

### maxFlow

```java
// (1)
public long maxFlow(int s, int t)
// (2)
public long flow(int s, int t, long flowLimit)
```

- (1) 頂点 `s` から `t` へ流せる限り流し、流せた量を返します。 Dinic のアルゴルズムを用います。
- (2) 頂点 `s` から `t` へ流量 `flowLimit` に達するまで流せる限り流し、流せた量を返します。 Dinic のアルゴルズムを用います。
- 複数回呼んだ場合は、前回流したフローからの差分を返します。

**制約**
* $0 \leq s, t < n$

**計算量**
$m$ を追加された辺数として
* 辺の容量がすべて $1$ の場合、 $O(\min(n^{2/3}m, m^{3/2}))$
* 上記以外の場合、 $O(n^2m)$

### minCut

```java
public boolean[] minCut(int s)
```

長さ $n$ の `boolean` 配列を返す。$i$ 番目の要素は、頂点 `s` から $i$ へ残余グラフで到達可能なとき、またその時のみ `true`。 `maxFlow(s, t)` をちょうど一回呼んだ後に呼ぶと、返り値は `s`, `t` 間の mincut に対応します。

**計算量**
* $m$ を追加された辺数として $O(n+m)$

### getEdge / getEdges

`getEdge`、`getEdges` の返り値には以下のメンバを持つ `MaxFlow.CapEdge` 型が用いられています。

```java
class CapEdge {
    // (1)
    public final int from;
    // (2)
    public final int to;
    // (3)
    public final long cap;
    // (4)
    public final long flow;
};
```

- (1) 辺の始点です。
- (2) 辺の終点です。
- (3) 辺の容量です。
- (4) 現在の流量です。

```java
// (1)
public CapEdge getEdge(int i)
// (2)
public CapEdge[] getEdges()
```

現在の内部の辺の状態を返す。辺の順番は `addEdge` で追加された順番と同一。

**制約**
* (1) 追加した辺の数を $m$ として、 $0 \leq i < m$

**計算量**
* (1)  $O(1)$
* (2)  $O(m)$

### changeEdge

```java
public void changeEdge(int i, long newCap, long newFlow)
```

$i$ 番目に変更された辺の容量、流量を `newCap` 、 `newFlow` に変更する。他の辺の容量、流量は変更しない。

**制約**
* $0 \leq \mathrm{newFlow} \mathrm{newCap}$

**計算量**
* $O(1)

## 使用例

[AtCoder Library Practice Contest D - Maxflow](https://atcoder.jp/contests/practice2/submissions/20808482)
