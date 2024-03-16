# クラス Sieve
---

エラトステネスの篩を構築します。

## コンストラクタ
```java
public Sieve(int n)
```
$[0, n]$ の範囲の篩を構築します。

**計算量**
- $O(n \log{\log{n}})$


## メソッド
### getPrimes
```java
 public int[] getPrimes()
```

構築された篩から素数のリストを取得します。つまり、$[0, n]$ の範囲で見つかった全ての素数を含む配列を返します。

**計算量**
- $O(n)$


### isPrime
```java
public boolean isPrime(int x)
```

与えられた整数 $x$ が素数であるかどうかを判定します。


**制約**
- $0 \leq x \leq n$

**計算量**
- $O(1)$

### factorize
```java
public java.util.ArrayList<PE> factorize(int x)
```

整数 $x$ を素因数分解します。

**制約**
- $0 \leq x \leq n$

**計算量**
- $O(\log{x})$

### divisors
```java
public int[] divisors(int x)
```

整数 $x$ の約数のリストを返します。

**制約**
- $1 \leq x \leq n$

**計算量**
- $d(x)$ を $x$ の約数の個数とした時、$O(d(x))$ 