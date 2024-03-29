# クラス TwoSAT
- - -

$2$ -SAT を解きます。 変数 $x_0, x_1, \dots, x_{N-1}$ に関して、

$(x_i = f) \lor (x_j = g)$

というクローズを足し、これをすべて満たす変数の割当があるかを解きます。

## コンストラクタ

```java
public TwoSAT(int n)
```

$n$ 変数の $2$ -SAT を作ります。

**計算量**
* $O(n)$

## メソッド

### addClause

```java
public void addClause(int x, boolean f, int y, boolean g)
```

$(x_i = f) \lor (x_j = g)$ というクローズを足します。

**制約**
* $0 \leq i < n$
* $0 \leq j < n$

**計算量**
* ならし $O(1)$

### addImplication

```java
public void addImplication(int x, boolean f, int y, boolean g)
```

$(x_i = f) \rightarrow (x_j = g)$, 即ち $(x_i = \neg f) \lor (x_j = g)$ というクローズを足します。

**制約**
* $0 \leq i < n$
* $0 \leq j < n$

**計算量**
* ならし $O(1)$

### addNand

```java
public void addNand(int x, boolean f, int y, boolean g)
```

$\neg ((x_i = f) \land (x_j = g))$, 即ち $(x_i = \neg f) \lor (x_j = \neg g)$ というクローズを足します。禁止制約の追加と考えるとよいです。

**制約**
* $0 \leq i < n$
* $0 \leq j < n$

**計算量**
* ならし $O(1)$

### set

```java
public void set(int x, boolean f)
```
$(x_i = f)$ というクローズを足します。

### satisfiable

```java
public boolean satisfiable()
```

条件を足す割当が存在するかどうかを判定する。割当が存在するならば `true`、そうでないなら `false` を返す。

**制約**
* 複数回呼ぶことも可能。

**計算量**
* 足した制約の個数を $m$ として $O(n+m)$

### answer

```java
public boolean[] answer()
```

`satisfiable` を最後に呼んだ時点での、クローズを満たす割当を返す。割当が存在しなかった場合は `null` を返す。

__`satisfiable` を一度も呼んでいない時点で呼ばれた場合は、実行時例外 `UnsupportedOperationException` が発生します (`satisfiable` の呼び忘れ防止)。__

**制約**
* __`satisfiable` を少なくとも $1$ 回は呼んでいる__

**計算量**
* $O(n)$

## 使用例

[AtCoder Library Practice Contest H - Two SAT](https://atcoder.jp/contests/practice2/submissions/16647102)
