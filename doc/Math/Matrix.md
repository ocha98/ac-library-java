# クラス Matrix
- - -

行列演算を行います。

## コンストラクタ
```java
public Matrix(int h, int w, long e) // e で初期化された h x w の行列
public Matrix(int h, int w) // 0 で初期化された h x w の行列
public Matrix(int n, long e) // e で初期化された h x n の正方行列
public Matrix(int n) // 0 で初期化された h x n の正方行列
public Matrix(final long[][] a) // 2次元配列をもとに行列を生成
```

## メソッド
### get
```java
public long get(int i, int j)
```

$i$ 行目、 $j$ 列目の要素を返します。

**制約**
- $0 \leq i < H$
- $0 \leq j < W$

**計算量**
- $O(1)$

### getHeight
```java
public int getHeight()
```

行数を返します。

**計算量**
- $O(1)$

### getWidth
```java
public int getWidth()
```

列数を返します。

**計算量**
- $O(1)$

### set
```java
public void set(int i, int j, long val)
```

$i$ 行目、 $j$ 列目に `val` を代入します。

**制約**
- $0 \leq i < H$
- $0 \leq j < W$

**計算量**
- $O(1)$

### copy
```java
public Matrix copy()
```

自身のディープコピーを作成します。

**計算量**
- $O(HW)$

### shape
```java
public java.awt.Dimension shape()
```

行数と列数を `java.awt.Dimension` で返します

**計算量**
- $O(1)$

### mul
```java
public Matrix mul(final Matrix other)
```

$(\text{self}) (\text{other})$ の行列積を計算します。

**制約**
- $\text{self}.W = \text{other}.H$

**計算量**
- $O(\text{self}.H \times \text{other}.W \times \text{self}.W)$

### modAsg
```java
public void modAsg(final int mod)
```

全ての要素の `mod` を取ります。

**制約**
- $0 < \text{mod}$

**計算量**
- $O(HW)$


### mulMod
```java
public Matrix mulMod(final Matrix other, final int mod)
public Matrix mulMod(final Matrix other, final ModIntFactory factory)
```
$(\text{self}) (\text{other})$ の行列積の `mod` で割った余りを求めます。

⚠️ $\text{self},\text{other}$ の各要素は mod が取られている前提です。 `modAsg` メソッドで予め mod を取っておいてください。

`ModIntFactory` を使うこともできます。

**制約**
- $\text{self}.H = \text{other}.W$
- $0 \leq \text{self}_ {i,j}, \text{other}_ {i,j} < \text{MOD}$

**計算量**
- $O(\text{self}.H \times \text{other}.W \times \text{self}.W)$

### pow
```java
public Matrix pow(long n)
```

正方行列の累乗を計算します。

**制約**
- $H = W$

**計算量**

- $O(H^3 \log(N))$

### powMod
```java
public Matrix powMod(long n, final int mod)
public Matrix powMod(long n, final ModIntFactory mod)
```

正方行列の累乗を `mod` で割った余りを求めます。

⚠️ 各要素は mod が取られている前提です。 `modAsg` メソッドで予め mod を取っておいてください。

`ModIntFactory` を使うこともできます。

**制約**
- $H = W$
- $0 \leq \text{self}_{i,j} < \text{MOD}$

**計算量**

- $O(H^3 \log(N))$
