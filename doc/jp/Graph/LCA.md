# LCA
---
木の最小共通先祖と２頂点間の距離を求めます。オフラインです。

## コンストラクタ
```java
public LCA(int n)
```

指定された頂点数 $n$ の木を扱う `LCA` のインスタンスを生成します。

## メソッド
### addEdge
```java
public void addEdge(int u, int v)
```
無向辺 $u, v$ を追加します。

**制約**
- $0 \leq u, v < n$

**計算量**
- $O(1)$

### build
```java
public void build()
```

`LCA` を構築します。 

**計算量**
- $O( n \log{n} )$

### getLCA
```java
public int getLCA(int u, int v)
```
頂点 $u, v$ の最小共通先祖を返します。

⚠️注意⚠️：このメソッドを呼び出す前に `build` を呼び出す必要があります。

**制約**
- $0 \leq u, v < n$

**計算量**
- $O(1)$


## dist
```java
public int dist(int u, int v)
```

２頂点間の距離を求めます。

⚠️注意⚠️：このメソッドを呼び出す前に `build` を呼び出す必要があります。


**制約**
- $0 \leq u, v < n$

**計算量**
- $O(1)$

## 使用例
AtCoder Beginner Contest 014 - 閉路 https://atcoder.jp/contests/abc014/submissions/50268315

Library Checker - 	
Lowest Common Ancestor https://judge.yosupo.jp/submission/190714