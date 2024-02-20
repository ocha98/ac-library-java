# ArrayUtils
---

`int[]` 、 `long[]` に関するツールがあります。

## メソッド
### lowerBound
```java
public static int lowerBound(int[] a, int x)
public static int lowerBound(long[] a, long x)
public static int lowerBound(double[] a, double x)
public static <T extends Comparable<T>> int lowerBound(T[] a, T x)
```

ソート済みの配列に対し $x$ 以上の値が現れる最初のインデックスを返します。

そのような要素が無い場合 `a.length` が返されます。

**計算量**
- $O(\log{(\text{a.length})})$

### upperBound
```java
public static int upperBound(int[], int x)
public static int upperBound(long[], long x)
public static int upperBound(double[] a, double x)
public static <T extends Comparable<T>> int lowerBound(T[] a, T x)
```

ソート済みの配列に対し $x$ より大きい値が現れる最初のインデックスを返します。

そのような要素が無い場合 `a.length` が返されます。

**計算量**
- $O(\log{(\text{a.length})})$

### dedup
```java
public static int[] dedup(int[] a)
public static long[] dedup(long[] a)
```

隣接する重複要素を取り除いた新たな配列を返します。

**計算量**
- $O(\text{a.length})$

## 例
```java
int[] a = {1, 2, 3, 4};
assert ArrayUtils.lowerBound(a, 1) == 0;
assert ArrayUtils.lowerBound(a, 5) == 4;

assert ArrayUtils.upperBound(a, 1) == 1;
assert ArrayUtils.upperBound(a, 4) == 4;

int[] b = {1, 2, 3, 3, 4, 4, 4, 4};
assert Arrays.equals(ArrayUtils.dedup(b), a);
```