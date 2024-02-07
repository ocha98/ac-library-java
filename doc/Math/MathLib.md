# クラスMathLib
- - -

数学的アルゴリズム詰め合わせです。
整数計算に関する様々なアルゴリズムが入っています。

## メソッド
### crt
```java
public static long[] crt(long[] r, long[] m)
```

同じ長さの配列 $r$ 、 $m$ を渡します。
この配列の長さを $n$ としたとき、 $$x \equiv r_i \mod m_i$$ を解きます。  
答えは存在するならば $y, z (0 \leq y < z = lcm(m))$ を用いて $x \equiv y \mod z$ の形で書けることが知られています。  
この関数の返り値は、 $y$ と $z$ をこの順に格納した長さ $2$ の配列です。  
答えがない場合は $[0,0]$ を返します。 $n=0$ のときは $[0,1]$ を返します。

**計算量**
* $O(n \log \mathrm{lcm}(m))$

### floor_sum
```java
public static long floor_sum(long n, long m, long a, long b)
```

$$\sum_{i=0}^{n-1} floor(\frac{a \times i+b}{m})$$ を返します。

**制約**
* $0 \leq n \leq 10^9$
* $1 \leq m \leq 10^9$
* $0 \leq a,b < m$

**計算量**
* $O(\log (n+m+a+b))$

### gcd, lcm

```java
public static long gcd(long a, long b)
public static long lcm(long a, long b)
```
$2$ 整数 $a, b$ の最大公約数、最小公倍数を返します。  
返り値は必ず非負整数になります。

**計算量**
* $O(\log \min(a,b))$

### divisors
```java
public static java.util.ArrayList<Long> divisors(int n)
```
整数 $n$ の約数を昇順に含んだリストを返します。

**計算量**
* $O(\sqrt{n})$
