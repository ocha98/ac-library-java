# クラス ModIntFactory, ModIntFactory$ModInt
- - -

剰余演算をサポートするクラスです。

## 使い方

1. `ModIntFactory` のインスタンス ( ModInt のファクトリ) を生成
2. 生成した Factory から `ModIntFactory$ModInt` を生成

```java
public static void main(String[] args) {
    // ModIntFactory のインスタンスを生成 (ここで mod を設定する)
    ModIntFactory factory = new ModIntFactory(998244353);
    // ModIntFactory を用いて ModInt を生成する
    ModIntFactory.ModInt n = factory.create(10000);
    ModIntFactory.ModInt m = factory.create(100000);
    // (n * m) % mod を計算
    ModIntFactory.ModInt k = n.mul(m);
    // value() メソッドにより値を取得する
    int kVal = k.value();
}
```

`Java 11` を使うことの出来る環境では，以下のように簡潔に書くことが出来ます。

```java
public static void main(String[] args) {
    var factory = new ModIntFactory(998244353);
    var n = factory.create(10000);
    var m = factory.create(100000);
    var k = n.mul(m);
    int kVal = k.value();
}
```


# ModIntFactory
## コンストラクタ

```java
public ModIntFactory(int mod)
```

`ModInt` を生成するファクトリを生成します。

**計算量**
* $O(1)$

## メソッド
### create
```java
public ModInt create(long value)
```

値 $\mathrm{value}$ % $\mathrm{mod}$ を持つ `ModInt` を生成します。

**計算量**
* $O(1)$

### factorial
```java
public ModInt factorial(int i)
```

値 $i!$ % $\mathrm{mod}$ を持つ `ModInt` を生成します。

**計算量**
* これまでの最大の $i$ を $i_{\mathrm{max}}$ としたとき、 $O(\max(1,i-i_{\mathrm{max}}))$

### permutation
```java
public ModInt permutation(int n, int r)
```

値 ${}_n \mathrm{P}_r$ を持つ `ModInt` を生成します。

**計算量**
* これまでの最大の $n$ を $n_{\mathrm{max}}$ としたとき、 $O(\max(1,n-n_{\mathrm{max}}))$

### combination
```java
public ModInt combination(int n, int r)
```

値 ${}_n \mathrm{C}_r$ を持つ `ModInt` を生成します。

**計算量**
* これまでの最大の $n$ を $n_{\mathrm{max}}$ としたとき、 $O(\max(1,n-n_{\mathrm{max}}))$

# ModIntFactory$ModInt
## コンストラクタ

外部からコンストラクタを呼ぶことは出来ません。

## メソッド

### mod

```java
public int mod()
```

`mod` を返します。

**計算量**
* $O(1)$

### value

```java
public int value()
```

保持している値を返します。  
__注意: `ModInt` のフィールド `int value` に直接アクセスしないで下さい. 正しい値が取得できない可能性があります.__

**計算量**
* $O(1)$

### add

```java
// (1)
public ModInt add(ModInt mi)
// (2)
public ModInt add(ModInt mi1, ModInt mi2)
// (3)
public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3)
// (4)
public ModInt add(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4)
// (5)
public ModInt add(ModInt mi1, ModInt... mis)
// (6)
public ModInt add(long mi)
```

1. 値 $(a + b)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
2. 値 $(a + b + c)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
3. 値 $(a + b + c + d)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
4. 値 $(a + b + c + d + e)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
5. 値 $(a + b + c + d + e + f + \dots)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
6. 値 $(a + b)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。 定数の加算でこれを用いると便利です。

**計算量**
* (1)~(4)、(6): $O(1)$
* (5): $n$ を可変長引数の長さとして、 $O(n)$

### sub

```java
// (1)
public ModInt sub(ModInt mi)
// (2)
public ModInt sub(long mi)
```

1. 値 $(a - b)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
2. 値 $(a - b)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。 定数の減算でこれを用いると便利です。

**計算量**
* $O(1)$

### mul

```java
// (1)
public ModInt mul(ModInt mi)
// (2)
public ModInt mul(ModInt mi1, ModInt mi2)
// (3)
public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3)
// (4)
public ModInt mul(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4)
// (5)
public ModInt mul(ModInt mi1, ModInt... mis)
// (6)
public ModInt mul(long mi)
```

1. 値 $(a \times b)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
2. 値 $(a \times b \times c)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
3. 値 $(a \times b \times c \times d)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
4. 値 $(a \times b \times c \times d \times e)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
5. 値 $(a \times b \times c \times d \times e \times f \times \dots)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。
6. 値 $(a \times b)$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。 定数の乗算でこれを用いると便利です。

**計算量**
* (1)~(4)、(6): $O(1)$
* (5): $n$ を可変長引数の長さとして、 $O(n)$

### div

```java
// (1)
public ModInt div(ModInt mi)
// (2)
public ModInt div(long mi)
```

1. 値 $(a \times b^{-1})$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。 ただし、 $b^{-1}$ は $(b \times x)$ % $\mathrm{mod} = 1$ を満たす $x$ です。
2. 値 $(a \times b^{-1})$ % $\mathrm{mod}$ を持つ `ModInt` を新たに生成します。 定数の除算でこれを用いると便利です。

**制約**
* $\mathrm{gcd}(b, mod) = 1$

**計算量**
* $O(\log \mathrm{mod})$

### inv

```java
public ModInt inv()
```

`ModInt a` に対して、 $(a \times x)$ % $\mathrm{mod} = 1$ を満たす値 $x$ を持つ `ModInt` を新たに生成します ( $a$ の値は書き換わりません)。

**計算量**
* $O(\log \mathrm{mod})$

**制約**
* $\mathrm{gcd}(a, mod) = 1$

### pow

```java
public ModInt pow(long n)
```

$a^n$ % $\mathrm{mod}$ を満たす値 $x$ を持つ `ModInt` を新たに生成します ( $a$ の値は書き換わりません)。

**計算量**
* $O(\log n)$

### addAsg

```java
// (1)
public ModInt addAsg(ModInt mi)
// (2)
public ModInt addAsg(ModInt mi1, ModInt mi2)
// (3)
public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3)
// (4)
public ModInt addAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4)
// (5)
public ModInt addAsg(ModInt... mis)
// (6)
public ModInt addAsg(long mi)
```

1. $a = a + b$ を行います。 $a$ の値は書き換えられます。
2. $a = a + b + c$ を行います。 $a$ の値は書き換えられます。
3. $a = a + b + c + d$ を行います。 $a$ の値は書き換えられます。
4. $a = a + b + c + d + e$ を行います。 $a$ の値は書き換えられます。
5. $a = a + b + c + d + e + f + ...$ を行います。 $a$ の値は書き換えられます。
6. $a = a + b$ を行います. $a$ の値は書き換えられます。 定数の加算でこれを用いると便利です。

**計算量**
* (1)~(4)、(6): $O(1)$
* (5): $n$ を可変長引数の長さとして、 $O(n)$

### subAsg

```java
// (1)
public ModInt subAsg(ModInt mi)
// (2)
public ModInt subAsg(long mi)
```

1. $a = a - b$ を行います。 $a$ の値は書き換えられます。
2. $a = a - b$ を行います。 $a$ の値は書き換えられます。定数の減算でこれを用いると便利です。

**計算量**
* $O(1)$

### mulAsg

```java
// (1)
public ModInt mulAsg(ModInt mi)
// (2)
public ModInt mulAsg(ModInt mi1, ModInt mi2)
// (3)
public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3)
// (4)
public ModInt mulAsg(ModInt mi1, ModInt mi2, ModInt mi3, ModInt mi4)
// (5)
public ModInt mulAsg(ModInt... mis)
// (6)
public ModInt mulAsg(long mi)
```

1. $a = a \times b$ を行います。 `a` の値は書き換えられます。
2. $a = a \times b \times c$ を行います。 `a` の値は書き換えられます。
3. $a = a \times b \times c \times d$ を行います。 `a` の値は書き換えられます。
4. $a = a \times b \times c \times d \times e$ を行います。 `a` の値は書き換えられます。
5. $a = a \times b \times c \times d \times e \times f \times \dots$ を行います。 `a` の値は書き換えられます。
6. $a = a \times b$ を行います。 `a` の値は書き換えられます。 定数の乗算でこれを用いると便利です。

**計算量**
* (1)~(4)、(6): $O(1)$
* (5): $n$ を可変長引数の長さとして、 $O(n)$

### divAsg

```java
// (1)
public ModInt divAsg(ModInt mi)
// (2)
public ModInt divAsg(long mi)
```

1. $a = a \times b^{-1}$ を行います。 $a$ の値は書き換えられます。 ただし、 $b^{-1}$ は $(b \times x)$ % $\mathrm{mod} = 1$ を満たす $x$ です。
2. $a = a \times b^{-1}$ を行います。 $a$ の値は書き換えられます。 定数の除算でこれを用いると便利です。

**制約**
* $\mathrm{gcd}(b, \mathrm{mod}) = 1$

**計算量**
* $O(\log \mathrm{mod})$
