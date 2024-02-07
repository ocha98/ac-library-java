# クラス FenwickTree
- - -

長さ $n$ の `long` 型配列に対し、

* 要素の $1$ 点変更
* 区間の要素の総和

を $O(\log n)$ の時間で求めることができるデータ構造です。


## コンストラクタ
### FenwickTree
```java
public FenwickTree(int n)
```

長さ $n$ の配列 $a_0, a_1, \dots, a_{n-1}$ を作ります。  
初期値はすべて $0$ です。

**計算量**
* $O(n)$

```java
public FenwickTree(long[] data)
```

長さ $n$ の配列 $a_0, a_1, \dots, a_{n-1}$ を `data` により初期化します。

**計算量**
* $O(n)$

## メソッド
### add
```java
public void add(int p, long x)
```
配列の第 $p$ 要素に $x$ を加えます。
すなわち、 $a_p \ += x$ と同義です。

**計算量**
* $O(\log n)$

### set
```java
public void set(int p, long x)
```
配列の第 $p$ 要素を $x$ に変更します。
すなわち、 $a_p = x$ と同義です。

**計算量**
* $O(\log n)$

### get
```java
public long get(int p)
```
配列の第 $p$ 要素を取得します。

**計算量**
* $O(\log N)$

### sum
```java
public long sum(int l, int r)
```
$a_l + a_{l+1} + \dots + a_{r-1}$ の値を返します。

**計算量**
* $O(\log n)$

## 使用例
[[https://atcoder.jp/contests/practice2/submissions/16573339]]
