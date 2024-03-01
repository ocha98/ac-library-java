# クラス Convolution

- - -

NTT で剰余 convolution を計算するクラスです。内部で `ModInt` を使用しているメソッドが $1$ 個あるので、
それを削除するか、使用する場合は `ModInt` も貼り付けてください。

## 使い方

static な `Convolution.convolute` を呼び出します。

```java
public static void main(String[] args) {
    // NTT用素数のModを指定します
    int mod = 998244353;
    long[] a = { 1, 2, 3, 4, 5 };
    long[] b = { 6, 7, 8 };
    // 畳み込みを計算します。a.length + b.length - 1 の配列が帰ります。
    long[] ret = Convolution.convolution(a, b, mod);
    System.out.println(Arrays.toString(ret));
}
```

## メソッド

### convolution (NTT prime only)

```java
public static long[] convolution(long[] a, long[] b, int mod) 
```

リストまたは配列 `a` と `b` の剰余 convolution を計算します。

**制約**
* `mod` は NTT 用素数( $998244353, 1053818881, 1004535809, \dots$ )

**計算量**
* $n =$ `a.length` $+$ `b.length` としたとき、 $O(n \log n + \log mod)$

### convolution (Any mod)

```java
public static long[] convolutionLL(long[] a, long[] b, int mod) 
public static java.util.List<ModIntFactory.ModInt> convolution(
            java.util.List<ModIntFactory.ModInt> a,
            java.util.List<ModIntFactory.ModInt> b
)
```

リストまたは配列 `a` と `b` の剰余 convolution を計算します。

**制約**
* `mod` は任意の正整数

**計算量**
* $n =$ `a.length` $+$ `b.length` としたとき、 $O(n \log n + \log mod)$
