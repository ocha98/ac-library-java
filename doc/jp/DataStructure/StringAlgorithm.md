# クラス StringAlgorithm
- - -

文字列アルゴリズム詰め合わせです。
文字列に関する様々なアルゴリズムが入っています。

## suffixArray

```Java
// (1)
public static int[] suffixArray(java.lang.String s)
// (2)
public static int[] suffixArray(char[] s)
// (3)
public static int[] suffixArray(int[] s)
// (4)
public static int[] suffixArray(int[] s, int upper)
```

長さ $n$ の文字列 `s` の Suffix Array として、長さ $n$ の `int` 配列を返します。  
Suffix Array `sa`は $(0,1,\ldots ,n-1)$ の順列であって、各 $i=0,1,\cdots , n-2$ について $\mathrm{s}[\mathrm{sa}_ {i} : n ) < \mathrm{s}[\mathrm{sa}_ {i+1} : n )$ を満たすものです。

**制約**
* $0 \le n \le 10^8$
* (4) $0\le \mathrm{upper}<10^8$
* (4) $s$ のすべての要素 $x$ について $0\le x\le \mathrm{upper}$

**計算量**
* (1) (2) $O(n)$
* (3) 時間 $O(n\log n)$ 空間 $O(n)$
* (4) $O(n+\mathrm{upper})$

## lcpArray

```Java
// (1)
public static int[] lcpArray(java.lang.String s, int[] sa)
// (2)
public static int[] lcpArray(char[] s, int[] sa)
// (3)
public static int[] lcpArray(int[] s, int[] sa)
```

長さ $n$ の文字列 `s` の LCP Array として、長さ $n-1$ の配列を返します。  
$i$ 番目の要素は $\mathrm{s}[\mathrm{sa}_ i:n), \mathrm{s}[\mathrm{sa}_{i+1}:n)$ の LCP ( Longest Common Prefix ) の長さです。

**制約**
* `sa` は `s` の Suffix Array
* $1 \le n \le 10^8$

**計算量**
* $O(n)$

### 使用例
[ https://atcoder.jp/contests/practice2/submissions/16585271 ]

## zAlgorithm

```Java
// (1)
public static int[] zAlgorithm(java.lang.String s)
// (2)
public static int[] zAlgorithm(char[] s)
// (3)
public static int[] zAlgorithm(int[] s)
```

入力の長さを $n$ として、長さ $n$ の配列を返します。  
$i$番目の要素は $\mathrm{s}[0:n)$ と $\mathrm{s}[i:n)$ の LCP ( Longest Common Prefix ) の長さです。

**制約**
* $0\le n\le 10^8$

**計算量**
* $O(n)$
